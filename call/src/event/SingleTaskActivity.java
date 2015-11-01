package event;
/**
 * 单任务启动模式在启动新的Activity时，会扫描栈中是否有该Activity,如果有则直接跳转到该Activity.并将栈中压在它之上的所有Activity销毁掉
 * 该类直接跳转到list界面并销毁掉之间的单顶模式Activity(LaunchMadeSingTop)
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.call.MainActivity;
import com.example.call.R;

public class SingleTaskActivity extends Activity {
	private Button mToMainActivityBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchmado_singletask);
		ButtonListener();
	}
	
	public void ButtonListener(){
		mToMainActivityBtn = (Button) findViewById(R.id.singleTask_toMainActivity_btn);
		mToMainActivityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(SingleTaskActivity.this,MainActivity.class));   //直接跳转到MainActivity.并将本类中的Activity与mianActivity
			}													//之间的单顶模式activity消费掉。需要设置MainActivity为单任务启动模式
		});
	}
}
