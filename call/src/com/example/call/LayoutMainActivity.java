package com.example.call;
/**
 * 进一步简化一下测试代码
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wiaget.ButtonActivity;
import wiaget.TextViewActivity;

import layout.CallActivity;
import layout.RelativelayoutActivity;
import layout.TableLayoutActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LayoutMainActivity extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();  //用来保存数据源
		addItem(data, "电话拨号", new Intent(this,CallActivity.class));  //调用方法，添加第一个元素
		addItem(data, "相对布局", new Intent(this,RelativelayoutActivity.class));
		addItem(data, "表格布局", new Intent(this,TableLayoutActivity.class));
		addItem(data, "java代码实现的布局", new Intent(this,LayoutsDemoActivity.class));
		setListAdapter(new SimpleAdapter(this, data, android.R.layout.simple_list_item_1,  //实列化适配器
				new String[] {"title"}, new int[] {android.R.id.text1}));
	}
	
	protected void addItem(List<Map<String,Object>> data,String name,Intent intent){  //向数据源中添加内容的方法
		Map<String,Object> temp = new HashMap<String,Object>();   //实列化集合对象
		temp.put("title", name);		//向集合中添加元素
		temp.put("intent", intent);
		data.add(temp);  //向数据源中添加元素
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String,Object>)l.getItemAtPosition(position);
		Intent intent = (Intent)map.get("intent");
		startActivity(intent);
		
	}
}
