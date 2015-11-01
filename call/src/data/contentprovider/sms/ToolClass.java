package data.contentprovider.sms;

import android.net.Uri;
import android.provider.BaseColumns;

public class ToolClass {
	/*
	 * 短信相关的uri
	 */
	public interface SMS extends BaseColumns{  //继承BaseColumns 就不用设置”_id“ 了。
		public static final Uri CONTENT_URI = Uri.parse("content://sms");
		public static final String FILTER = "!imichat";
		public static final String TYPE = "type";
		public static final String THREAD_ID = "thread_id";
		public static final String ADDRESS = "address";
		public static final String PERSON_ID = "person";
		public static final String DATE = "date";
		public static final String READ = "read";
		public static final String BODY = "body";
		public static final String PROTOCOL = "protocol";
	}
}
