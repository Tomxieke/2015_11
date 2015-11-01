package com.example.call;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

public class StartActivity extends Activity {
	private Handler mHandler = new Handler();  //此处没传参数，默认与主线程关联
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_layout);
		
		startActivity();
	}
	
	
	public void looperHandler(){
		Looper mainLooper = getMainLooper(); //得到主线程的Looper  是一个可循环从消息队列中取出消息并分发给Hander处理的类对象
		Handler handler = new Handler(mainLooper,new Callback() {  //new Callback()   该Handler对应的回调方法
			
			@Override
			public boolean handleMessage(Message msg) {
				//处理消息
				
				return false;
			}
		});
		
		Message msg = Message.obtain();  //消息池里取消息，没有则new一个
		msg.what = 1;  //message的唯一标识。
		msg.obj = "dijhg";  //消息内容
		
		handler.sendMessage(msg);
	}
	
	/**
	 * 通过Handler方式停留两秒后进入主界面
	 */
	public void startActivity(){
		mHandler.postDelayed(new Runnable() {   // 这个方法其实是在主线程中的到消息然后执行的。
			
			@Override
			public void run() {
				startActivity(new Intent(StartActivity.this,MainActivity.class));
				finish();
			}
		}, 2000);
	}
}
