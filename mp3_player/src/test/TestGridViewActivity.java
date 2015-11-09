package test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp3_player.R;

public class TestGridViewActivity extends Activity {
	private GridView mGridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_home_main_layout);
		mGridView = (GridView) findViewById(R.id.home_gridview);
		
		HomeGridViewAdapter adapter = new HomeGridViewAdapter(this);
		adapter.refrishData(getdata());
		mGridView.setAdapter(adapter);
		
		
	}
	
	public ArrayList<Icon> getdata(){
		ArrayList<Icon>  data = new ArrayList<Icon>();
		
		Icon icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl1_selector));
		icon.setText("本地歌曲");
		icon.setCount(512);
		data.add(icon);
		
		icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl2_selector));
		icon.setText("下载歌曲");
		icon.setCount(12);
		data.add(icon);
		
		icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl3_selector));
		icon.setText("最近播放");
		icon.setCount(68);
		data.add(icon);
		
		icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl4_selector));
		icon.setText("我喜欢");
		icon.setCount(11);
		data.add(icon);
		
		icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl5_selector));
		icon.setText("下载歌MV");
		icon.setCount(0);
		data.add(icon);
		
		icon = new Icon();
		icon.setDrawable(getResources().getDrawable(R.drawable.gridview_item_drawabl6_selector));
		icon.setText("听歌识曲");
		icon.setCount(0);
		data.add(icon);
		
		return data;
	}
	
	/*适配器*/
	public class HomeGridViewAdapter extends BaseAdapter{
		ArrayList<Icon>  data = new ArrayList<Icon>(); 
		private Context mContext;
		private LayoutInflater mInflter;
		public HomeGridViewAdapter(Context context){
			this.mContext = context;
			mInflter = LayoutInflater.from(context);
		}
		
		public void refrishData(ArrayList<Icon> data){
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = new Holder();
			View v;
			if(convertView == null){
				v = mInflter.inflate(R.layout.gridview_item_layout, null);
				holder.iconImg = (ImageView) v.findViewById(R.id.gridView_item_img);
				holder.contentView = (TextView) v.findViewById(R.id.gridView_item_contentTxt);
				holder.countView =(TextView) v.findViewById(R.id.gridView_item_countTxt);
				v.setTag(holder);
			}else{
				v = convertView;
				holder = (Holder) v.getTag();
			}
			Icon icons = data.get(position);
			holder.iconImg.setImageDrawable(icons.getDrawable());
			holder.contentView.setText(icons.getText());
			holder.countView.setText(""+icons.getCount());
			return v;
		}
		
		class Holder{
			ImageView iconImg;
			TextView contentView,countView;
		}
		
	}
	
	 /*保存GridView每一项的数据*/
	public class Icon{ 
		private Drawable drawable;  //图标资源id
		private String text;	//文本
		private int count;	//下方数值
		public Drawable getDrawable() {
			return drawable;
		}
		public void setDrawable(Drawable drawable) {
			this.drawable = drawable;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
}
