package com.example.call;

import wiaget.ButtonActivity;
import wiaget.TextViewActivity;
import layout.CallActivity;
import layout.RelativelayoutActivity;
import layout.TableLayoutActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LayoutActivity extends ListActivity {
	
	String[] arrayList = {"布局一","布局二","布局三","布局四","布局五","按钮控件学习"};		//定义需要设置的布局主题
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);   //实列化适配器
		setListAdapter(adapter);  
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch(position){
		case 0:  startActivity(new Intent(this,CallActivity.class));
			break;
		case 1:  startActivity(new Intent(this,RelativelayoutActivity.class));
			break;
		case 2:  startActivity(new Intent(this,TableLayoutActivity.class));
			break;
		case 3:  startActivity(new Intent(this,TextViewActivity.class));
			break;
		case 4:  startActivity(new Intent(this,LayoutsDemoActivity.class));
			break;
		case 5:  startActivity(new Intent(this,ButtonActivity.class));
			break;
		}
		
	}
}
