package com.example.call;

/**
 * 进一步简化一下测试代码
 * 在封装一层
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.ExpandableListActivity;
import wiaget.ButtonActivity;
import wiaget.CheckBoxStudy;
import wiaget.ImageViewActivity;
import wiaget.LogCat;
import wiaget.RadioButtonActivity;
import wiaget.SpinnerActivity;
import wiaget.TextViewActivity;
import wiaget.TextViewApiStudyActivity;
import wiaget.ToggleButtonSwitchActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class WiagetMainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		List<Map<String, Object>> data = this.getData(); // 调用方法得到资源文件
		setListAdapter(new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_1, // 实列化适配器
				new String[] { "title" }, new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() { // 封装添加资源方法
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(); // 用来保存数据源
		addItem(data, "学习textveiw", TextViewActivity.class);
		addItem(data, "学习API中的textveiw属性", TextViewApiStudyActivity.class);
		addItem(data, "控件Button的学习", ButtonActivity.class);
		addItem(data, "ImageView学习", ImageViewActivity.class);
		addItem(data, "Spinner学习", SpinnerActivity.class);
		addItem(data, "RadioButton单选框学习", RadioButtonActivity.class);
		addItem(data, "CheckBox多选框学习", CheckBoxStudy.class);
		addItem(data, "ToggleButtonSwitch开关学习", ToggleButtonSwitchActivity.class);
		addItem(data, "LogCat日志学习", LogCat.class);
		addItem(data, "ExpandableListView学习", ExpandableListActivity.class);
		return data;
	}

	protected void addItem(List<Map<String, Object>> data, String name,
			Class<?> c) { // 封装Intent
		addItem(data, name, new Intent(this, c));
	}

	protected void addItem(List<Map<String, Object>> data, String name,
			Intent intent) { // 向数据源中添加内容的方法
		Map<String, Object> temp = new HashMap<String, Object>(); // 实列化集合对象
		temp.put("title", name); // 向集合中添加元素
		temp.put("intent", intent);
		data.add(temp); // 向数据源中添加元素
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) l
				.getItemAtPosition(position);
		Intent intent = (Intent) map.get("intent");
		startActivity(intent);

	}
}
