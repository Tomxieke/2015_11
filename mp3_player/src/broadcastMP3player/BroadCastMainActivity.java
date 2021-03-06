package broadcastMP3player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import mp3_adapter.ListViewAdapter;
import mp3_adapter.MusicBean;
import mp3_adapter.ViewPagerAdapter;
import mp3_main.Mp3Service.ServiceBinder;
import mp3_main.MusicListActivity;
import mylog.Mylog;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp3_player.R;

public class BroadCastMainActivity extends Activity implements OnClickListener,OnItemClickListener,OnSeekBarChangeListener{
	
	private ViewPagerAdapter mPagerAdapter;
	boolean state;
	boolean flag = true;//控制seekbar无限循环
	private Handler mHandler = new Handler();
	private ImageView mNextMusicImg,mLastMusicImg,mPuseImg,mlocalMusicImg,mTileHomeImg,mPlayModeImg,mMusicTxtImg;
	private TextView mNowTimeTxt,mTotalTimeTxt,mMusicNameText;
	private SeekBar mSeekbar;
	private ViewPager mViewPager;
	private Intent intent;
	private ListView mListView;   // ViewPager里面的listview
	ArrayList<File> mp3List;  //所有mp3文件的集合  
	private ServiceBinder mServiceBinder;
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mServiceBinder = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mServiceBinder = (ServiceBinder) service;  //service服务回传回来的Binder对象
			getDataFromService();  //取得服务service的数据设置给listview
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		initView();
		registerReceiver(); // 注册广播
	}
	
	
	/*取得服务service传来的数据*/
	public void getDataFromService(){
		mp3List = mServiceBinder.getListFile();  //取得数据集合并设置给listview
		ListViewAdapter listViewAdapter = new ListViewAdapter(this);
		listViewAdapter.refushData(mp3List);
		mListView.setAdapter(listViewAdapter);
	}
	
	/*加载布局控件*/
	public void initView(){
		
		mNextMusicImg = (ImageView) findViewById(R.id.mp3_nextMusic_btn);
		mNextMusicImg.setOnClickListener(this);
		mLastMusicImg = (ImageView) findViewById(R.id.mp3_lastMusic_btn);
		mLastMusicImg.setOnClickListener(this);
		mPuseImg = (ImageView) findViewById(R.id.mp3_puseMusic_btn);
		mPuseImg.setOnClickListener(this);
		mlocalMusicImg = (ImageView) findViewById(R.id.local_music_img);
		mlocalMusicImg.setOnClickListener(this);
		mPlayModeImg = (ImageView) findViewById(R.id.play_mode_img);
		mPlayModeImg.setOnClickListener(this);
		mTileHomeImg =(ImageView) findViewById(R.id.title_local_first_img);
		mTileHomeImg.setOnClickListener(this);
		mMusicTxtImg = (ImageView) findViewById(R.id.title_musictxt_img);
		mMusicTxtImg.setOnClickListener(this);
		
		mSeekbar = (SeekBar) findViewById(R.id.mp3_seekbar);
		mSeekbar.setOnSeekBarChangeListener(this);
		mNowTimeTxt = (TextView) findViewById(R.id.mp3_nowtime_txt);
		mTotalTimeTxt = (TextView) findViewById(R.id.mp3_alltime_txt);
		setSeekBarProgress(); 
		
		mMusicNameText = (TextView) findViewById(R.id.mp3_musicName_text); 
		
		mViewPager = (ViewPager) findViewById(R.id.mp3_viewpager);
		
		mPagerAdapter = new ViewPagerAdapter();
		mPagerAdapter.refreshData(getViewPagerData());
		mViewPager.setAdapter(mPagerAdapter);
		mListView.setOnItemClickListener(this);
		
		intent = new Intent(this,BroadCastMp3Service.class);
		bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
		startService(intent);
		views = getViews(); 
	}
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.mp3_lastMusic_btn:
			mMusicNameText.setText("正在播放      "+mServiceBinder.getMusicName()); //点击上一曲下一曲时更新歌曲题目
			mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal); //暂停状态下点击上一首时改变为播放图标
			Intent intennt = new Intent(Action.Mp3Action.LAST_MUSIC);
			sendBroadcast(intennt);
			state = true;   //解决当在暂停状态下点击上一首时seekbar不更新的问题
		break;
		case R.id.mp3_nextMusic_btn:
			mMusicNameText.setText("正在播放      "+mServiceBinder.getMusicName());  
			mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal);
			intennt = new Intent(Action.Mp3Action.NEXT_MUSIC);
			sendBroadcast(intennt);
			state = true;
			break;
		case R.id.mp3_puseMusic_btn:
			state = mServiceBinder.puseState();
			if(state){
				mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal);
			}else{
				mPuseImg.setImageResource(R.drawable.landscape_player_btn_play_normal);
			}
			break;
		case R.id.local_music_img:
			Intent intent = new Intent(this,MusicListActivity.class);
			int requestCode = 1;
			startActivityForResult(intent, requestCode);
			break;
		case R.id.title_local_first_img:
			mPagerAdapter.refreshData(getViewPagerData()); //点击主页时再次刷新viewpager数据源显示主界面
			mViewPager.setAdapter(mPagerAdapter);
			getDataFromService();     //刷新viewpager中item项listview数据源，不然不会显示数据
			mListView.setOnItemClickListener(this);  //再次设置监听，不然点击点击就没有反应
			break;
		case R.id.title_musictxt_img:   
			mPagerAdapter.refreshData(reSetViewPagerData());
			mViewPager.setAdapter(mPagerAdapter);
			break;
		case R.id.play_mode_img:
			if(mode == 1){
				mServiceBinder.getPlayMode(mode);  //mode = 1 单曲循环
				mPlayModeImg.setImageResource(R.drawable.play_list_mode_repeat_one_normal);
				Toast.makeText(this, "单曲循环", Toast.LENGTH_SHORT).show();
				mode = 2;
			}else if(mode == 2){
				mServiceBinder.getPlayMode(mode);  //mode = 2 顺序播放
				mPlayModeImg.setImageResource(R.drawable.play_list_mode_sequent_normal);
				Toast.makeText(this, "顺序播放", Toast.LENGTH_SHORT).show();
				mode = 3;
			}else if(mode == 3){
				mServiceBinder.getPlayMode(mode);  //mode =3 随机播放
				mPlayModeImg.setImageResource(R.drawable.play_list_mode_shuffle_normal);
				Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
				mode = 1;
			}
			break;
		}
	}
	int mode = 2;  // 循环改变播放模式的图标
	
	@Override  //回传值方法 得到点击另一个activity的歌曲信息
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			state = true;  //暂停状态下去点击下一曲、上一曲、列表项都要将状态变为true。这样才会执行设置seekbar的代码
			mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal);   //并且将暂停按钮设置为播放状态
			MusicBean m = data.getParcelableExtra("MUSIC_BEAN");
			mMusicNameText.setText("正在播放      " + m.getMusicName());
			intent.putExtras(data); // 将回传的参数又传给service
			startService(intent);
			mPagerAdapter.refreshData(reSetViewPagerData());   //刷新wiewpager数据源显示歌手界面
			mViewPager.setAdapter(mPagerAdapter);
		}
	}
	
	
	@Override  
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		mServiceBinder.getIndex(arg2); //传id到服务播放对应歌曲
		state = true;
		mPuseImg.setImageResource(R.drawable.landscape_player_btn_pause_normal);  //点击播放就将图片设为播放状态
		
		
		mPagerAdapter.refreshData(reSetViewPagerData());
		mViewPager.setAdapter(mPagerAdapter);
		mMusicNameText.setText("正在播放      "+mServiceBinder.getMusicName());
	}
	
	/*------------------viewPager的数据源-----------------*/
	public ArrayList<View> getViewPagerData(){   // 刚进去的viewpager数据源
		ArrayList<View> mPagerListData = new ArrayList<View>();
		View v1 = LayoutInflater.from(this).inflate(R.layout.view_pager_item1_layout, null);
		mPagerListData.add(v1);
		View v3 = LayoutInflater.from(this).inflate(R.layout.view_pager_item3_layout, null);
		mPagerListData.add(v3);
		mListView = (ListView) LayoutInflater.from(this).inflate(R.layout.viewpager_item2_listview, null);
		mPagerListData.add(mListView);
		return mPagerListData;
	}
	
	public View[] getViews(){    //得到View[]数组，必须放在初始化里面
		LayoutInflater inflater = LayoutInflater.from(this);
		View v1 = inflater.inflate(R.layout.re_viewpager_item1_layout, null);
		View v2 = inflater.inflate(R.layout.re_viewpager_item2_layout, null);
		View v3 = inflater.inflate(R.layout.re_viewpager_item3_layout, null);
		View v4 = inflater.inflate(R.layout.re_viewpager_item4_layout, null);
		View v5 = inflater.inflate(R.layout.re_viewpager_item5_layout, null);
		View v6 = inflater.inflate(R.layout.re_viewpager_item6_layout, null);
		View v7 = inflater.inflate(R.layout.re_viewpager_item7_layout, null);
		View v8 = inflater.inflate(R.layout.re_viewpager_item8_layout, null);
		View v9 = inflater.inflate(R.layout.re_viewpager_item9_layout, null);
		View v10 = inflater.inflate(R.layout.re_viewpager_item10_layout, null);
		View v11 = inflater.inflate(R.layout.re_viewpager_item11_layout, null);
		View v12 = inflater.inflate(R.layout.re_viewpager_item12_layout, null);
		View[] views = new View[]{v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12};
		return views;
	}
	private View[] views;   
	public ArrayList<View> reSetViewPagerData(){ //点击listview的每一项的viewpager数据源
		ArrayList<View> mPagerListData = new ArrayList<View>();
		Random r = new Random();
		for(int i = 0;i <3;i++){
			mPagerListData.add(views[r.nextInt(12)]);
		}
		return mPagerListData;
	}
	
//---------------------------------------------------------------------------------------------------------
	public void setSeekBarProgress() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (flag) {
					if (mServiceBinder != null && state) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								final int totalTime = mServiceBinder.getTotalTime();
								final int nowTime = mServiceBinder.getNowTime();
								mSeekbar.setMax(totalTime);
								mSeekbar.setProgress(nowTime);
								mNowTimeTxt.setText(setTimes(nowTime));
								mTotalTimeTxt.setText(setTimes(totalTime));
							//	mMusicNameText.setText("正在播放      "+mServiceBinder.getMusicName());
							//	mMusicNameText.setMovementMethod(ScrollingMovementMethod.getInstance());
							}
						});
					}
					try { 
						Thread.sleep(1000);  //必须睡眠
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
//---------------00:00时间处理-----------------------
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
//-----------------------------------------------------------------
	private int seekBarProgress;   //解决拖动进度条卡顿问题，记录进度
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		/*if(fromUser == true){  // 拖动进度条时会卡顿
			mServiceBinder.setMediaPlayer(progress);
		}*/
		seekBarProgress = progress;
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mServiceBinder.setMediaPlayer(seekBarProgress);  //停止拖动时才回传值就可以解决卡顿问题
	}
	
	@Override
	protected void onDestroy() {   //销毁activity时解绑
		super.onDestroy();
		if(mServiceConnection != null){
			flag = false;
			unbindService(mServiceConnection);  
			stopService(intent);
		}
	//	mHandler.getLooper().quit();  //摧毁时将handler退出looper循环
	}
	
	//----------------广播------------------------------------
	public void registerReceiver(){   //注册广播方法
		MyReceiver receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter("mp3_main.MainActivity");
		registerReceiver(receiver, filter);
	}
	
	
	/*定义广播*/
	public class MyReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			if(mServiceBinder != null){
			//	Toast.makeText(context, "-------------", Toast.LENGTH_SHORT).show();
				mMusicNameText.setText("正在播放      "+mServiceBinder.getMusicName());
			}
		}
	}
}
