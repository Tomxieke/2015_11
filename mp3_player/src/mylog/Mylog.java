package mylog;

import android.util.Log;

/**
 * 将log日志进行封装，在使用测试时更方便。直接用类名调用方法并且只需要传一个需要打印的字符串参数即可
 * @author scxh
 *
 */

public class Mylog {
	private static final String TAG = "test";
	public static boolean flag = true;  // if == true 显示日志      false则不显示日志
	
	public static void v(String msg){
		if(flag){
			Log.v(TAG, msg);
		}
	}
	
	public static void i(String msg){
		if(flag){
			Log.i(TAG, msg);
		}
	}
	
	public static void d(String msg){
		if(flag){
			Log.d(TAG, msg);
		}
	}
	
	public static void e(String msg){
		if(flag){
			Log.e(TAG, msg);
		}
	}
}
