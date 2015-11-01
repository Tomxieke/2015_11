package event;
/**
 * 启动模式之标准模式跳转到单实例模式。activity的LaunchMade为单实例模式时，启动该activity将会开辟新的栈，并且该栈只放该activity，不允许其他activity
 * 压入该栈。从标准模式跳转到单实例模式。在跳回标准模式。退出会见标准模式栈中的所有activity弹出后再调回单实例模式的activity。
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.call.R;

public class LaunchMadeSingleInstance extends Activity {
	private Button mTostandardBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.learn_launchmade_singleinstance);
		
		mTostandardBtn = (Button) findViewById(R.id.singleInstance_Btn);
		mTostandardBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LaunchMadeSingleInstance.this,LearnActivityLaunchMade.class));
			}
		});
	}
}
