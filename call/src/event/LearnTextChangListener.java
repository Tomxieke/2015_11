package event;
/**
 * 文本框输入监听测试
 */
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.call.R;

public class LearnTextChangListener extends Activity {
	private EditText mTextChangeListenerBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_textchangelistener);
		
		mTextChangeListenerBtn = (EditText) findViewById(R.id.learn_textChangeListener_edit);
		
		mTextChangeListenerBtn.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {  //输入时调用的方法
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {															//输入之间调用的方法
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {	//输入后调用的方法
				
			}
		});
	}
}
