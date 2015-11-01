package adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class AdapterSimpleAdapter extends Activity implements OnItemClickListener{
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_sempleadapter);
		
		mListView = (ListView) findViewById(R.id.adapter_simple_listview); //实例化listview
		
		ArrayList<HashMap<String, Object>>  listData = new ArrayList<HashMap<String,Object>>();  //  准备数据源
		setData(listData);   //设置数据源
		
		// new SimpleAdapter(context, data, resource, from, to)    参数data为ArrayList数据源  resource为一个对应的子layout。
		//form为Hashmap对应的key的数组  。to为hashmap中vaule值被显示在相应控件上的控件id
		String[] from = {"icon","title","content",};
	//	int[] to = {R.id.adapter_simple_icon_imageview, R.id.adapter_simple_title_textview, R.id.adapter_simple_content_textview};//linearlayout实现的布局
	//	SimpleAdapter adapter = new SimpleAdapter(this, listData, R.layout.adapter_simple_item1_linearlayout, from, to);   //设置适配器
		int[] to = {R.id.adapter_relative_icon, R.id.adapter_relative_title, R.id.adapter_relative_content};//通过相对布局relativelayout实现的布局管理
		SimpleAdapter adapter = new SimpleAdapter(this, listData, R.layout.adapter_simple_item_relativelayout, from, to);   //设置适配器
		
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(this); //注册监听
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
	}

	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {    //实现OnItemClickListener接口来监听listview中的每一项，有三种
																						//方式找到对应的控件
	//	LinearLayout layout = (LinearLayout) view;   //得到的是最外层的layout
	//	TextView txt = (TextView) layout.findViewById(R.id.adapter_simple_title_textview); //1、通过最外层调用findViewById()找到对应的子控件
	//	Toast.makeText(AdapterSimpleAdapter.this,"父控件 findViewById" +txt.getText(), Toast.LENGTH_SHORT).show();
		
	//	LinearLayout son = (LinearLayout) layout.getChildAt(1);  //2、通过外层layout调用getChildAt(index)方法找到子控件
	//	TextView txt = (TextView) son.getChildAt(0);   //通过子控件调用getChildAt(index)方法得到在它下面的子控件
	//	Toast.makeText(AdapterSimpleAdapter.this,"父控件 getChildAt " +txt.getText(), Toast.LENGTH_SHORT).show();
		
		//3、 比较常用的一种方式。通过参数parent（就是数据源）来得到需要显示的内容
		SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter(); //通过参数parent得到适配器
		HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItem(position);  //通过适配器调用getItem(position)方法得到对应的hashmap值
		String title = (String) item.get("title");  //从hashmap中取对应数据
		Toast.makeText(AdapterSimpleAdapter.this,"通过adapter->hashmap->数据   " + title , Toast.LENGTH_SHORT).show();
		
	}


}
