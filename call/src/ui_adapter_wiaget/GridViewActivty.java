package ui_adapter_wiaget;
/**
 * mGridView的使用探讨    在GridView中只显示图片(ImageView)   与采用单帧布局在在图片上加字。(ImageView + TextView)
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.call.R;

public class GridViewActivty extends Activity {
	private GridView mGridView;
	private int[] gvData = {R.drawable.img_smile,R.drawable.meituan_image6,R.drawable.meituan_image7,R.drawable.meituan_image8,
			R.drawable.meituan_image1,R.drawable.meituan_image2,R.drawable.meituan_image3,R.drawable.meituan_image4,R.drawable.imageview_icon,
			R.drawable.meituan_image5,R.drawable.img_buetyful,R.drawable.imageview_icon};  //数据源
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_adapter_layout);
		mGridView = (GridView) findViewById(R.id.gridview_adapter); //实例化
		GridViewAdapter adapter = new GridViewAdapter(this);  //实例化自定义适配器
		adapter.setData(getArrayData());
	//	adapter.setData(gvData);  //设置数据源
		mGridView.setAdapter(adapter);
		
	}
	
	/**
	 * 必须自定义adapter.若用arrayAdapter则只会显示图片资源id，因为arrayAdapter源码中getView()方法中设置的是setText()方法
	 * 而不是设置的背景或者是src内容
	 * @author scxh
	 *
	 */
	public class GridViewAdapter extends BaseAdapter{
		//private int[] data = {};
		private ArrayList<ArrayData> data = new ArrayList<ArrayData>();
		private Context context;
		private LayoutInflater inflater;
		public GridViewAdapter(Context context){
			this.context = context;
			inflater = LayoutInflater.from(context);  //只需实例化一次LayoutInflater
		}
		
		/*public void setData(int[] data){  //设置数据源  便于以后的网络取数据
			this.data = data;
			notifyDataSetChanged();  //刷新数据源
		}*/
		
		public void setData(ArrayList data){
			this.data = data;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
		//	return data.length;
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			//return data[position];
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override  //当数据源是多个时  图片上有各自的文字说明。文字覆盖在图片上，采用单帧布局，要采用arrayList<HashMap>的方式出入数据了
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			Holder holder = new Holder();
			if(convertView == null){
				v = inflater.inflate(R.layout.gridview_item_layout, null);  //  采用单帧布局，可以在图片上加文字说明
				holder.image = (ImageView) v.findViewById(R.id.gridview_item_imageview);
				holder.textview = (TextView)v.findViewById(R.id.gridview_item_textview);
				v.setTag(holder);  //  保存子控件
			}else{
				v = convertView;
				holder = (Holder) v.getTag();  //取得保存的子控件 
			}
			ArrayData arrData = (ArrayData) this.getItem(position);  //取得每一项数据
			holder.image.setImageResource(arrData.getImage());  //设置的是内容不是背景
			holder.textview.setText(arrData.getText());  
			return v;
		}
		
		public class Holder{  //  用来保存所有子控件的类
			ImageView image;
			TextView textview;
		}
		
		/*@Override //当数据源只是图片时或是单一数据时
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageview;
			if(convertView == null){
				convertView = inflater.inflate(R.layout.gridview_item_layout, null);  //  当数据源只有图片时。
				imageview = (ImageView) convertView.findViewById(R.id.gridview_item_imageview);
				convertView.setTag(imageview);  //  保存子控件
			}else{
				imageview = (ImageView) convertView.getTag();  //取得保存的子控件
			}
			
			imageview.setImageResource((Integer) getItem(position));  //设置的是内容不是背景
			
			return convertView;
		}*/
	}
	
	public ArrayList<ArrayData> getArrayData(){   // 设置数据源
		ArrayList<ArrayData> listData = new ArrayList<ArrayData>();
		
		ArrayData arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image1);
		arrData.setText("这美味简直美得无话可说，绝对巴适");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image2);
		arrData.setText("肯德基特惠装，满足你所有需求");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image3);
		arrData.setText("家常小菜，美味可口");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image4);
		arrData.setText("这美味简直美得无话可说，绝对巴适");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image5);
		arrData.setText("simse小姐，来自美国加州");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image6);
		arrData.setText("拥有完美身材的mm,你值得拥有");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image7);
		arrData.setText("just do it,make moving");
		listData.add(arrData);  //  添加了一项数据了
		
		arrData = new ArrayData();
		arrData.setImage(R.drawable.meituan_image8);
		arrData.setText("这美味简直美得无话可说，绝对巴适");
		listData.add(arrData);  //  添加了一项数据了
		
		return listData;
	}
	
	public class ArrayData{				//  采用对象的方式保存每一天数据记录 文字说明和图片
		private int image;
		private String text;
		public int getImage() {
			return image;
		}
		public void setImage(int image) {
			this.image = image;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}
	
}
