package replayer;

import java.io.IOException;
import java.util.ArrayList;

import mp3_adapter.MusicBean;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.mp3_player.R;

public class Resevice extends Service {
	private boolean flag = true;   //循环发送进度条的进度
	private MediaPlayer mMediaPlayer;
	private ArrayList<MusicBean> listData;
	private int posstion; 
	private Intent intent = new Intent(); //服务端广播intent
	NotificationManager manager;
	private String musicName;
	private interface ServiceInter{  //交互接口
		public boolean playState();
	}
	
	public class MusicServiceBinder extends Binder implements ServiceInter{
		@Override
		public boolean playState() {
			if(mMediaPlayer.isPlaying()){
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mMediaPlayer = new MediaPlayer();
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  //这个可以共用
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		listData = intent.getParcelableArrayListExtra("MusicBean");
		posstion = intent.getIntExtra("posstion", 0);
		registerReceiver();   //注册广播
		initMusic(listData.get(posstion));
		getTime();
	//	musicName = listData.get(posstion).getMusicName();  //第一次进入时就发送通知，并将该歌名传入
		reNotification();  //一启动服务就发送通知
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new MusicServiceBinder();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(receiver != null){
			unregisterReceiver(receiver);
		}
		flag = false;  // 结束发送广播循环
	}
	
	/*刷新进度条的*/
	public void getTime(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (flag ) {
					if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
					intent.putExtra(Action.MUSIC_TOTAL_TIME, mMediaPlayer.getDuration());  //发送文件播放的总时间
					intent.putExtra(Action.MUSIC_NOW_TIME,mMediaPlayer.getCurrentPosition()); // 发送正播放到的时间
					intent.setAction(Action.PLAY_TIME);   //发送广播
					sendBroadcast(intent);
					}
				}
				try {
					Thread.sleep(1000); // 每隔一秒发送一次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start(); 
	}
	
	/*暂停或播放*/
	public void pauseMusic(){
		if(mMediaPlayer.isPlaying()){
			mMediaPlayer.pause();
			intent.setAction(Action.PLAY_STATE_PAUSE);
			sendBroadcast(intent);
		}else{
			mMediaPlayer.start();
			intent.setAction(Action.PLAY_STATE_PLAYINT);
			sendBroadcast(intent);
		}
	}
	
	/*上一曲*/
	public void playLastMusic(){
		if(--posstion < 0){
			posstion = listData.size()-1;
		}
		initMusic(listData.get(posstion));
	}
	
	/*下一曲*/
	public void playNaxtMusic(){
		if(++posstion >= listData.size()){
			posstion = 0;
		}
		initMusic(listData.get(posstion));
	}
	
	/*播放music*/
	public void initMusic(MusicBean m) {
		if (mMediaPlayer != null) {
			try {
				musicName = m.getMusicName();
				Intent intent = new Intent(Action.MUSIC_NAME);
				intent.putExtra(Action.MUSIC_NAME, musicName);
				sendBroadcast(intent);
				mMediaPlayer.reset(); //重置资源
				mMediaPlayer.setDataSource(m.getMusicPath());
				mMediaPlayer.prepare();
				mMediaPlayer.start();
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					playNaxtMusic();
					reNotification();  //刷新通知，更改播放歌曲的图标
				}
			});
		}
	}
	
	Receiver receiver = new Receiver();
	/*注册广播*/
	public void registerReceiver(){
		receiver = new Receiver();
		IntentFilter filter = new IntentFilter(Action.PLAY_MUSIC);
		filter.addAction(Action.PAUSE_MUSIC);
		filter.addAction(Action.LAST_MUSIC);
		filter.addAction(Action.NAXT_MUSIC);
		filter.addAction(Action.USER_PROGRESS);
		filter.addAction(Action.SEND_NOTIFICATION);
		registerReceiver(receiver, filter);
	}
	/*定义广播*/
	public class Receiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(Action.PLAY_MUSIC.equals(action)){
				initMusic(listData.get(posstion));
			}else if(Action.PAUSE_MUSIC.equals(action)){
				pauseMusic();
				reNotification();  //刷新通知，更改暂停播放的图标
			}else if(Action.NAXT_MUSIC.equals(action)){
				playNaxtMusic();
				reNotification();  //刷新通知，更改播放歌曲的名字
			}else if(Action.LAST_MUSIC.equals(action)){
				playLastMusic();
				reNotification();  //刷新通知，更改播放歌曲的图标
			}else if(Action.USER_PROGRESS.equals(action)){   //用户拖动进度条
				int seek = intent.getIntExtra(Action.USER_PROGRESS, 0);
				mMediaPlayer.seekTo(seek);
			}else if(Action.SEND_NOTIFICATION.equals(action)){
				reNotification();  //activity不在前置生命走起是发送通知
			}
		}
	}

	/*发送广播*/
	public void reNotification(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.icon_notification);
		builder.setAutoCancel(true);
		builder.setTicker(musicName);
		RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.mp3_notification_layout);
		/*通知行为设置*/
		if(mMediaPlayer.isPlaying()){
			remoteView.setImageViewResource(R.id.mp3_puseMusic_btn, R.drawable.landscape_player_btn_play_normal);
		}else{
			remoteView.setImageViewResource(R.id.mp3_puseMusic_btn, R.drawable.landscape_player_btn_pause_normal);
		}
		remoteView.setTextViewText(R.id.notification_music_content_txt, musicName);
		remoteView.setOnClickPendingIntent(R.id.mp3_lastMusic_btn, pre_pendintIntent());
		remoteView.setOnClickPendingIntent(R.id.mp3_nextMusic_btn, next_pendintIntent());
		remoteView.setOnClickPendingIntent(R.id.mp3_puseMusic_btn, pause_pendintIntent());
	//	builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this,PlayerActivity.class), 0));
		
		builder.setContent(remoteView);
		manager.notify(1, builder.build());
	}
	
	/*返回上一曲的pendingIntent*/
	public PendingIntent pre_pendintIntent(){
		Intent intent = new Intent(Action.LAST_MUSIC); //这里关联广播的Action,就可以与广播关联了。相当于调用时就会发送关联这个antion的广播
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}
	
	/*返回下一曲的pendingIntent*/
	public PendingIntent next_pendintIntent(){
		Intent intent = new Intent(Action.NAXT_MUSIC);  
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}
	
	/*返回暂停的pendingIntent*/
	public PendingIntent pause_pendintIntent(){
		Intent intent = new Intent(Action.PAUSE_MUSIC);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		return pendingIntent;
	}
	
	
}
