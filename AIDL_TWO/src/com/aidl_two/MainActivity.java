package com.aidl_two;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.aidl_one.AidlService;
import com.aidl_one.ShowTxt;


public class MainActivity extends Activity {
	private Button mBtn;
	public ShowTxt serviceBinder;
	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			serviceBinder = ShowTxt.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			if(serviceBinder != null){
				serviceBinder = null;
			}
		}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("a");
		bindService(intent, mServiceConnection, BIND_ABOVE_CLIENT);
        mBtn = (Button) findViewById(R.id.aidl_btn); 
        mBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Log.e("test", ""+serviceBinder);
					if(serviceBinder != null)
					serviceBinder.show("123");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(mServiceConnection != null){
    		unbindService(mServiceConnection);
    	}
    }

}
