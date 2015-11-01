package application;

import android.app.Application;
/**
 * 1、全局初始化操作  2、定义的变量为全局变量，每个activity里都可以访问getApplication()方法得到Application实例    通过实例.的方式访问定义的变量
 * @author scxh
 *
 */
public class MyApplication extends Application {
	public String strApp = "Application中的全局变量";
	@Override
	public void onCreate() {
		super.onCreate();
		
	}
}
