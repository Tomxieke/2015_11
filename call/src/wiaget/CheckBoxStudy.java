package wiaget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.call.R;

public class CheckBoxStudy extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_checkbox);
	}
	
	
	public void checkBoxChick(View v){      //onChlick属性调用该方法完成时间处理
		CheckBox checkBox = (CheckBox) v;
		boolean isChecked = checkBox.isChecked();  //保存是否是选中状态
		switch (v.getId()) {
		case R.id.study_checkBox1:
			CheckBox checkBox1 = (CheckBox) findViewById(R.id.study_checkBox1);
			if(isChecked){
				Toast.makeText(this, "选中"+checkBox1.getText(), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "取消选中"+checkBox1.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.study_checkBox2:
			CheckBox checkBox2 = (CheckBox) findViewById(R.id.study_checkBox2);
			if(isChecked){																	//判断是否为选中状态
				Toast.makeText(this, "选中"+checkBox2.getText(), Toast.LENGTH_LONG).show(); //对话框显示提示信息
			}else{
				Toast.makeText(this, "取消选中"+checkBox2.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.study_checkBox3:
			CheckBox checkBox3 = (CheckBox) findViewById(R.id.study_checkBox3);
			if(isChecked){
				Toast.makeText(this, "选中"+checkBox3.getText(), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "取消选中"+checkBox3.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		
		}
	}
	
	public void mycheckBoxChick(View v){      //onChlick属性调用该方法完成时间处理
		CheckBox checkBox = (CheckBox) v;
		boolean isChecked = checkBox.isChecked();  //保存是否是选中状态
		switch (v.getId()) {
		case R.id.study_mycheckBox1:
			CheckBox checkBox1 = (CheckBox) findViewById(R.id.study_mycheckBox1);
			if(isChecked){
				Toast.makeText(this, "选中"+checkBox1.getText(), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "取消选中"+checkBox1.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.study_mycheckBox2:
			CheckBox checkBox2 = (CheckBox) findViewById(R.id.study_mycheckBox2);
			if(isChecked){																	//判断是否为选中状态
				Toast.makeText(this, "选中"+checkBox2.getText(), Toast.LENGTH_LONG).show(); //对话框显示提示信息
			}else{
				Toast.makeText(this, "取消选中"+checkBox2.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.study_mycheckBox3:
			CheckBox checkBox3 = (CheckBox) findViewById(R.id.study_mycheckBox3);
			if(isChecked){
				Toast.makeText(this, "选中"+checkBox3.getText(), Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "取消选中"+checkBox3.getText(), Toast.LENGTH_LONG).show();
			}
			break;
		
		}
	}
}
