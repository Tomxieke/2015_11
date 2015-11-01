package data.files;
/**
 * 实现对手机储存中的内部储存、外部储存私有部分、外部储存公有部分分别写入文件并读取出来
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.call.R;

public class WritePrictureToStoreActivity extends Activity {
	private Button mWriteBtn,mReadBtn;
	private ImageView mInnerImg,mOutPublicImg,mOutPrivateImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_data_to_file_layout);
		mWriteBtn = (Button) findViewById(R.id.write);
		mReadBtn = (Button) findViewById(R.id.read);
		
		mInnerImg = (ImageView) findViewById(R.id.inner_file_img);
		mOutPublicImg = (ImageView) findViewById(R.id.out_file_public_img);
		mOutPrivateImg = (ImageView) findViewById(R.id.out_file_private_img);
		
		mWriteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				writePricterToFile();
			}
		});
		
		mReadBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				readPricturFromFile();
			}
		});
		
	}
	
	/*
	 * 可以直接将写入的图片提取出来直接用就可以了
	 * 也可以采用流读取出来的方式
	 */
	public void readPricturFromFile(){
		//直接使用资源的方式
		File outPublicFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "333.png");
		Bitmap bitmap = BitmapFactory.decodeFile(outPublicFile.getAbsolutePath());
		mOutPublicImg.setImageBitmap(bitmap);
		//使用流读取的方式带缓冲
		try {
			File outPrivateFile = new File(this.getExternalCacheDir(), "333.png");  //外部储存私有部分文件
			FileInputStream in = new FileInputStream(outPrivateFile);
		//	BufferedInputStream bin = new BufferedInputStream(in);
			Bitmap btp = BitmapFactory.decodeStream(in);
			mOutPrivateImg.setImageBitmap(btp);
			in.close();
		//	bin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//不带缓冲
		try {
			File innerFile = new File(this.getCacheDir(),"333.png");  //内部储存文件
			FileInputStream in = new FileInputStream(innerFile);
			BufferedInputStream bin = new BufferedInputStream(in);
			Bitmap btp = BitmapFactory.decodeStream(bin);
			mInnerImg.setImageBitmap(btp);
			in.close();
			bin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * 写入图片的方法
	 */
	public void writePricterToFile(){
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_person);
		File outPublicFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "333.png");  //外部储存公有部分文件
		File outPrivateFile = new File(this.getExternalCacheDir(), "333.png");  //外部储存私有部分文件
		File innerFile = new File(this.getCacheDir(),"333.png");  //内部储存文件
		try {
			FileOutputStream in_in = new FileOutputStream(innerFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, in_in);
			
			FileOutputStream out_in = new FileOutputStream(outPrivateFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out_in);
			
			FileOutputStream out_out = new FileOutputStream(outPublicFile);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out_out);
			in_in.close();
			out_out.close();
			out_in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
