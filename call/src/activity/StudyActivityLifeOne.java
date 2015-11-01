package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.call.R;

public class StudyActivityLifeOne extends Activity {
	private Button mActivityLifeBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("life", "StudyActivityLifeOne----->onCreate()");
		setContentView(R.layout.study_activity_lifeone);
		
		mActivityLifeBtn = (Button) findViewById(R.id.activity_life_button1);
		mActivityLifeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(StudyActivityLifeOne.this,StudyActivityLifeTwo.class)); //用Intent方式跳转Activity
			}
		});
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("life", "StudyActivityLifeOne----->onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("life", "StudyActivityLifeOne----->onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.w("life", "StudyActivityLifeOne----->onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.e("life", "StudyActivityLifeOne----->onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("life", "StudyActivityLifeOne----->onDestroy()");
	}
	
}
