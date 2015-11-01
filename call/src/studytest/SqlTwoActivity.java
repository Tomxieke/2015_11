package studytest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.call.R;

public class SqlTwoActivity extends Activity {
	private ListView mListview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqltwo_layout);
		mListview = (ListView) findViewById(R.id.sql_listview);
		
		View v = LayoutInflater.from(this).inflate(R.layout.sql_listview_header, null);
		mListview.addHeaderView(v);
		
		Intent intent = getIntent();
		ArrayList<? extends Parcelable> stuList = intent.getParcelableArrayListExtra("data");
		SqlTwoAdapter adapter = new SqlTwoAdapter(this); 
		adapter.refishData((ArrayList<Student>) stuList);
		mListview.setAdapter(adapter);
		
		
	}
	
	/*
	 * listview的适配器
	 */
	public class SqlTwoAdapter extends BaseAdapter {   //Listview的适配器
		
		private ArrayList<Student> data = new ArrayList<Student>();  //数据源
		private Context mContext;   //getView()方法需要的参数 ，通过构造传进去 
		private LayoutInflater inflater;
		public SqlTwoAdapter(  Context context){  //通过构造方法将数据源传进来
			this.mContext = context;
			inflater = LayoutInflater.from(mContext);
		}
		
		public void refishData(ArrayList<Student> data){  //更新数据源
			this.data = data;
		}
		

		@Override
		public int getCount() {  //取得listView里的项数，也就是数据源中有多少项数据记录
			return data.size();
		}

		@Override
		public Object getItem(int position) {  // 取得Item所在的位置,也就是数据源中对应的hashmap中的位置
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {  //取得对应位置的ID
			return position;  //直接返回position
		}
		
		@Override 			
		public View getView(int position, View convertView, ViewGroup parent) { //取得对应界面上的view
			ViewHodler hodler ;  
			View v;
			if(convertView == null){   //converView为储存器中保存的布局。当为空的时候就去解析。当不为空的时候就直接去调用储存器中的xml布局
				v = inflater.inflate(R.layout.sql_liseview_layout, null);  //解析xml布局文件
				
				hodler = new ViewHodler();//实例化内部类对象来保存布局中的控件
				hodler.name =  (TextView) v.findViewById(R.id.list_name);  // 找到view中对应的子控件
				hodler.password = (TextView) v.findViewById(R.id.list_password);
				v.setTag(hodler);  //通过对象的方式保存这些控件
				
			}else{
				v = convertView;
				hodler = (ViewHodler) v.getTag();  //取出之前保存的控件
			}
			
			
			Student item = (Student) this.getItem(position);  //取得数据源中的每一项数据并一一对应设置
			hodler.name.setText(item.getName());
			hodler.password.setText(item.getPassword());
			return v;
		}
		
		class ViewHodler{  //内部类，用来保存布局中的所有空间
			TextView name,password;
		}
	}
}
