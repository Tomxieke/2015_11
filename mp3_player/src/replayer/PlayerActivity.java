package replayer;

import java.util.ArrayList;

import lyric.LyricView;
import mp3_adapter.ViewPagerAdapter;
import mylog.Mylog;
import replayer.Resevice.MusicServiceBinder;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.mp3_player.R;

public class PlayerActivity extends Activity implements OnClickListener,OnSeekBarChangeListener{
	private ImageView mNextMusicImg,mLastMusicImg,mPuseImg,mlocalMusicImg,mLoaclimg;
	private TextView mNowTimeTxt,mTotalTimeTxt,mMusicName;
	private Intent mIntent;
	private SeekBar mSeekbar;
	private Handler mHandler = new Handler();
	private ViewPager mViewPager;
	private ViewPagerAdapter mPagerAdapter;
	private MusicServiceBinder serviceBinder;
	
	private ServiceConnection mServiceConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			serviceBinder = (MusicServiceBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			if(serviceBinder != null){
				serviceBinder = null;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		initView();
		
		Mylog.v("++++++++++++++onCreate+++++++++++++++++++++++++++");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		resgesitReceiver();  //注册广播
	}
	
	@Override
	protected void onPause() {  //注销广播
		super.onPause();
		unregisterReceiver(receiver);  
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		actionIntent.setAction(Action.SEND_NOTIFICATION);
		sendBroadcast(actionIntent);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mServiceConnection != null){
			unbindService(mServiceConnection);
			stopService(mIntent);
		}
		
	}
	
	public void initView(){
		mIntent = new Intent(this,Resevice.class);
		mNextMusicImg = (ImageView) findViewById(R.id.mp3_nextMusic_btn);
		mNextMusicImg.setOnClickListener(this);
		mLastMusicImg = (ImageView) findViewById(R.id.mp3_lastMusic_btn);
		mLastMusicImg.setOnClickListener(this);
		mPuseImg = (ImageView) findViewById(R.id.mp3_puseMusic_btn);
		mPuseImg.setOnClickListener(this);
		mlocalMusicImg = (ImageView) findViewById(R.id.local_music_img);
		mlocalMusicImg.setOnClickListener(this);
		mSeekbar = (SeekBar) findViewById(R.id.mp3_seekbar);
		mSeekbar.setOnSeekBarChangeListener(this);
		mNowTimeTxt = (TextView) findViewById(R.id.mp3_nowtime_txt);
		mTotalTimeTxt = (TextView) findViewById(R.id.mp3_alltime_txt);
		mMusicName = (TextView) findViewById(R.id.mp3_musicName_text);
		mLoaclimg = (ImageView) findViewById(R.id.local_music_img);
		mLoaclimg.setOnClickListener(this);
		
		mViewPager = (ViewPager) findViewById(R.id.mp3_viewpager);
		
		mPagerAdapter = new ViewPagerAdapter();
		mPagerAdapter.refreshData(getViewPagerData());
		mViewPager.setAdapter(mPagerAdapter);
		
		Intent intent = getIntent();
		mIntent.putExtras(intent);
		startService(mIntent);
		bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
	}
//--------------------------seekbar事件--------------------------------	
	int userProgress;
	boolean user;   //拖动进度条时间过长时要弹回去
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		userProgress = progress; 
	//	seekBar.setProgress(progress);
		user = fromUser;
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	//	seekBar.setProgress(userProgress);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		actionIntent.setAction(Action.USER_PROGRESS);
		actionIntent.putExtra(Action.USER_PROGRESS, userProgress);
		sendBroadcast(actionIntent);
		user = !user;
	}
	
//------------------------------------------------------	
	Intent actionIntent = new Intent();
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.mp3_nextMusic_btn:
			actionIntent.setAction(Action.NAXT_MUSIC);
			sendBroadcast(actionIntent);
			break;
		case R.id.mp3_puseMusic_btn:
			actionIntent.setAction(Action.PAUSE_MUSIC);
			sendBroadcast(actionIntent);
			break;
		case R.id.mp3_lastMusic_btn:
			actionIntent.setAction(Action.LAST_MUSIC);
			sendBroadcast(actionIntent);
			break;
		case R.id.local_music_img:  //本地音乐
			Intent intent = new Intent(this,ReMusicListActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	
	ActivityReceiver receiver = new ActivityReceiver();
	/*注册广播*/
	public void resgesitReceiver(){
		receiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter(Action.PLAY_STATE_PLAYINT);
		filter.addAction(Action.PLAY_STATE_PAUSE);
		filter.addAction(Action.PLAY_TIME);
		filter.addAction(Action.MUSIC_NAME);
		registerReceiver(receiver, filter);
	}
	
	/*广播*/
	public class ActivityReceiver extends BroadcastReceiver{
		int nowTime,totalTime;
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(Action.PLAY_STATE_PLAYINT.equals(action)){
				mPuseImg.setImageResource(R.drawable.landscape_player_btn_play_normal);
			}else if(Action.PLAY_STATE_PAUSE.equals(action)){
				mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal);
			}else if(Action.PLAY_TIME.equals(action)){
				totalTime = intent.getIntExtra(Action.MUSIC_TOTAL_TIME, 0);  //得到总时间
				mSeekbar.setMax(totalTime);
				if(user){
					nowTime = userProgress;
				}else{
					mSeekbar.setProgress(nowTime);
					nowTime = intent.getIntExtra(Action.MUSIC_NOW_TIME, 0); //当前进度
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mNowTimeTxt.setText(setTimes(nowTime));
						mTotalTimeTxt.setText(setTimes(totalTime));
					}
				});
			}else if(Action.MUSIC_NAME.equals(action)){
				String name = intent.getStringExtra(Action.MUSIC_NAME);
				mMusicName.setText(name);
			}
		}
	}

	/*时间00:00处理*/
	public String setTimes(int times){
		int mTimes = times/1000;
		int minute = 0,second = 0 ;
		//if(mTimes > 60){
			minute = (int) (mTimes/60);
			second = (int) (mTimes%60);
			return getTime(minute) + ":" + getTime(second);
		/*}else{
			second = (int) (mTimes%60);
			return getTime(minute) + ":" + 
		}*/
	}
	
	public String getTime(int date){
		if(date < 10){
			return "0" + date;
		}else{
			return "" + date;
		}
	}
	
	public ArrayList<View> getViewPagerData(){   // 刚进去的viewpager数据源
		ArrayList<View> mPagerListData = new ArrayList<View>();
		View v1 = LayoutInflater.from(this).inflate(R.layout.re_viewpager_item4_layout, null);
		final LyricView lyricView = (LyricView) v1.findViewById(R.id.mp3_lyricView);   //得到这个控件
		mPagerListData.add(v1);
		View v2 = LayoutInflater.from(this).inflate(R.layout.re_viewpager_item5_layout, null);
		mPagerListData.add(v2);
		View v3 = LayoutInflater.from(this).inflate(R.layout.re_viewpager_item6_layout, null);
		mPagerListData.add(v3);
		
		new Thread(new Runnable() {//开启线程刷新歌词界面
			long time = 100; // 开始 的时间，不能为零，否则前面几句歌词没有显示出来
			@Override
			public void run() {
				while (true) {
					if(serviceBinder != null && serviceBinder.playState()){
						long sleeptime = lyricView.updateIndex(time);
						time += sleeptime;
						mHandler.post(new Runnable() {
							public void run() {
								lyricView.invalidate(); // 更新视图
							}
						});
						if (sleeptime == -1)
							return;
						try {
							Thread.sleep(sleeptime);
						} catch (InterruptedException e) {
							e.printStackTrace();
					}
					}
				}
			}
		}).start();
		
		return mPagerListData;
	}
}
