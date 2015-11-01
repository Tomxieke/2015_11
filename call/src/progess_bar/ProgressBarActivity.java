package progess_bar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.example.call.R;

public class ProgressBarActivity extends Activity {
	private ProgressBar mProgressBar;
	private SeekBar mSeekBar,mSeekbar_runMan;
	private Button mStratBtn,mSeekStratBtn;
	private ImageView mSeekImg;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_bar_layout);
		mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
		mStratBtn = (Button) findViewById(R.id.progresbar_startbtn);
		mSeekBar = (SeekBar) findViewById(R.id.seekbar);
		mSeekStratBtn = (Button) findViewById(R.id.progresbar_seekBarStartbtn);
//-0-----------------Handler消息机制在主线程（UI线程）和子线程之间传递消息----------------------------
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what){  // msg.what  消息的唯一标识
				case 10:
					String str = (String) msg.obj;   // 取得消息内容
					Toast.makeText(ProgressBarActivity.this, str, Toast.LENGTH_SHORT).show();
					break;
				}  
			}
		};
		
		
		
		btnStartListener(mStratBtn,mProgressBar);
		StartSeekBarListener(mSeekStratBtn,mSeekBar);
		seekBarListener(mSeekBar);	
	//---------------小图标跟着进度条跑---------------------------	
		mSeekbar_runMan = (SeekBar) findViewById(R.id.seekbar_runningman);
		mSeekImg = (ImageView) findViewById(R.id.seekbar_run_btn);
		for(int i = 1;i<mSeekbar_runMan.getMax();i++){
			mSeekbar_runMan.setProgress(i);
			mSeekImg.setX(mSeekbar_runMan.getProgress()*6);
		}
	//------------------------------------------------------------------
	}
	
	public void seekBarListener(SeekBar seekBar){  //seekBar监听
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			//	Toast.makeText(ProgressBarActivity.this, "----onStopTrackingTouch-----", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			//	Toast.makeText(ProgressBarActivity.this, "----onStartTrackingTouch-----", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
			//	Toast.makeText(ProgressBarActivity.this, "--onProgressChanged--" + progress + fromUser, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void StartSeekBarListener(Button button,final SeekBar seekBar){
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						for (int i = 1; i <= seekBar.getMax(); i++) {
							seekBar.setProgress(i);
							seekBar.setSecondaryProgress(i+10);
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
			
						mHandler.post(new Runnable() {   // Handler 消息机制发送的时线程 该线程将在主线程中执行
							@Override
							public void run() {
								Toast.makeText(ProgressBarActivity.this, "seekbar加载完成", Toast.LENGTH_SHORT).show();
							}
						});                                                                                                                                                                                                                                                                                                                                                                                                                                                        
					}
					
				}).start();
			}
		});
	}
	public void btnStartListener(Button button,final ProgressBar bar){   //  点击按钮开始加载进度条
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {  // 用子线程启动进度条开始加载
					
					@Override
					public void run() {
						for(int i = 1;i <= bar.getMax();i++){
							/*if(i%2 == 0){
								bar.setBackgroundResource(R.drawable.refreshing_image_01);
							}else{
								bar.setBackgroundResource(R.drawable.refreshing_image_02);
							}*/
						//	mSeekImg.setX(bar.getProgress());   //只有在主线程中设置哟。
							bar.setProgress(i);
							bar.setSecondaryProgress(i*2); // 二级缓冲条
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
		//------------------------------0000000000000000000000------------------------				
		//			Message msg = new Message();  // 加载完成。用Hander消息机制通知主线程执行相应操作  
					Message msg = Message.obtain(); //在消息池里取，若有则直接用，若没有则new一个。上面一种方式每次都new一个
					msg.what = 10;  //该消息的唯一标识
					msg.obj = "加载完成";  //消息内容
					mHandler.sendMessage(msg);  //发送sendMessage消息给主线程
	//==================================================
			//			Toast.makeText(ProgressBarActivity.this, "加载完成", Toast.LENGTH_SHORT).show();//报错，不能再子线程中用Toast 只能在UI(主)线程中用
						Log.v("test", "加载完成");
					}
				}).start();
			}
		});
	}
}
