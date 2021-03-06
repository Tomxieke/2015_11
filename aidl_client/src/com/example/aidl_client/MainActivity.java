package com.example.aidl_client;

import com.aidl_service.IName;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	private Button mBtn;
	private IName INameBinder;
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			if(INameBinder != null)
				INameBinder = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			INameBinder = IName.Stub.asInterface(service);   //转化为对应aidl接口对象
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.aidl_btn);
        Intent intent = new Intent("com.aidl_service.AIDLService");
        bindService(intent, conn, BIND_AUTO_CREATE);
        mBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					INameBinder.showName("123");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(conn != null){
    		unbindService(conn);
    	}
    }


}
