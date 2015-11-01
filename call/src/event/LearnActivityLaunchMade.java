package event;
/**
 * 启动模式之标准模式，不管启动的Activity是否是同一个，无限制向栈顶压入Activity.调用finsh()方法时一个一个弹出
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.call.R;

public class LearnActivityLaunchMade extends Activity {
	private Button mLaunchMadeBtn,mTosingleInstanceBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_avtivitylaunchmade);
		mLaunchMadeBtn = (Button) findViewById(R.id.activity_launchMado_btn);
		
		mLaunchMadeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LearnActivityLaunchMade.this,LearnActivityLaunchMade.class));
			}
		});
		
		
		mTosingleInstanceBtn = (Button) findViewById(R.id.launchMade_istandardBtn);
		mTosingleInstanceBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LearnActivityLaunchMade.this,LaunchMadeSingleInstance.class));
				Toast.makeText(LearnActivityLaunchMade.this, "启动了单实例模式的Activity", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
