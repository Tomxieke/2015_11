package adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class ListArrayAdapter extends Activity {
	private ListView mListView;
	String[] str = {"A","B","C","D","E","F"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_arrayitem_listview);
		dealChick();
		
	}
	
	public void dealChick(){
		mListView = (ListView) findViewById(R.id.listAdapterlistview);
		//new ArrayAdapter<T>(context, textViewResourceId, objects); 参数分别为context对象  textViewResourceId  TextView的xml文件   objects数据源
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.adapter_arrayitem_textview1, str);   //自定义的layout   
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, str); //调用系统的layout
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {  //为listView添加监听

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				//view 和 position决定ListView上对应的TextView
				switch(position){
				case 0:
					Uri data = Uri.parse("http://baidu.com"); //  Uri 数据类型
					Intent intent = new Intent(Intent.ACTION_VIEW, data);  // 用action确定启动方式
					startActivity(intent);
					break;
				case 1:
					TextView textView = (TextView) view;
					Toast.makeText(ListArrayAdapter.this, "position" + position + ","+textView.getText(), Toast.LENGTH_SHORT).show();	
				case 2:
					Uri uri = Uri.parse("tel:5554");
					Intent intent2 = new Intent(Intent.ACTION_DIAL,uri);
					startActivity(intent2);
					break;
				case 3:
					Uri date2 = Uri.parse("smsto:18685149643");
					Intent intent3 = new Intent(Intent.ACTION_SENDTO,date2);
					startActivity(intent3);
					break;
				}
			}
		});
		
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {  //长按监听事件 返回值boolean很有讲究。返回false则还回去执行OnItemClickListener
																	//点击事件。返回true则不会再去执行单击事件了
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
				Toast.makeText(ListArrayAdapter.this, "position" + position + ",卧槽。还可以这样", Toast.LENGTH_SHORT).show();
			//	return false;  //返回false会再去执行OnItemClickListener单击事件
				return true;  //返回true则不会再去执行单击事件
			}  
		});
		
		mListView.setOnItemSelectedListener(new OnItemSelectedListener() {  //选中状态的监听

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				Toast.makeText(ListArrayAdapter.this, "position" + position + ",选中状态---onItemSelected", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(ListArrayAdapter.this,  ",选中状态---onNothingSelected", Toast.LENGTH_SHORT).show();
			}  
			
		});
		
	}
}
