package application;
/**
 * 访问Application中的全局变量
 */

import android.app.Activity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.call.R;

public class ApplicationActivity extends Activity {
	private EditText mEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_activity_layout);
		mEditText = (EditText) findViewById(R.id.application_edittext);
		
		MyApplication app = (MyApplication) getApplication();   //  得到该应用的Application.
		String appString = app.strApp;  //取得该应用中Application中定义的全局变量
		
		mEditText.setText(appString);
		
	}
}
