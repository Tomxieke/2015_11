package data.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mylog.Mylog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.call.R;

import data.sharedpreference.Dog;

public class FilesTest extends AndroidTestCase {
	private FileHelper mHelper;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mHelper = FileHelper.getInstance(getContext(), "dog.txt"); 
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testFiles(){
		File fileDir = getContext().getFilesDir();   //getFilesDir() 这个方法可以得到文件保存位置的文件
	//	File fileCache = getContext().getCacheDir();  //getCacheDir() 这个方法返回的也是系统保存文件的一个文件根目录
		File file = new File(fileDir.getAbsolutePath()+"test.txt");  // 创建文件
		FileOutputStream out = null ;
		try {
			String data = "hello world!";
			out = new FileOutputStream(file);
			out.write(data.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void testReadDataFromFiles(){
		File fileDir = getContext().getFilesDir();
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileDir.getAbsolutePath()+"test.txt");
			byte[] bt = new byte[1024];
			int temp = 0;
			while((temp = in.read()) != -1){
				in.read(bt, 0, temp);
				Log.v("test", new String(bt));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * android系统封装的读写操作
	 */
	public void writeDataToFileByAndroid(){
		try {
			FileOutputStream out = getContext().openFileOutput("android.txt", getContext().MODE_APPEND);  
			//openFileOutput(name,mode) name为文件路径  mode为模式 
			String data = "android封装的方式写入";
			out.write(data.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 访问外部储存公有区域之前，判断该区域是否可写可读
	 * @return true可读可写   false不能读写    
	 */
	public boolean isExternalStorageWritable(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * 访问外部储存公有区域
	 * 路径为  /mnt/sdcard/Pictures 。。。
	 */
	public void writePrictorToExternalPublic() {
		if (isExternalStorageWritable()) {   //读写之前先判断是否可以读写
			String dir = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES).getAbsolutePath(); // 得到外部储存公有区域路径
			Log.v("test", dir);
			// 写图片借用Bitmap这个类
			Bitmap bitmap = BitmapFactory.decodeResource(getContext()
					.getResources(), R.drawable.img_person);
			File file = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					"1.png");
			try {
				FileOutputStream out = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 访问外部储存私有区域
	 * 路径为   getExternalFilesDir（）       /mnt/sdcard/Android/data/com.example.call/files
	 * 		getExternalCacheDir()    /mnt/sdcard/Android/data/com.example.call/cache
	 */
	public void writeFileToExternalPrivate(){
		String fileDir = getContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
		Log.v("test", fileDir);
		
		String cacheDir = getContext().getExternalCacheDir().getAbsolutePath();
		Log.v("test", cacheDir);
	}
	
	public void testWriteObjectToFile(){
		Dog dog = new Dog();
		dog.setName("kit");
		dog.setAge(10);
		dog.setSex("mu");
		mHelper.writeDogToFile(dog);
	}
	
	public void testReadObjectToFile(){
		Dog dog = mHelper.readDogFromFile();
		Mylog.v("姓名：   " + dog.getName() + "性别：  " + dog.getSex() + "年龄：  " + dog.getAge());
	}
	//----------------------------------遍历sdcard目录下的所有文件----------------------------------------------------
	public  void testScanFiles(){
		File file = Environment.getExternalStorageDirectory();// 得到mnt/sdcard/  路径
		ScanFilesList(file);
	}
	
	public void ScanFilesList(File srcFile) {
		File[] parentFileList = srcFile.listFiles();
//		if (parentFileList != null) {
			for (int i = 0; i < parentFileList.length; i++) {
				File file = parentFileList[i];
				if (file.isFile()) {
					ScanFilesList(file);
				} else {
					Mylog.v("文件名" + file.getName());
					Mylog.i("路径" + file.getAbsolutePath());
				}
			}
		}
//	}
	
	
	
	public void Test(){
		File file = Environment.getExternalStorageDirectory();// 得到mnt/sdcard/  路径
		String filePath = file.getAbsolutePath();
		TestFilePath(filePath);
	}
	public void TestFilePath(String filePath){
		File srcFile = new File(filePath);
		File[] parentFileList = srcFile.listFiles();
//		if (parentFileList != null) {
			for (int i = 0; i < parentFileList.length; i++) {
				File file = parentFileList[i];
				if (file.isFile()) {
					ScanFilesList(file);
				} else {
					Mylog.v("文件名" + file.getName());
					Mylog.i("路径" + file.getAbsolutePath());
				}
			}
		
	}
//------------------------------------------------------------------------------------------------------------------
}
