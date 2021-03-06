package broadcastMP3player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import mp3_adapter.MusicBean;
import mylog.Mylog;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BroadCastMp3Service extends Service {
	private int playMode = 2;
	private String name;
	public MediaPlayer mMediaPlayer;
	private ServiceBinder mServiceBinder = new ServiceBinder();  //onBind()的返回对象，可以绑定的activity中获取
	private ArrayList<File> listFile = new ArrayList<File>();  //讲加载歌曲的线程放在服务里面
	private int posstion;  //当前播放的是listFile中对应位置的音乐
	private interface ServiceInt{   //交互接口
		public MediaPlayer getMediaPlayer();
		public ArrayList<File> getListFile();
		public void getIndex(int _posstion);   //点击listview播放对应项
		
		public boolean puseState();
		public int getTotalTime(); // 获取歌曲总时间
		public int getNowTime();  //获取歌曲当前播放时间
		public void setMediaPlayer(int progress); //拖动进度条设置进度
		public String getMusicName();//获取正在播放歌曲的名字
		public void getPlayMode(int mode);  //传递循环播放模式
	}
	
	public class ServiceBinder extends Binder implements ServiceInt{  //交互类
		@Override
		public MediaPlayer getMediaPlayer() {
			return mMediaPlayer;   //  回传给Activity用来设置seekbar
		}

		@Override
		public ArrayList<File> getListFile() {
			return listFile;  //设置完成回传给Activity设置listView的数据源
		}

		@Override
		public void getIndex(int _posstion) {
			posstion = _posstion;
			playMusic(); // 播放音乐
		}

		@Override
		public int getTotalTime() {
			if (mMediaPlayer != null) {
				return mMediaPlayer.getDuration();
			} else {
				return 0;
			}
		}

		@Override
		public int getNowTime() {
			if (mMediaPlayer != null) {
				return mMediaPlayer.getCurrentPosition();
			} else {
				return 0;
			}
		}

		@Override
		public boolean puseState() {
			if(mMediaPlayer.isPlaying()){
				mMediaPlayer.pause();
				return false;
			}else{
				mMediaPlayer.start();
				return true;
			}
		}

		@Override
		public void setMediaPlayer(int progress) {
			mMediaPlayer.seekTo(progress);
		}
		
		@Override
		public String getMusicName(){
			return name;
		}

		@Override
		public void getPlayMode(int mode) {
			playMode = mode;
		}
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mMediaPlayer = new MediaPlayer();
		new Thread(new Runnable() { // 扫描歌曲，设置数据源并返回给Activity
					@Override
					public void run() {
						searchMp3File(Environment.getExternalStorageDirectory()); // 设置数据源
					}
				}).start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {   //回传过来的数据
		if (intent != null) {
			MusicBean m = intent.getParcelableExtra("MUSIC_BEAN");
			if (m != null) {
				posstion = intent.getIntExtra("MUSIC_POSSTION", 0);
				initMusic(m.getMusicPath()); // 调用方法播放音乐
			}
		}
		regestReceiver();
		regestReceiverN();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mServiceBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {  //解绑时释放资源
		super.onDestroy();
		if(mMediaPlayer != null){
			mMediaPlayer.stop();
		}
		mMediaPlayer.release();
	}
	
	/*暂停按钮*/
	public boolean playState(){ //暂停按钮
		if(mMediaPlayer.isPlaying()){
			mMediaPlayer.pause();
			return true;
		}else{
			mMediaPlayer.start();
			return false;
		}
	}
	
	/*上一曲*/
	public void playLastMusic(){
		if(--posstion < 0){
			posstion = listFile.size()-1;
		}
		playMusic();
	}
	
	/*上一曲*/
	public void playNaxtMusic(){
		if(++posstion >= listFile.size()){
			posstion = 0;
		}
		playMusic();
	}
	
	/*随机播放*/
	public void randomPlayMode(){
		int size = listFile.size();
		posstion = (int) (Math.random()*size);
		playMusic();
	}
	
	/*播放对应listFiles位置的音乐*/
	public void playMusic(){
		File mp3File = listFile.get(posstion);
		name = mp3File.getName();
		initMusic(mp3File.getAbsolutePath());
	}
	
	/*初始化MediaPlayer*/
	public void initMusic(String path) {
		if (mMediaPlayer != null) {
			try {
				mMediaPlayer.reset(); //重置资源
				mMediaPlayer.setDataSource(path);
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
					if(playMode == 1){    //循环模式为1，单曲播放
						playMusic();
					}else if(playMode == 2){ //顺序播放
						playNaxtMusic();   //一首曲播放完全直接播放下一首
					}else if(playMode == 3){ //随机播放
						randomPlayMode();
					}
					
					Intent intent = new Intent("mp3_main.MainActivity");  //与广播关联
					sendBroadcast(intent);  //发送广播
				}
			});
		}
	}
	

	/* 取得所有的mp3文件 */
	public void searchMp3File(File srcFile) {
		File[] arrFiles = srcFile.listFiles();
		if (arrFiles != null && arrFiles.length > 0) { // 空判断
			for (int i = 0; i < arrFiles.length; i++) { // 不为空就遍历
				File mp3File = arrFiles[i];
				if (mp3File.isDirectory()) { // 是目录 则递归
					searchMp3File(mp3File);
				} else {
					if (mp3File.getName().endsWith(".mp3")) { // 文件以mp3结尾，则放入数据源
						listFile.add(mp3File);
					}
				}
			}
		}
	}
	
	
	public void regestReceiver(){
		Mp3BroadCastReceiver receiver = new Mp3BroadCastReceiver();
		IntentFilter filter = new IntentFilter(Action.Mp3Action.LAST_MUSIC);
		registerReceiver(receiver, filter);
	}
	
	public void regestReceiverN(){
		Mp3BroadCastReceiver receiver = new Mp3BroadCastReceiver();
		IntentFilter filter = new IntentFilter(Action.Mp3Action.LAST_MUSIC);
		registerReceiver(receiver, filter);
	}
	
	public class Mp3BroadCastReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(Action.Mp3Action.LAST_MUSIC.equals(action)){
				playLastMusic();
			}else if(Action.Mp3Action.NEXT_MUSIC.equals(action)){
				playNaxtMusic();
			}
		}
		
	}
	
}
