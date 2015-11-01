package data.contentprovider;
/**
 * 自定义ContentProvider暴露了增、删、改、查的接口。主要是供调用的。不关心具体的实现过程。该类采用的是用数据库保存数据 实现对数据库的增、删、查、改
 */
import database.MySQLiteDBHelper;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
	private static final String AUTHORITY = "data.contentprovider.MyContentProvider";   // 这个东西是权限，在Manifest中注册该主键时需要设置这个权限。
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);  // 得到用于与数据库联系起来的Uri
	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		MySQLiteDBHelper dbHelper = new MySQLiteDBHelper(getContext());  //得到helper对象
		db = dbHelper.getReadableDatabase(); 
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		return db.query("user", projection, selection, selectionArgs, null, null, null);
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		db.insert("user", null, values);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
		return 0;
	}

}
