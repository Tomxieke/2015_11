package event;
/**
 * 事件处理的四种方式 1、匿名内部类处理方式
 * 2、内部类处理方式
 * 3、实现OnClickListener方式
 * 4、android方式
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.call.R;

public class ButtonOnClickEvent extends Activity implements OnClickListener{
	private TextView mShowMessageTxt;
	private Button mAnonymityBtn;		//匿名内部类的button
	private Button mInnerclassBtn;		//内部类的button
	private Button mImplementBtn;		//实现OnClickListener接口button
	private Button mAndroidBtn;			//android方式的button
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_study_button);
		mShowMessageTxt = (TextView) findViewById(R.id.event_study_textview1);
		//匿名内部类的方式实现事件处理
		mAnonymityBtn = (Button) findViewById(R.id.event_study_innerclass_button1);
		mAnonymityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//setText()方法中参数直接字符串传参数，也可以引用资源传参数
				mShowMessageTxt.setText("匿名内部内实现的事件处理方式");
			}
		});
		
		//  内部类的方式实现点击事件处理
		mInnerclassBtn = (Button) findViewById(R.id.event_study_innerclass_button2);
		mInnerclassBtn.setOnClickListener(new MyOnClickListener());
		
		//实现OnClickListener接口处理点击事件
		mImplementBtn = (Button) findViewById(R.id.event_study_implments_button);
		mImplementBtn.setOnClickListener(this);
		
	}
	
	
	
	//  内部类实现点击事件处理
	class MyOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//setText()方法参数引用本地资源的一种方式。还有另一种哟
			String message = getResources().getString(R.string.event_text1);
			mShowMessageTxt.setText(message);
		}
	}
	
	
	
	//  实现OnClickListener接口处理点击事件的OnClick方法
	@Override
	public void onClick(View v) {
		//setText()方法参数不同过资源直接R.调用
		mShowMessageTxt.setText(R.string.event_text2);	
	}
	
	// android方式处理点击事件  在xml文件定义OnClick属性并调用该方法
	public void myOnClick(View v){
		mShowMessageTxt.setText("android方式处理点击事件");
	}
}
