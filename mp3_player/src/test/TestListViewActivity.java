package test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mp3_player.R;

public class TestListViewActivity extends Activity {
	private ListView mListView;
	private int oldPossiton = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview_main_layout);
		mListView = (ListView) findViewById(R.id.test_listview);
		ListViewMusicBeanAdapter adapter = new ListViewMusicBeanAdapter(this);
		adapter.refushData(getData());
		mListView.setAdapter(adapter);
		
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Data d = (Data) parent.getAdapter().getItem(position);
				if (oldPossiton == position) {
					if (d.open) {
						oldPossiton = -1;
					}
					d.open = !d.open;
				} else {
					oldPossiton = position;
					d.open = true;
				}
			}
		});
		
	}
	
	
	public ArrayList<Data> getData(){
		ArrayList<Data> listData = new ArrayList<Data>();
		
		Data d = new Data();
		d.name = "A";
		listData.add(d);
		
		d = new Data();
		d.name = "B";
		listData.add(d);
		
		d = new Data();
		d.name = "C";
		listData.add(d);
		
		d = new Data();
		d.name = "D";
		listData.add(d);
		
		return listData;
	}

	
	
	
	
	/*适配器*/
	public class ListViewMusicBeanAdapter extends BaseAdapter {
		ArrayList<Data> fileData = new ArrayList<Data>();
		private Context mContext;
		private LayoutInflater mInflater;
		public ListViewMusicBeanAdapter(Context context){
			this.mContext = context;
			mInflater = LayoutInflater.from(context);
		}
		public void refushData(ArrayList<Data> list){
			this.fileData = list;
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			return fileData.size();
		}

		@Override
		public Object getItem(int position) {
			return fileData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Tag tag ;
			if(convertView == null){
				tag  = new Tag();
				convertView = mInflater.inflate(R.layout.test_listview_item_layout, null);
				tag.itemA = (TextView) convertView.findViewById(R.id.item1); 
				tag.itemB = (ImageView) convertView.findViewById(R.id.item2);
				convertView.setTag(tag);
			}else{
				tag = (Tag) convertView.getTag();
			}
			
			Data data = fileData.get(position);
			if(data.open){
				tag.itemB.setVisibility(View.VISIBLE);
			}else{
				tag.itemB.setVisibility(View.GONE);
			}
			tag.itemA.setText(data.name);
			return convertView;
		}
		
		
		class Tag{
			TextView itemA;
			ImageView itemB;
		}
	}
	
	class Data{
		boolean open = true;
		String name;
	}
}
