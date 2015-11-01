package event;

/**
 * 测试状态保存方法调用，当程序意外终止时。可以保存
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.call.R;

public class LearnSaveStateActivity extends Activity {
	private Button mSendBtn;
	private EditText  mMessageEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {  //参数savedInstanceState 就是上一个Activity传来的数据
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_savestate_activity);
		mMessageEdit = (EditText) findViewById(R.id.saveState_edit);
		mSendBtn = (Button) findViewById(R.id.saveState_sendBtn);
		
		
		if(savedInstanceState != null){   //savedInstanceState  就是保存数据的集合吧。暂时这么理解以后再深入理解  这些代码也是可以放在OnCreate()方法中的。
			Log.w("saveState", "-------------------->onCreate------saveState()");
			String message = savedInstanceState.getString("message");
			mMessageEdit.setText(message+"这是恢复后的显示信息");
		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {		//该方法是在Activity意外停止时执行的方法。可以用来保存我们输入框中的重要信息
															//在恢复Activity是将信息恢复
		super.onSaveInstanceState(outState);
		Log.v("saveState", "------------------>onSaveInstanceState()");
		String message = mMessageEdit.getText().toString();  //得到EditText中输入的信息
		
		outState.putString("message", message);  //outState是android中用来保存信息的一个类。以后会学到。
		
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {  //该方法是意外停止的activity终止后恢复时系统调用的方法。当然也可以将恢复数据的代码
		super.onRestoreInstanceState(savedInstanceState);				//放在OnCreate()方法里面。因为要呈现Activity首先调用的就是OnCreate()方法。
		Log.e("saveState", "-------------->onRestoreInstanceState()");  //在控制台上打印信息以便验证他是否执行
		
		if(savedInstanceState != null){   //savedInstanceState  就是保存数据的集合吧。暂时这么理解以后再深入理解  这些代码也是可以放在OnCreate()方法中的。
			String message = savedInstanceState.getString("message");
			mMessageEdit.setText(message+"这是恢复后的显示信息");
		}
	}
	
}
