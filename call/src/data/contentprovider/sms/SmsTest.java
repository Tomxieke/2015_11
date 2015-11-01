package data.contentprovider.sms;

import mylog.Mylog;
import android.content.Context;
import android.database.Cursor;
import android.test.AndroidTestCase;
/*
 * 测试短信contentProvider 的使用
 */
public class SmsTest extends AndroidTestCase {
	private Context mContext;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getContext();
	}
	
	/*
	 * 从手机储存中去所有短信内容
	 */
	public void searchSms(){  
		Cursor cursor = mContext.getContentResolver().query(ToolClass.SMS.CONTENT_URI, null, null, null, null);
		if(cursor != null && cursor.getCount() > 0){
			Mylog.d("地址                " + "内容");
			while(cursor.moveToNext()){
				String address = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.ADDRESS));
				String body = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.BODY));
				Mylog.d( address+"               " + body);
			}
		}
	}
	
	
}
