package wiaget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


import com.example.call.R;

public class RadioButtonActivity extends Activity {
	private RadioGroup mRadioGroup;
	private RadioButton mManRadioBtn;
	private RadioButton mWomanRadioBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.miaget_radiobutton_group);

		mRadioGroup = (RadioGroup) findViewById(R.id.study_myradiogroup); // 通过id得到实例

		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() { // 通过该方法来确定选中的是哪个radioButton
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) { // checkedId就是对应组件的ID
						switch (checkedId) {
						case R.id.study_radioButton_man:
							// 可以用好三种方式得到mManRadioBtn的实例化对象了。如下三种
							// mManRadioBtn = (RadioButton)
							// findViewById(checkedId); 通过id = checkedId 来得到实列化
							// mManRadioBtn = (RadioButton)
							// mRadioGroup.getChildAt(0);
							// 通过RadioGroup中的getChildAt()方法下标来得到实例化
							mManRadioBtn = (RadioButton) findViewById(R.id.study_radioButton_man); // 通过最原始的ID来实例化
							System.out.println("-------------->"
									+ mManRadioBtn.getText()); // 输出
							Toast.makeText(RadioButtonActivity.this, mManRadioBtn.getText(), Toast.LENGTH_SHORT).show();
							break;

						case R.id.study_radioButton_woman:
							mWomanRadioBtn = (RadioButton) findViewById(checkedId);
							System.out.println("-------------->"
									+ mManRadioBtn.getText());
							Toast.makeText(RadioButtonActivity.this, mWomanRadioBtn.getText(), Toast.LENGTH_SHORT).show();
							break;
						}
					}
				});

	}
}
