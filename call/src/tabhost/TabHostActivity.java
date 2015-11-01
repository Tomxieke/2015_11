package tabhost;

import studytest.AdapterHomeworkActivity;
import studytest.ImageInTextActivity;
import ui_adapter_wiaget.GridViewActivty;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.call.R;

public class TabHostActivity extends TabActivity implements OnCheckedChangeListener{
	
	private RadioGroup mRadioGroup;
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //设置标题隐藏
		setContentView(R.layout.tab_main_layout);
		mRadioGroup = (RadioGroup) findViewById(R.id.tab_radioGroup);
		
		mTabHost = getTabHost(); // 得到对应布局的TabHost

		TabSpec tabSpec1 = mTabHost.newTabSpec("tab1"); // 实例化TabSpec 也就是
														// tabHost的一项
		// tabSpec1.setIndicator("首页"); //设置显示的文本
		tabSpec1.setIndicator(setTabSpec1("首页")); // setIndicator()重载方法，可以以view为参数来自定义显示效果
		tabSpec1.setContent(new Intent(this, ImageInTextActivity.class)); // 设置要跳转到的activity

		TabSpec tabSpec2 = mTabHost.newTabSpec("tab2"); // 实例化TabSpec 也就是
														// tabHost的一项
		// tabSpec2.setIndicator("美食"); //设置显示的文本
		tabSpec2.setIndicator(setTabSpec2("Home")); // setIndicator()重载方法，可以以view为参数来自定义显示效果
		tabSpec2.setContent(new Intent(this, AdapterHomeworkActivity.class)); // 设置要跳转到的activity

		TabSpec tabSpec3 = mTabHost.newTabSpec("tab3"); // 实例化TabSpec 也就是
														// tabHost的一项
		tabSpec3.setIndicator("地点"); // 设置显示的文本
		tabSpec3.setContent(new Intent(this, GridViewActivty.class)); // 设置要跳转到的activity

		TabSpec tabSpec4 = mTabHost.newTabSpec("tab4"); // 实例化TabSpec 也就是
														// tabHost的一项
		tabSpec4.setIndicator("更多"); // 设置显示的文本
		tabSpec4.setContent(new Intent(this, GridViewActivty.class)); // 设置要跳转到的activity

		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		// tabHost.addView(setTabSpec1("VIP"), 1); // 这个应该是 设置二级子菜单
		mTabHost.addTab(tabSpec3);
		mTabHost.addTab(tabSpec4);
		mRadioGroup.setOnCheckedChangeListener(this);   //mRadioGroup添加监听启动对应的页卡项
	}
	
	public View setTabSpec1(String txt) {
		View v = LayoutInflater.from(this).inflate(R.layout.tab_item1_layout,
				null);
		TextView textView = (TextView) v.findViewById(R.id.tab_itemOne_txt); // 得到txtview控件
		textView.setText(txt);
		return v;
	}

	public View setTabSpec2(String txt) {
		View v = LayoutInflater.from(this).inflate(R.layout.tab_item2_layout,
				null);
		TextView textView = (TextView) v.findViewById(R.id.tab_itemTwo_txt); // 得到txtview控件
		textView.setText(txt);
		return v;
	}

	@Override
	/**
	 * 通过RadioGroup 中的RadioButotn关联页卡，同时启动页卡项  。。所有页卡项也可以用textView imageView等控件通过同样的方法来设置
	 */
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
		case R.id.tab_radio_one_radioBtn:
	//		mTabHost.setCurrentTabByTag("tab1");  //通过初始化页卡设置的tag 来启动该页卡
			mTabHost.setCurrentTab(0);  //  通过对应位置启动页卡
			break;
		case R.id.tab_radio_two_radioBtn:
			mTabHost.setCurrentTabByTag("tab2");
			break;
		case R.id.tab_radio_three_radioBtn:
			mTabHost.setCurrentTab(2);
			break;
		}
		
	}


}
