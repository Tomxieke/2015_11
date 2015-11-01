package ui_adapter_wiaget;
/**
 * 通过输入历史记录获取数据提示输入信息的AutoCompleteTextView
 */
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.call.R;

public class HistoryAutoCompleteTextViewActiviity extends Activity{
	private AutoCompleteTextView mAutoTxtv;
	private Button msearchBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocompletetv_historydata_layout);
		mAutoTxtv = (AutoCompleteTextView) findViewById(R.id.history_autoCompleteTv);
		inputAutoComplete("history", mAutoTxtv);
		msearchBtn = (Button) findViewById(R.id.history_btn);
		searchBtnListener();   //添加监听
	}
	
	public void searchBtnListener(){   // 按钮点击事件
		msearchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				savaHistory("history",mAutoTxtv);   //调用方法，保存数据
			}
		});
	}
	
	/**
	 * 输入时弹出历史记录提示框
	 * @param key 保存在SharedPreferences中的字段名
	 * @param auto 要操作的AutoCompleteTextView
	 */
	public void inputAutoComplete(String key,AutoCompleteTextView auto){
		SharedPreferences sp  = getSharedPreferences("historyData",0); //这里的参数一要与下面的一一对应
		String shareData = sp.getString(key, ""); //此处的nothing以及key也要与下面的一一对应，所有可以定义为全局变量
		String[] arrData = shareData.split(",");   //解析为数组作为数据源传给适配器
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrData);
		//只保留5条记录，有第六天记录时删除第一天记录
		int arrLegth = arrData.length;
		if(arrData.length > 5){
			String[] newArray = new String[5];
			System.arraycopy(arrData, arrData.length-5, newArray, 0, 5);
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, newArray);
		//	arrLegth = newArray.length;
		}
		
		auto.setAdapter(adapter);  //将适配器设置给输入提示框
		auto.setThreshold(1);
		auto.setCompletionHint("最近的" + arrLegth + "条记录");  
		auto.setOnFocusChangeListener(new OnFocusChangeListener() {  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                AutoCompleteTextView view = (AutoCompleteTextView) v;  
                if (hasFocus) {  
                        view.showDropDown();  
                }  
            }  
        });
		
	}
	
	/**
	 * 保存输入的记录
	 * @param key  保存在SharedPreferences中的字段名
	 * @param auto  要操作的AutoCompleteTextView
	 */
	public void savaHistory(String key,AutoCompleteTextView auto){
		String text = auto.getText().toString();   // 得到当前的输入记录
		SharedPreferences sp = getSharedPreferences("historyData", 0);   //SharedPreferences  保存数据的专用类，第一个参数为保存数据的名字。第二个参数为取得数据需要的key
		String historyData = sp.getString(key, "");  //取得之前保存的数据
		if( ! historyData.contains(text + ",")){   //如果不包括在保存数据中，则将其保存起来
			StringBuffer sb = new StringBuffer(historyData);  //转换为StringBuffer便于向里面加数据
			sb.append(text + ",");
			sp.edit().putString(key, sb.toString()).commit();   //加入新记录后再转换为SharedPreferences数据保存
		}
	}
}
