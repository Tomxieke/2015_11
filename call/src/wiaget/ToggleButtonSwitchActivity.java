package wiaget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.call.R;

public class ToggleButtonSwitchActivity extends Activity {
	private Switch mSwitch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_togglebutton_switch);	
		mSwitch = (Switch) findViewById(R.id.study_switch1);  //得到控件
		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {  //设置状态改变事件处理
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Toast.makeText(ToggleButtonSwitchActivity.this, "滑动打开", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(ToggleButtonSwitchActivity.this, "滑动关闭", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
	
	public void onToggleButton(View v){
		boolean isChecked = ((ToggleButton) v).isChecked();  //向下转型并调用方法
		if(isChecked){
			Toast.makeText(this, "ToggleButton已经打开", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "ToggleButton已经关闭", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onSwitch(View v){  //这种处理只是点击事件的处理。由于Switch还可以滑动来改变状态。当时滑动改变状态时将不执行以下的操作。因此要给其
									//添加滑动状态改变事件处理    如上面的匿名内部类  但是在运行过程中发现滑动打开正常，而点击打开的时候也执行了滑动打开
									//的代码。并且先于点击代码执行。说明改变状态（滑动打开）事件先处理。这关系到后面的事件分发机制。在这里不做研究
									//将该方法注释掉并在xml中不设置onCleck()属性就不会出现执行两次的情况了。以后注意
		boolean isChecked = ((Switch) v).isChecked();  //向下转型并调用方法
		if(isChecked){
			Toast.makeText(this, "Switch已经打开", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "Switch已经关闭", Toast.LENGTH_SHORT).show();
		}
	}
	
	
}
