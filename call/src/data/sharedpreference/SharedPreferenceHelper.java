package data.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferenceHelper 工具类，对对象的一个保存，可以这样来封装，
 * 因为是个工具类，所以一般情况下定义为单列模式来设计这个类
 * @author scxh
 *
 */
public class SharedPreferenceHelper {
	private static final String FILE_NAME = "data.sharedprefrence";  //数据保存的文件名一般用包名加后缀来命名
	private  SharedPreferences mSharedPreferences;
	
	private static SharedPreferenceHelper sHelper;  // 私有 static
	public static SharedPreferenceHelper getInstance(Context context){     // 私有 static
		if(sHelper == null){
			sHelper = new SharedPreferenceHelper(context);
		}
		return sHelper;
	}
	
	private SharedPreferenceHelper(Context context){ 
		mSharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
	}
	
	/*
	 * 向文件中保存对象
	 */
	public void addDog(Dog dog){
		Editor editor = mSharedPreferences.edit();
		editor.putString("name", dog.getName());
		editor.putString("sex", dog.getSex());
		editor.putInt("age", dog.getAge());
		editor.commit();
	}
	/*
	 * 通过名字找对象
	 */
	public Dog findDogByName(String name){
		Dog dog = new Dog();
		if(mSharedPreferences.getString("name", "").equals(name)){
			dog.setName(name);
			dog.setAge(mSharedPreferences.getInt("age", 1));
			dog.setSex(mSharedPreferences.getString("sex", ""));
		}else{
			return null;
		}
		return dog;
	}
}
