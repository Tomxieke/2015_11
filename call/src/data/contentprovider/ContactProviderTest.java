package data.contentprovider;

import java.util.Map;

import mylog.Mylog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;

public class ContactProviderTest extends AndroidTestCase {
	ContentResolver contentResolver;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		contentResolver = getContext().getContentResolver();
	}
	
	
	public void testFindContact() {
		// String uriString = "content://com.android.contacts/contacts";
		// //可以通过这种Uri.parse()的方式得到Uri.
		// Uri strUri = Uri.parse(uriString);
		// 1、得到Uri 这里是系统的Uri
		Uri uri = ContactsContract.Contacts.CONTENT_URI; // content://com.android.contacts/contacts
		// 2、实列化ContentResolver
		 contentResolver = getContext().getContentResolver();
		// 3、用ContentResolver对象来执行增、删、查、改。。。
		Cursor c = contentResolver.query(uri, null, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				String name = c
						.getString(c
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				Mylog.v(name);
			}
		}
	}
	
	public void testMyContentProvider() {
		Mylog.v("开始插入");
		ContentValues values = new ContentValues();
		values.put("id", 110);
		values.put("username", "小强");
		values.put("password", "10010");
		contentResolver.insert(MyContentProvider.CONTENT_URI, values);
		Mylog.v("插入完毕，开始查询");

		Cursor c = contentResolver.query(MyContentProvider.CONTENT_URI, null,
				null, null, null); // Uri 参数就是自定义的Uri
		if (c != null) {
			while (c.moveToNext()) {
				String name = c.getString(c.getColumnIndex("username"));
				String password = c.getString(c.getColumnIndex("password"));
				Mylog.v("姓名：    " + name + "   密码：     " + password);
			}
		}
		Mylog.v("查询完毕");
	}
	
	//---------------------------------------------------------------------------------------------------------
	public void testGetValuesFromContentValues(){   //  从ContentValues 里面取数据
		ContentValues values = new ContentValues();
		values.put("name", "Rose");
		values.put("password", "abc");
		
		for(String key : values.keySet()){  //values.keySet() 返回key值得集合
			String name =  values.getAsString(key);
			Mylog.i("key    " + key +"   " + "values         "  + name);
		}
	}
	
	
	public void testGetValuesFromSharedPreferences(){  //从SharedPreferences 里面取数据
		SharedPreferences sharedPreferences = getContext().getSharedPreferences("teacher", getContext().MODE_PRIVATE);
		sharedPreferences.edit().putString("name", "老王").putString("password", "123").commit();
		
		Map<String,?> map = sharedPreferences.getAll();  // 返回sharedPreferences数据组成的Map.
		
		for(String key : map.keySet()){   //map.keySet()  返回key值对应的集合
			String value = (String) map.get(key);  
			Mylog.i("key:   " + key + "  ,  " + "value:   " + value);
		}
	}
	
	
	public void testAddTeacherByTeacherContentProvider(){
		ContentValues values = new ContentValues();
		values.put("name", "邦德");
		values.put("id", "007");
		contentResolver.insert(TeacherContentProvider.TEACHER_URI, values);
		
	}
	
	public void testFindTeacherByTeacherContentProvider(){
		Cursor c = contentResolver.query(TeacherContentProvider.TEACHER_URI, null, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				String name = c.getString(c.getColumnIndex("name"));
				String password = c.getString(c.getColumnIndex("id"));
				Mylog.v("姓名：    " + name + "   id：     " + password);
			}
		}
	}
	
	//--------------------------------------------------------------------------------------------------------
}
