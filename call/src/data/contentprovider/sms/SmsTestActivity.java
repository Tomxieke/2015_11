package data.contentprovider.sms;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.call.R;

public class SmsTestActivity extends Activity {
	private Button mRegiestBtn;
	private TextView mShowMsgTxt;
	// handler回调，处理监听得到的消息
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 110:
				HashMap<String,String>  map = (HashMap<String, String>) msg.obj;
				mShowMsgTxt.setText(map.get("address")+"    "+ map.get("body") + "    "  +map.get("date") + "    " + map.get("person_id"));
				//下面就可以删除这条短信了
				getContentResolver().delete(ToolClass.SMS.CONTENT_URI, "address = ?", new String[]{"110"});
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_contentprovider_layout);
		initView();
		
		
		
		
	}
	
	public void initView(){
		mRegiestBtn = (Button) findViewById(R.id.sms_btn);
		mShowMsgTxt = (TextView) findViewById(R.id.sms_show_msg_txt);
		
		mRegiestBtn.setOnClickListener(new OnClickListener() {   //点击按钮就注册监听短消息
			@Override
			public void onClick(View v) {
				SmsContentObserver sms = new SmsContentObserver(SmsTestActivity.this,mHandler);
				getContentResolver().registerContentObserver(ToolClass.SMS.CONTENT_URI, true, sms);  // 注册（添加）观察者
				mShowMsgTxt.setText("开始监听");
			}
		});
		
		
	}
}
