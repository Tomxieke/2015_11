package com.aidl_service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class AIDLService extends Service {
	private Handler mHandler = new Handler();
	
	public  IName.Stub stubBinder = new IName.Stub() {
		
		@Override
		public void showName(String name) throws RemoteException {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(AIDLService.this, "aidl中的接口方法", Toast.LENGTH_SHORT).show();
				}
			});
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return stubBinder;
	}
	
}
