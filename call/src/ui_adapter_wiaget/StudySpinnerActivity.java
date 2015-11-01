package ui_adapter_wiaget;

import java.util.ArrayList;

import tabhost.PopWindowsActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.call.R;

public class StudySpinnerActivity extends Activity {
	private Spinner spinner;
	PopupWindow mPopupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_layout);
		
		spinner = (Spinner) findViewById(R.id.adapter_spinner);   //实例化spinner
		
		String[] data = {"小强","张三","李四","王五"};  //数据源
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_layout, data);  //适配器
		
		spinner.setAdapter(adapter);
	//	spinner.setSelection(3); // 默认选中第一项 但要执行OnItemSelectedListener事件
		spinner.setSelection(3, true);  //  设置进入时默认显示第几项，后面的boolean参数决定第一次进入时会不会执行OnItemSelectedListener事件
										//true 和false都不会执行不执行， 默认是执行的。
		
		mPopupWindow = new PopupWindow(getPopView(), 300, 600); // 实列化PopupWindow
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {  //当子列表被选中的监听事件

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {     //对应位置上选中时事件
				if (mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				} else {
					mPopupWindow.showAsDropDown(view); // 显示PopupWindow
				}
				
				Toast.makeText(StudySpinnerActivity.this, "setOnItemSelectedListener position" + position , Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {   //一个都没选中时的事件
				Toast.makeText(StudySpinnerActivity.this, "onNothingSelected position" + parent , Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	/**
	 * PopupWindow显示的View
	 * 
	 * @return
	 */
	public View getPopView() {
		View v = LayoutInflater.from(this).inflate(
				R.layout.popwindow_item_layout, null);
		ListView mListView = (ListView) v;
		String[] city = { "北京", "上海", "成都", "重庆" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, city);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Toast.makeText(StudySpinnerActivity.this,
							"1231414" + position, Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});

		return mListView;
	}
}
