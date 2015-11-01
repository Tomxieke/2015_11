package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.call.R;

public class StudyActivityLifeTwo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_activity_lifetwo);
		
	}
	
	public void exitListener(View V){
		//startActivity(new Intent(StudyActivityLifeTwo.this,StudyActivityLifeOne.class));  //通过Intent对象让ActivityOne启动。但是启动模式是默认的
		this.finish();																	//标准模式，所以在又要开辟栈空间。将	ActivityOne放在栈顶，此时栈中已经
																						//保存了两个ActivityOne。所以可以用finsh()方法将StudyActivityLifeTwo
																					//推出栈。让ActivityOne在栈顶。
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("life", "StudyActivityLifeTwo----->onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("life", "StudyActivityLifeTwo----->onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.w("life", "StudyActivityLifeTwo----->onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.e("life", "StudyActivityLifeTwo----->onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("life", "StudyActivityLifeTwo----->onDestroy()");
	}


}
