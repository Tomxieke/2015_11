package adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.call.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyAdapterActivity extends Activity {
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_sempleadapter);
		
		mListView = (ListView) findViewById(R.id.adapter_simple_listview); //实例化listview
		
		ArrayList<HashMap<String, Object>>  listData = new ArrayList<HashMap<String,Object>>();  //  准备数据源
		setData(listData);   //设置数据源
		
		MyBaseAdapter adapter = new MyBaseAdapter(listData, this);   //实例化自定义的adapter
		mListView.setAdapter(adapter);
	}
	
	protected void setData(ArrayList data){
		
		HashMap<String, Object> map = new HashMap<String, Object>(); //准备数据源的hashmap
		map.put("icon", R.drawable.img_person);
		map.put("title", "火腿鸡肠");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_person);
		map.put("title", "香辣鸡肠");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_person);
		map.put("title", "回味无穷的驴蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_smile);
		map.put("title", "回味无穷的驴蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_buetyful);
		map.put("title", "回味无穷蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.weixin_icon2);
		map.put("title", "无穷蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.weixin_icon2);
		map.put("title", "自定义无穷蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		data.add(map);  //向数据源中添加元素
	}

}
