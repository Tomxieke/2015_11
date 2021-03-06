package replayer;

import java.io.File;
import java.util.ArrayList;

import mp3_adapter.MusicBean;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mp3_player.R;

public class ReMusicListActivity extends Activity implements OnItemClickListener{
	private ListView mMusicNameListView;
	private ArrayList<MusicBean> mMusicBeanList = new ArrayList<MusicBean>();
	private Intent mIntent;
	private Handler mHandler = new Handler();
	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.music_activity_list_layout);
		initView();
	}
	
	public void initView(){
		mMusicNameListView = (ListView) findViewById(R.id.music_list_activity_listview);
		mMusicNameListView.setOnItemClickListener(this);
		
		mImageView = (ImageView) findViewById(R.id.anim_img);
		Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.player_rotate); 
		mImageView.startAnimation(operatingAnim);
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						searchMp3File(Environment.getExternalStorageDirectory());  // 扫描歌曲,加载数据源
					}
				});
			}
		}).start();
		ListViewMusicBeanAdapter adapter = new ListViewMusicBeanAdapter(this);
		adapter.refushData(mMusicBeanList);
		mMusicNameListView.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MusicBean m  = (MusicBean) arg0.getAdapter().getItem(arg2);
		mIntent = new Intent(this,PlayerActivity.class);
		mIntent.putParcelableArrayListExtra("MusicBean", mMusicBeanList);
		mIntent.putExtra("posstion", arg2);
		startActivity(mIntent);
	}
	
	
	public void searchMp3File(File srcFile) {
		File[] arrFiles = srcFile.listFiles();
		if (arrFiles != null && arrFiles.length > 0) { // 空判断
			for (int i = 0; i < arrFiles.length; i++) { // 不为空就遍历
				File mp3File = arrFiles[i];
				if (mp3File.isDirectory()) { // 是目录 则递归
					searchMp3File(mp3File);
				} else {
					if (mp3File.getName().endsWith(".mp3")) { // 文件以mp3结尾，则放入数据源
						MusicBean musicBean = new MusicBean();
						musicBean.setMusicPath(mp3File.getAbsolutePath());
						musicBean.setMusicName(mp3File.getName().substring(0, mp3File.getName().length()-".mp3".length()));
						mMusicBeanList.add(musicBean);
					}
				}
			}
		}
	}
	
	
	/*适配器*/
	public class ListViewMusicBeanAdapter extends BaseAdapter {
		ArrayList<MusicBean> fileData = new ArrayList<MusicBean>();
		private Context mContext;
		private LayoutInflater mInflater;
		public ListViewMusicBeanAdapter(Context context){
			this.mContext = context;
			mInflater = LayoutInflater.from(context);
		}
		public void refushData(ArrayList<MusicBean> list){
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
			TextView mp3Name;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.listview_item_layout, null);
				mp3Name = (TextView) convertView.findViewById(R.id.listview_music_name_txt);
				convertView.setTag(mp3Name);
			}
			
			MusicBean mp3MusicBean = (MusicBean) getItem(position);
			mp3Name = (TextView) convertView.getTag();
			mp3Name.setText(mp3MusicBean.getMusicName());
			return convertView;
		}
	}
}
