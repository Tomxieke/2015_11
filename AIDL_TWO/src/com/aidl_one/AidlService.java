package com.aidl_one;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.aidl_one.ShowTxt.Stub;

public class AidlService extends Service {
	public Handler mHandler = new Handler();
	public SeriviceBinder serviceBinder = new SeriviceBinder();
	
	public class SeriviceBinder extends Stub{   //服务中定义一个类继承Stub.并实现我们定义的接口方法

		@Override
		public void show(String string) throws RemoteException {
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(AidlService.this, "这里是实现adil的接口方法，执行操作", Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return serviceBinder;
	}

}
