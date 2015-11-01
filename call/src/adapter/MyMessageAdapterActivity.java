package adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.call.R;

public class MyMessageAdapterActivity extends Activity {
	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_sempleadapter);
		
		mListview = (ListView) findViewById(R.id.adapter_simple_listview);  //实例化
		
		ArrayList<MyMessage> listData = getData();//  得到数据源
		
		MyMessageAdapter adapter = new MyMessageAdapter(listData,this);  //得到自定义的适配器
		
		mListview .setAdapter(adapter);
		
		
	}
	
	/**
	 * 内部类自定义的adapter
	 * @author scxh
	 *
	 */
	public class MyMessageAdapter extends BaseAdapter{   //内部类自定义Adapter
		ArrayList<MyMessage> data = new ArrayList<MyMessage>();  //实例化的数据源
		LayoutInflater layoutInflater;
		public MyMessageAdapter(ArrayList<MyMessage> data,Context context){
			this.data = data;
			layoutInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {   //返回数据个数
			return data.size();
		}

		@Override
		public Object getItem(int position) {   //返回一组数据
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {   //返回对应数据的id
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder(); // 这里特别注意 如果不实例化的话，有可能报空指针异常，最好是实列化
			View v;
			if(convertView == null){
				v = layoutInflater.inflate(R.layout.adapter_simple_item1_linearlayout, null);//解析子布局管理器
				holder.icon = (ImageView) v.findViewById(R.id.adapter_simple_icon_imageview);
				holder.title = (TextView) v.findViewById(R.id.adapter_simple_title_textview);
				holder.content = (TextView) v.findViewById(R.id.adapter_simple_content_textview);
				v.setTag(holder);  //  将子控件放如对象中并保存起来setTag().
			}else{
				v = convertView;
				holder = (ViewHolder) v.getTag();  // 将控件取出
			}
			
			MyMessage msg = (MyMessage) getItem(position);  // 取得ArrayList数据源中的每一项
			holder.icon.setImageResource(msg.getIcon());  // 设置每一个控件显示的类容
			holder.title.setText(msg.getTitle());  //设置title控件显示的类容
			holder.content.setText(msg.getContent());
			return v;
		}
		
		public class ViewHolder{  //内部类用来保存所有的子控件
			ImageView icon;
			TextView title,content;
		//	public ViewHolder(){}
		}
	}
	
	/**
	 * 取得数据源的方法
	 * @return
	 */
	public ArrayList<MyMessage> getData(){ // 取得数据源
		ArrayList<MyMessage> data = new ArrayList<MyMessage>();
		
		MyMessage msg = new MyMessage();
		msg.setIcon(R.drawable.img_person);
		msg.setTitle("蛋炒饭1");
		msg.setContent("绝世蛋炒饭，吃了回味无穷。赶快加入吧。");
		data.add(msg); //将对象加入数据源
		
		msg = new MyMessage();
		msg.setIcon(R.drawable.img_buetyful);
		msg.setTitle("蛋炒饭2");
		msg.setContent("绝世蛋炒饭，吃了回味无穷。赶快加入吧。");
		data.add(msg); //将对象加入数据源
		
		msg = new MyMessage();
		msg.setIcon(R.drawable.img_smile);
		msg.setTitle("回锅肉");
		msg.setContent("绝世蛋炒饭，吃了回味无穷。赶快加入吧。");
		data.add(msg); //将对象加入数据源
		
		msg = new MyMessage();
		msg.setIcon(R.drawable.loagin_login_p);
		msg.setTitle("蛋炒饭1");
		msg.setContent("绝世蛋炒饭，吃了回味无穷。赶快加入吧。");
		data.add(msg); //将对象加入数据源
		
		msg = new MyMessage();
		msg.setIcon(R.drawable.mana);
		msg.setTitle("蛋炒饭1");
		msg.setContent("绝世蛋炒饭，吃了回味无穷。赶快加入吧。");
		data.add(msg); //将对象加入数据源
		
		return data;
	}
}
