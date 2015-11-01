package adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.call.R;

public class MyBaseAdapter extends BaseAdapter {
	
	private ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();  //数据源
	private Context mContext;   //getView()方法需要的参数 ，通过构造传进去 
	private LayoutInflater inflater;
	public MyBaseAdapter(ArrayList<HashMap<String, Object>> data , Context context){  //通过构造方法将数据源传进来
		this.mContext = context;
		this.data = data;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {  //取得listView里的项数，也就是数据源中有多少项数据记录
	//	Log.v("myAdapter", "getCount------->");
		return data.size();
	}

	@Override
	public Object getItem(int position) {  // 取得Item所在的位置,也就是数据源中对应的hashmap中的位置
	//	Log.d("myAdapter", "getItem------->");
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {  //取得对应位置的ID
	//	Log.i("myAdapter", "getItemId------->");
		return position;  //直接返回position
	}
	
	@Override 			// 二级优化
	public View getView(int position, View convertView, ViewGroup parent) { //取得对应界面上的view
		Log.w("myAdapter", "getView------->" + "convertView" + convertView);//可发现会执行多次.优化一哈如下。当滑动的时候就会实例去解析xml文件。所以加如下判断
	//	LayoutInflater inflater = LayoutInflater.from(mContext);  //这里需要一个context对象。可以通过构造函数传进来。解析xml布局文件用的对象, 
															//	实例化只需一次 ,放到构造方法中实例化
		//inflater.inflate(resource, root)  参数 resource为listview中每一项的xml布局的id。root为一个ViewGroup
		ViewHodler hodler ;  
		View v;
		if(convertView == null){   //converView为储存器中保存的布局。当为空的时候就去解析。当不为空的时候就直接去调用储存器中的xml布局
			v = inflater.inflate(R.layout.adapter_simple_item_relativelayout, null);  //解析xml布局文件
			
			hodler = new ViewHodler();//实例化内部类对象来保存布局中的控件
			hodler.icon = (ImageView) v.findViewById(R.id.adapter_relative_icon);  // 找到view中对应的子控件
			hodler.title = (TextView) v.findViewById(R.id.adapter_relative_title);
			hodler.content = (TextView) v.findViewById(R.id.adapter_relative_content);
			v.setTag(hodler);  //通过对象的方式保存这些控件
			
		}else{
			v = convertView;
			hodler = (ViewHodler) v.getTag();  //取出之前保存的控件
		}
		
	//	ImageView imgIcon = (ImageView) v.findViewById(R.id.adapter_relative_icon);  // 找到view中对应的子控件
	//	TextView txtTitle = (TextView) v.findViewById(R.id.adapter_relative_title);
	//	TextView txtContent = (TextView) v.findViewById(R.id.adapter_relative_content);
		
		HashMap<String, Object> item = (HashMap<String, Object>) this.getItem(position);  //取得数据源中的每一项数据并一一对应设置
		int icon = (Integer) item.get("icon");   //取得数据源中的icon对应的value值
		String title = (String) item.get("title");
		String content = (String) item.get("content");
		
		hodler.icon.setImageResource(icon); // 通过hodler对象中保存的控件来设置它要显示的内容
		hodler.title.setText(title);
		hodler.content.setText(content);	
	//	imgIcon.setImageResource(icon); //  在对应项上设置对应的内容
	//	txtTitle.setText(title);
	//	txtContent.setText(content);	
		return v;
	}
	
	class ViewHodler{  //内部类，用来保存布局中的所有空间
		ImageView icon;
		TextView title,content;	
	}
	/*@Override 			// 一级优化
	public View getView(int position, View convertView, ViewGroup parent) { //取得对应界面上的view
		Log.w("myAdapter", "getView------->" + "convertView" + convertView);//可发现会执行多次.优化一哈如下。当滑动的时候就会实例去解析xml文件。所以加如下判断
	//	LayoutInflater inflater = LayoutInflater.from(mContext);  //这里需要一个context对象。可以通过构造函数传进来。解析xml布局文件用的对象, 
															//	实例化只需一次 ,放到构造方法中实例化
		//inflater.inflate(resource, root)  参数 resource为listview中每一项的xml布局的id。root为一个ViewGroup
		View v;
		if(convertView == null){   //converView为储存器中保存的布局。当为空的时候就去解析。当不为空的时候就直接去调用储存器中的xml布局
			//
			v = inflater.inflate(R.layout.adapter_simple_item_relativelayout, null);  //解析xml布局文件
		}else{
			v = convertView;
		}
		
		ImageView imgIcon = (ImageView) v.findViewById(R.id.adapter_relative_icon);  // 找到view中对应的子控件
		TextView txtTitle = (TextView) v.findViewById(R.id.adapter_relative_title);
		TextView txtContent = (TextView) v.findViewById(R.id.adapter_relative_content);
		
		HashMap<String, Object> item = (HashMap<String, Object>) this.getItem(position);  //取得数据源中的每一项数据并一一对应设置
		int icon = (Integer) item.get("icon");   //取得数据源中的icon对应的value值
		String title = (String) item.get("title");
		String content = (String) item.get("content");
		
		imgIcon.setImageResource(icon); //  在对应项上设置对应的内容
		txtTitle.setText(title);
		txtContent.setText(content);	
		return v;
	}*/

	
	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) { //取得对应界面上的view
		Log.w("myAdapter", "getView------->");//可发现会执行多次。随意优化一哈如下。
		LayoutInflater inflater = LayoutInflater.from(mContext);  //这里需要一个context对象。可以通过构造函数传进来。解析xml布局文件用的对象,
		//inflater.inflate(resource, root)  参数 resource为listview中每一项的xml布局的id。root为一个ViewGroup
		View v = inflater.inflate(R.layout.adapter_simple_item_relativelayout, null);  //解析xml布局文件
		
		ImageView imgIcon = (ImageView) v.findViewById(R.id.adapter_relative_icon);  // 找到view中对应的子控件
		TextView txtTitle = (TextView) v.findViewById(R.id.adapter_relative_title);
		TextView txtContent = (TextView) v.findViewById(R.id.adapter_relative_content);
		
		HashMap<String, Object> item = (HashMap<String, Object>) this.getItem(position);  //取得数据源中的每一项数据并一一对应设置
		int icon = (Integer) item.get("icon");   //取得数据源中的icon对应的value值
		String title = (String) item.get("title");
		String content = (String) item.get("content");
		
		imgIcon.setImageResource(icon); //  在对应项上设置对应的内容
		txtTitle.setText(title);
		txtContent.setText(content);
		
		return v;
	}*/

}
