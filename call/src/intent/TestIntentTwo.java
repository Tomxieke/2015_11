package intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.call.R;

public class TestIntentTwo extends Activity {
	Intent mIntent;
	TextView mTextview;
	int mType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_two);
		mTextview = (TextView) findViewById(R.id.intent_two_textview);
		mIntent = getIntent();   //得到intent
		mType = mIntent.getIntExtra("TYPE", 1);   //  得到启动的类型信息
		setText(mIntent);
	}
	
	public void setText(Intent intent){
		switch(mType){
		case 1:
			mTextview.setText(intent.getStringExtra("MESSAGE"));
		break;
		case 2:
			mTextview.setText(intent.getStringExtra("MESSAGE"));
		break;
		}
	}
	
	public void onReturnResult(View v){
		if(mType == 1){   //说明是直接启动方式。返回对应的提示信息
			mIntent.putExtra("TYPE", 1);
			mIntent.putExtra("RESULT", "直接启动方式成功");
			setResult(2, mIntent);
			finish();
		}
		if(mType == 2){   //说明是隐式启动方式。返回对应的提示信息
			mIntent.putExtra("TYPE", 2);
			mIntent.putExtra("RESULT", "隐式启动方式成功");
			setResult(3, mIntent);
			finish();
		}
		
	}
}
