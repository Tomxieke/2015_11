package music.mediaplayer;

import java.io.File;
import java.util.ArrayList;

import mylog.Mylog;

import android.os.Environment;
import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	
	public void testFilePath(){
		File file = Environment.getExternalStorageDirectory();
		Mylog.i(file.getAbsolutePath() + "     parent"  +  file.getParent());
	}
	
	
	public void testSearchMp3File(File file){
		File srcFile = Environment.getExternalStorageDirectory();
		SearchMp3File(srcFile);
	}
	
	public void SearchMp3File(File file){
		File[] arrFiles = file.listFiles();
		if(arrFiles != null && arrFiles.length > 0){  //空判断
			for(int i = 0;i < arrFiles.length;i++){  //不为空就遍历
				if(arrFiles[i].isDirectory()){  // 是目录 则递归
					Mylog.i(arrFiles[i].getAbsolutePath()+"------------------");
					SearchMp3File(arrFiles[i]);
				}else{
					if(arrFiles[i].getName().endsWith(".mp3")){  //文件以mp3结尾，则放入数据源
						Mylog.i(arrFiles[i].getAbsolutePath()+"--------mp3   ----------"+ arrFiles[i].getName()); 
					}
				}
			}
		}
	}
//----------------------------------------------------------------------	
	/*毫秒转换为分钟的测试*/
	public void testMakeTimes(){
		int hour = 0,minute = 0,second = 0 ;
		
		long times = 253015;
		long mTimes = times/1000;  // 得到总的秒数
		if(mTimes > 60){
			minute = (int) (mTimes/60);
			second = (int) (mTimes%60);
		}
		if(minute > 60){
			hour = minute/60;
			minute = minute%60;
		}
		
		Mylog.d("====================  " + dillZore(hour)+ ":" + dillZore(minute) + ":" + dillZore(second));
	}
	
	public String dillZore(int date){
		if(date < 10){
			return "0" + date;
		}else{
			return "" + date;
		}
	}
//----------------------------------------------------------
}
