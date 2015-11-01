package event;
/**
 * 单顶模式是指再次启动栈顶的Activity时，不会再一次压入新的这个Activity而是直接弹出该栈顶的Activity.就这个区别于标准模式
 * 在AndroidManifest.xml中设置LaunchMode属性即可。
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.call.R;

public class LaunchMadeSingTop extends Activity {
	private Button mSingleTopBtn,mToSingleTaskBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launchmade_activity_singltop);
		
		dealButton();
	}
	
	
	public void dealButton(){
		mSingleTopBtn = (Button) findViewById(R.id.singleTop_activity_btn);
		mSingleTopBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LaunchMadeSingTop.this,LaunchMadeSingTop.class));  //单顶模式启动的Activity直接将栈顶的Activity显示
				Toast.makeText(LaunchMadeSingTop.this, "启动了当前Activity", Toast.LENGTH_SHORT).show();  //窗口提示框验证这个的语句被执行了
			}
		});
		
		mToSingleTaskBtn = (Button) findViewById(R.id.toSingleTask_btn);
		mToSingleTaskBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LaunchMadeSingTop.this,SingleTaskActivity.class));  //跳转到单任务模式Activity
			}
		});
		
	}
}
