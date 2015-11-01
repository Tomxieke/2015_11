package scan_files;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mylog.Mylog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.call.R;

public class LookFilesActivity extends Activity {
	private ListView mList;
	private TextView mPathTxt;
	private ImageView mBackImg;
	ListAdapter adatper;
//	private File file = Environment.getExternalStorageDirectory();
	private File file = new File("/");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lookfile_list_layout);
		mPathTxt = (TextView) findViewById(R.id.path);
		mBackImg = (ImageView) findViewById(R.id.file_back_img);
		
		mList = (ListView) findViewById(R.id.file_listview);
		adatper = new ListAdapter(this);
		adatper.refishData(getData(file));
		mList.setAdapter(adatper);
		
		/*File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/AVI"); // 在目录下创建文件夹
		Mylog.d(""+myFile.mkdir());*/
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListAdapter adapter = (ListAdapter) parent.getAdapter();
				File file = (File) adapter.getItem(position);
				if(file.isDirectory()){
					adapter.refishData(getData(file));
					mPathTxt.setText(file.getAbsolutePath());  // 显示当前文件的全路径
				}else{
					startIntent(file);
				}
			}
		});
		
		
		mBackImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				File file = new File((String) mPathTxt.getText());  // 得到当前fiel
				backBtnListener(file);
				mPathTxt.setText(file.getParent());
			}
		});
	}
	
	/*
	 * 返回键
	 */
	public void backBtnListener(File file){
		String parentFile = file.getParent();
	//	Mylog.e(parentFile);
		if(parentFile != null){  //说明有父目录
			File files = new File(parentFile);
			ArrayList<File>   listFile = getData(files);
			adatper.refishData(listFile);
		}else{
			LookFilesActivity.this.finish();
		}
	}
	
	
	/*
	 *点击每一项执行的代码    如果是文件执行打开文件操作
	 */
	public void startIntent(File file){
		 String fileName = file.getName();
	//	 Mylog.i(file.getAbsolutePath());
		 if(fileName.endsWith(".png") || fileName.endsWith(".jpg")){
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(file);
			intent.setDataAndType(uri, "image/*");
			startActivity(intent);
		} else if (fileName.endsWith(".mp3")) {
			String mp3FileDir = "file://";
			Uri uri = Uri.parse(mp3FileDir + file.getAbsolutePath());
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(uri, "audio/mp3");
			startActivity(intent);
		}
	}
	 
	
	
	/**
	 * 设置适配器的数据源
	 * @param parentFilePath
	 * @return
	 */
	public ArrayList<File> getData(File root) {
		ArrayList<File> mData = new ArrayList<File>();
		File[] parentFileList = root.listFiles();
		if (parentFileList != null) {
			for (int i = 0; i < parentFileList.length; i++) {
				File file = parentFileList[i];
				mData.add(file);
			}
		}
		return mData;
	}

	/*
	 * listview适配器
	 */
	public class ListAdapter extends BaseAdapter{
		ArrayList<File> mData = new ArrayList<File>();
		private LayoutInflater mInflater;
		private Context mContext;
		
		public ListAdapter(Context context){
			this.mContext = context;
			mInflater = LayoutInflater.from(context);
		}
		
		public void refishData(ArrayList<File> data){
			this.mData = data;
			notifyDataSetChanged();   //刷新数据
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
			Holder holer = new Holder();
			View v;
			if(convertView  == null){
				convertView = mInflater.inflate(R.layout.lookfile_item_layout, null);
				holer.fileNameTxt = (TextView) convertView.findViewById(R.id.file_name_txt);
				holer.timeTxt = (TextView) convertView.findViewById(R.id.file_create_time_txt);
				holer.sizeTxt = (TextView) convertView.findViewById(R.id.file_size_txt);
				holer.classImg = (ImageView) convertView.findViewById(R.id.file_img);
				convertView.setTag(holer);
			}else{
				holer =  (Holder) convertView.getTag();
			}
			
			File itemFile = mData.get(position);
			
			if(itemFile.isDirectory()){   //如果是文件夹
				holer.classImg.setImageResource(R.drawable.filesystem_icon_folder);
			}else if(itemFile.isFile()){ //如果是文件
				if(itemFile.getAbsolutePath().endsWith(".mp3")){  //如果是mp3文件
					holer.classImg.setImageResource(R.drawable.vdieo_icon);
				}else if(itemFile.getAbsolutePath().endsWith(".jpg")){
					holer.classImg.setImageResource(R.drawable.filesystem_grid_icon_zip);
				}else{
					holer.classImg.setImageResource(R.drawable.filesystem_grid_icon_text);
				}
			}
			
			holer.fileNameTxt.setText(itemFile.getName());
			holer.timeTxt.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(itemFile.lastModified()));  // 设置最近访问文件的时间
			
			long fileSize = itemFile.length();
			if(fileSize > 1024*1024) {
                float size = fileSize /(1024f*1024f);
                holer.sizeTxt.setText(new DecimalFormat("#.00").format(size) + "MB");
            } else if(fileSize >= 1024) {
                float size = fileSize/1024;
                holer.sizeTxt.setText(new DecimalFormat("#.00").format(size) + "KB");
            } else {
            	holer.sizeTxt.setText(fileSize + "B");
            }
			
			return convertView;
		}
		
		class Holder{
			TextView fileNameTxt,timeTxt,sizeTxt;
			ImageView classImg;
		}
	}
	
	
}

