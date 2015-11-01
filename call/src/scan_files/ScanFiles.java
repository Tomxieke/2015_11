package scan_files;

import java.io.File;

import mylog.Mylog;
import android.os.Environment;

public class ScanFiles {

	
	public  static void getFileList(){
		File file = Environment.getExternalStorageDirectory();// 得到mnt/sdcard/  路径
		ScanFilesList(file);
	}
	
	public static void ScanFilesList(File srcFile) {
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
}
