package intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.call.R;

public class TestIntentOne extends Activity {
	TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_one);
		mTextView = (TextView) findViewById(R.id.intent_button1);
	}
	
	public void onDirectStartTwo(View v){  //直接启动activity
	//  Intent intent = new Intent(this,TestIntentTwo.class);  跟下面的三句话是一样的。
		Intent intent = new Intent();
		intent.putExtra("TYPE", 1);
		intent.putExtra("MESSAGE", "直接启动方式");
		ComponentName component = new ComponentName(this,TestIntentTwo.class);  //  设置主键
		intent.setComponent(component);
		int requestCode = 1;
		startActivityForResult(intent, requestCode);
	//	startActivity(intent);
	}
	
	public void onHideStartTwo(View v){  //隐式启动Activity  通过设置的category（类别），type(数据类型)，component()，extras(附加信息)来识别activity
									//从而启动它。与AndroidManifest中设置的属性一一对应。
		Intent intent = new Intent();
		intent.putExtra("TYPE", 2);
		intent.putExtra("MESSAGE", "隐式启动方式");
		intent.setAction("IntentActivityOne");  //设置动作来识别activity  在AndroidManifest中two中设置intent-filter(过滤器)并设置对应action	
		int requestCode = 1;									//Action中有很多android自带的常量。设置不同的动作可以实现不同的功能
		startActivityForResult(intent, requestCode);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		int type = data.getIntExtra("TYPE", 1);   //得到返回值的提示类型
		switch(type){
		case 1:
			mTextView.setText(data.getStringExtra("RESULT"));
		break;	
		case 2:
			mTextView.setText(data.getStringExtra("RESULT"));
		break;	
		}
		
	}
	
	public void onCallPhone(View v){  //通过URL(数据类型)来调用系统的拨打号码的Activity
		Intent intent = new Intent();
		Uri data = Uri.parse("tel:18685149643");  //创建Uri的数据类型
		intent.setAction(Intent.ACTION_DIAL);   //启动系统拨号的Activity
		intent.setData(data);    //加载数据类型
		startActivity(intent);   		
	}
	
	public void onSendMassage(View v){   //调用发送短信的程序
		Uri uri = Uri.parse("smsto:123456789");
		Intent intent = new Intent(Intent.ACTION_VIEW ,uri);  //直接启动方式  可以用下面三句代码代替
		
	//	Intent intent = new Intent();
	//	intent.setAction(Intent.ACTION_VIEW);
	//	intent.setData(uri);
		startActivity(intent);
	}
	
	
	
	
}
