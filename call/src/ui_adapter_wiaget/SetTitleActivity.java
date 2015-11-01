package ui_adapter_wiaget;
/**
 * 将默认Title隐藏，在xml布局中设置自定义Title
 */
import com.example.call.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SetTitleActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 将默认Title隐藏  需要放在setContentView()方法前面
		setContentView(R.layout.set_title_study_layout);
	}
}
