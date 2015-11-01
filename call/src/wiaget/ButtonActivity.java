package wiaget;
/**
 * 对按钮控件的一些设置
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.call.R;

public class ButtonActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.midget_button);
		Button showFromButton = (Button)findViewById(R.id.showbutton);
		showFromButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ButtonActivity.this,ShowFromButtonActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	
}
