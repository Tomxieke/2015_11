package studytest;
/**
 * 对adapter对应显示控件的学习  这些子控件可以为 ListView  Spinner(点击弹出选项框) AutoCompleteTextView(输入弹出提示框) 
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class AdapterHomeworkActivity extends Activity {
	private ListView mlistView;
	private Spinner mSpinner,mSpinner1;  //点击弹出选项框 
	private AutoCompleteTextView mAutoCompletetxt;  //输入时提示框
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_homework_layout_listview);
//=====================================listview加载的数据源=================================================================================
		mlistView = (ListView) findViewById(R.id.adapter_homework_listview);  //实列化listView
		
		ArrayList<HashMap> mListData = new ArrayList<HashMap>();
		setDate(mListData);  //设置数据源
		
		HomeworkAdapter adapter = new HomeworkAdapter(mListData,this);  //实例化自定义的适配器
		
		mlistView.setAdapter(adapter);
//==================================Spinner加载的数据源===================================================================================
		mSpinner = (Spinner) findViewById(R.id.adapter_homework_spinner);  //  实例化显示控件
		
		String[] strData = {"本地特色","火锅系列","清淡系列","特价系列"};  //数据源
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strData);
		mSpinner.setAdapter(arrayAdapter);
//		mSpinner.setSelection(1);  //设置默认选中哪一项，但第一次进去的时候还是会执行onItemSelected()监听  必须在设置适配器之后
		mSpinner.setSelection(1, true);  // 后面的boolean参数决定第一次进去的时候执不执行onItemSelected()监听  必须在设置适配器之后
		
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {  //setOnItemSelectedListener()方法添加各项被选中的监听
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {  //被选中时间
				TextView textView = (TextView) view;  //强制转换
				Toast.makeText(AdapterHomeworkActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {  //没有一项被选中的事件
				
			}
		});
//=================================AutoCompleteTextView加载数据源======================================================================================
		
		mSpinner1 = (Spinner) findViewById(R.id.adapter_homework_spinner1);  //实例化Spinner控件
		ArrayList<HashMap> mListData1 = new ArrayList<HashMap>();
		setDate(mListData1);  //设置数据源
		HomeworkAdapter adapter1 = new HomeworkAdapter(mListData1,this);  //实例化自定义的适配器
		mSpinner1.setAdapter(adapter1);
		
//=================================AutoCompleteTextView加载数据源======================================================================================
		mAutoCompletetxt = (AutoCompleteTextView) findViewById(R.id.adapter_homework_autocompletetextview);
		
		final String[] aData = {"a dog come here","awdfge","adghe","aclock","apple"};  //有两个不同的数据源，需要设置文本转化时监听
		final String[] AData = {"Amothe","Amoon","Aocjel","Aperson","Atree","After"};
		
		mAutoCompletetxt.addTextChangedListener(new TextWatcher() {  //添加文本转化监听
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) { //输入时需要的操作  s即为输入的内容
				String in = s.toString();  //只能用toString()方法。不能采用强制转换为String型  
				if(in.equals("a")){    //当输入第一个字母为a时 设置带有对应数据源的适配器
					ArrayAdapter<String> autoArrayAdapter = new ArrayAdapter<String>(AdapterHomeworkActivity.this, 
							android.R.layout.simple_list_item_1, aData);
					mAutoCompletetxt.setAdapter(autoArrayAdapter);
				}else if(in.equals("A")){     //当输入第一个字母为A时 设置带有对应数据源的适配器
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdapterHomeworkActivity.this, 
							android.R.layout.simple_list_item_1, AData);
					mAutoCompletetxt.setAdapter(arrayAdapter);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) { //输入前需要的操作
				
			}
			
			@Override
			public void afterTextChanged(Editable s) { //输入后的操作
				
			}
		});
		mAutoCompletetxt.setThreshold(1);   // 设置当输入一个时就弹出提示框
	}
//======================================================================================================================
	
	protected class HomeworkAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		private ArrayList<HashMap> mData;
		public HomeworkAdapter(ArrayList<HashMap> mData,Context context){  //构造方法，传递必要的参数
			this.mData = mData;
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder ;  // 用于保存v中的所有小控件
			View v;
			if(convertView == null){  //当为空时，实例化布局
				v = inflater.inflate(R.layout.adapter_homework_item_layout, null);
				holder = new ViewHolder();
				holder.icon = (ImageView) v.findViewById(R.id.adapter_homework_imageview_icon);
				holder.title = (TextView) v.findViewById(R.id.adapter_homework_textview_title);
				holder.content = (TextView) v.findViewById(R.id.adapter_homework_textview_content);
				holder.price = (TextView) v.findViewById(R.id.adapter_homework_textview_price);
				holder.discount = (TextView) v.findViewById(R.id.adapter_homework_textview_oldPrice);
				holder.sellCount = (TextView) v.findViewById(R.id.adapter_homework_textview_sellCount);
				v.setTag(holder);  //将控件保存起来
			}else{
				v = convertView;
				holder = (ViewHolder) v.getTag();  //将控件从holder中取出来
			}
			
			HashMap<String, Object> map = (HashMap<String, Object>) this.getItem(position);  //取得每一项数据
			int icon = (Integer) map.get("icon");  //取出对应的每一个数据
			String title1 = (String) map.get("title");
			String content1 = (String) map.get("content");
			String sellCount = (String) map.get("sellCount");
			
			String price1 = (String) map.get("price");  
			SpannableString price = setText(price1);
			String discount1 = (String) map.get("discount");
			SpannableString discount = addStrikeSpan(discount1);  //调用方法设置显示字体的样式  。删除原价的样式
			
			holder.icon.setImageResource(icon);  //设置显示的对应每一项数据
			holder.content.setText(content1);
			holder.price.setText(price);  //  显示数据是通过处理后的内容
			holder.title.setText(title1);
			holder.discount.setText(discount);
			holder.sellCount.setText(sellCount);
			
			return v;
		}
		
		class ViewHolder{
			ImageView icon;
			TextView title;
			TextView content;
			TextView price,discount,sellCount;
		}
		
	} 
	
	
	public SpannableString setText(String string){  //设置同一个textView中的字体为不同大小的字体
		SpannableString spanString = new SpannableString(string);
		AbsoluteSizeSpan span = new AbsoluteSizeSpan(45); //文本字体
		spanString.setSpan(span, 0, string.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
		//设置文字颜色
		spanString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, string.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //设置颜色
		spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, string.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置字体
		return spanString;
	}
	
	public SpannableString addStrikeSpan(String string){ //  删除文字的处理
		SpannableString spanString = new SpannableString(string);
		StrikethroughSpan span = new StrikethroughSpan();		//删除文本处理
		spanString.setSpan(span, 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spanString;
	}
	
	protected void setDate(ArrayList<HashMap> data){  //设置数据源的方法
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_person);
		map.put("title", "火腿鸡肠");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "9.9元");
		map.put("discount", "25元");
		map.put("sellCount", "已售238份");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_person);
		map.put("title", "香辣鸡肠");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "9.9元");
		map.put("discount", "29元");
		map.put("sellCount", "已售2381份");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_person);
		map.put("title", "回味无穷的驴蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "90元");
		map.put("discount", "158元");
		map.put("sellCount", "已售38份");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_smile);
		map.put("title", "回味无穷的驴蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "100元");
		map.put("discount", "298元");
		map.put("sellCount", "已售208份");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.img_buetyful);
		map.put("title", "回味无穷蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "2000元");
		map.put("discount", "5600元");
		map.put("sellCount", "已售28份");
		data.add(map);  //向数据源中添加元素
		
		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.weixin_icon2);
		map.put("title", "无穷蛋");
		map.put("content", "2015年推出火腿鸡肠蛋炒饭，入口即化。香甜可口，绝对是饭桌上不可或缺的开胃菜。");
		map.put("price", "9.8元");
		map.put("discount", "12元");
		map.put("sellCount", "已售2份");
		data.add(map);  //向数据源中添加元素
	}

}
