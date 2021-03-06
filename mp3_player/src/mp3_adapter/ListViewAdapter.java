package mp3_adapter;

import java.io.File;
import java.util.ArrayList;

import mylog.Mylog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp3_player.R;

public class ListViewAdapter extends BaseAdapter {
	

	ArrayList<File> fileData = new ArrayList<File>();
	private Context mContext;
	private LayoutInflater mInflater;
	public ListViewAdapter(Context context){
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}
	
	public void refushData(ArrayList<File> list){
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
	
	Holder holder;
	@Override
	public View getView( int position,  View convertView, ViewGroup parent) {
		final View v ;
		if(convertView == null){
			holder = new Holder();
			v = mInflater.inflate(R.layout.listview_item_layout, null);
			holder.nameText = (TextView) v.findViewById(R.id.listview_music_name_txt);
			holder.loveImg = (ImageView) v.findViewById(R.id.listview_love_img); 
			v.setTag(holder);
		}else{
			v = convertView;
			holder = (Holder) v.getTag();
			
		}
		
		// holder.loveImg.setOnClickListener(new MyOnClickListener(position));
		 holder.loveImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vi) {
				ImageView img = (ImageView) v.findViewById(R.id.listview_love_img);
				img.setBackgroundResource(R.drawable.player_btn_favorited_normal);
			}
		});
		
		
		
		
		File mp3File = (File) getItem(position);
		holder.nameText.setText(mp3File.getName());
		return v;
	}
	
	
	class Holder{
		TextView nameText;
		ImageView loveImg;
	}
	
	
	public class MyOnClickListener implements OnClickListener{
		private int posstion; 
		
		public MyOnClickListener(int posstion){
			this.posstion = posstion;
		}

		@Override
		public void onClick(View v) {
			int vid = v.getId();
			if(vid == holder.loveImg.getId()){
				holder.loveImg.setBackgroundResource(R.drawable.player_btn_favorited_normal);
			}
		}
		
	}
}


