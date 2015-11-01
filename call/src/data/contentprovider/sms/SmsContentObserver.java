package data.contentprovider.sms;
/**
 * 继承ContentObserver  信息观察者模式。onChange里面实现通知观察者  被观察者正在执行的操作
 */
import java.util.HashMap;

import mylog.Mylog;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

public class SmsContentObserver extends ContentObserver {
	private Context mContext;
	private Handler mHandler;

	public SmsContentObserver(Context context,Handler handler) {
		super(handler);
		this.mContext = context;
		this.mHandler = handler;
	}
	
	@Override
	public void onChange(boolean selfChange) {  // 当数据发生改变时就会执行这个方法
		super.onChange(selfChange);
		Mylog.e("收到信息          SmsContentObserver           onChange   ");
		String[] projection = new String[]{ToolClass.SMS.ADDRESS,ToolClass.SMS.BODY,ToolClass.SMS.DATE,ToolClass.SMS.PERSON_ID};  //查询后需要得到字段
		String selection = ToolClass.SMS.ADDRESS + " =  ?";  //查询条件
		String[] selectionArgs = new String[]{"110"};  // 条件值
		Cursor cursor = mContext.getContentResolver().query(ToolClass.SMS.CONTENT_URI, projection, selection, selectionArgs, null);
		
		if(cursor != null && cursor.getCount() > 0){
			cursor.moveToFirst();  //  最新的一条数据，游标直接定位到最新的一条数据
			String address = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.ADDRESS));
			String body = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.BODY));
			String date = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.DATE));
			String person_id = cursor.getString(cursor.getColumnIndex(ToolClass.SMS.PERSON_ID));
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("address", address);
			map.put("body", body);
			map.put("date", date);
			map.put("person_id", person_id);
			
			Message msg = Message.obtain();
			msg.what = 110;
			msg.obj = map;
			mHandler.sendMessage(msg);  // 发送消息交由Handler处理
		}
		
	}
	
	

}
