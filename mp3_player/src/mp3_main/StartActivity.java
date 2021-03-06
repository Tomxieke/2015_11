package mp3_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import com.example.mp3_player.R;

public class StartActivity extends Activity {
	private ImageView mImg;
	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_activity_layout);
		mImg = (ImageView) findViewById(R.id.start_img);
		startMainActivity();
	}
	
	public void startMainActivity(){
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mImg.setImageResource(R.drawable.guide_bg_3);
				startActivity(new Intent(StartActivity.this,MainActivity.class));
				finish();
			}
		}, 2000);
	}
	
}
