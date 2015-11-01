package study_handler;

/**
 * Handler 实现主线程（UI线程）与子线程  或者说是线程与线程之间的相互通信
 */
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class HandlerActivity extends Activity {
	private Button mButton_main, mBtn_o2o;
	private TextView mTxt;
	MyLooperThread mylooperthread;
	private int count = 0;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_layout);
		mButton_main = (Button) findViewById(R.id.handler_btn_one);
		mBtn_o2o = (Button) findViewById(R.id.handler_btn_two);
		mTxt = (TextView) findViewById(R.id.handler_textview);

		mylooperthread = new MyLooperThread();
		mylooperthread.start();

		mButton_main.setOnClickListener(new OnClickListener() { // 点击按钮向子线程中发送消息
					@Override
					public void onClick(View v) {

						Message msg = Message.obtain();
						msg.obj = "子线程handler传来的消息" + count;
						count++;
						mylooperthread.handler.sendMessage(msg); // 通过子线程的handler传递消息
					}
				});

		mBtn_o2o.setOnClickListener(new OnClickListener() { // 点击向子线程中发送信息
															// 再由子线程返回给主线程处理
			@Override
			public void onClick(View v) {
				Message msg = Message.obtain();
				msg.obj = "主发给子--" + count;
				mylooperthread.handler2.sendMessage(msg); // 用关联子线程的handler向子线程发送形象
			}
		});

		mHandler = new Handler(getMainLooper(), new Callback() { // 主线程handler处理主线程MQ中的消息
					@Override
					public boolean handleMessage(Message msg) { // 处理消息
						mTxt.setText((String) msg.obj);
						Toast.makeText(HandlerActivity.this, "32334" + msg.obj,
								Toast.LENGTH_SHORT).show();
						return true;
					}
				});

	}

	/*
	 * d点击按钮发送消息给子线程。并在子线程中设置textview的值
	 */
	public class MyLooperThread extends Thread { // 子线程Handler
		Handler handler; // 关联主线程的handler
		Handler handler2; // 关联子线程的handler

		@Override
		public void run() {
			Looper.prepare(); // 将该线程初始化为looper线程
			handler = new Handler(getMainLooper(), new Callback() { // getMainLooper()得到的是主线程的Looper,说明该Handler是与主线程绑定的
						// 可以从主线程中的MQ（队列）中取得消息并在下面的方法中进行处理
						@Override
						public boolean handleMessage(Message msg) {
							String strMsg = (String) msg.obj;
							mTxt.setText(strMsg); // 由于上面handler是与主线程关联的，所以可以操控textview
							return true;
						}
					});

			handler2 = new Handler(Looper.myLooper(), new Callback() { // Looper.myLooper()
																		// 关联的是子线程的looper，所以是子线程hander
						// 只能循环提取或分发子线程的MQ中的信息
						@Override
						public boolean handleMessage(Message msg) {
							String str = (String) msg.obj;
							Message msgMain = new Message();
							msgMain.obj = str + "子线程再到主线程";
							mHandler.sendMessage(msgMain); // 用关联主线程的Handler发送消息。则可以在主线程中获取消息
							return true;
						}
					});
			Looper.loop(); // 开始循环处理消息队列
		}
	}

	/**
	 * andriod系统自带的一个Looper线程  HandlerThread
	 */
	public void androidHandlerThread() {
		HandlerThread handlerThread = new HandlerThread("HandlerThread"); //看HandlerThread源码 。可找到Looper.prepare()和Looper.loop() 方法使该线程变为looper线程
		handlerThread.start();

		Looper mLooper = handlerThread.getLooper();

		Handler handler = new Handler(mLooper, new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// HandlerThread线程执行操作
				return true;
			}
		});

		handler.sendEmptyMessage(1);

		// 退出Looper循环，
		if (handlerThread != null) {
			handlerThread.quit();
		}

	}
}
