package data.contentprovider;
/**
 * 采用sharedpreference 保存数据形式
 */
import java.util.Map;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class TeacherContentProvider extends ContentProvider {
	private static final String AUTHORITY = "data.contentprovider.sharedpervience";
	public static final Uri TEACHER_URI = Uri.parse("content://" + AUTHORITY);
	private SharedPreferences sharepreference ;  //保存数据的sharepreference
	@Override
	public boolean onCreate() {
		sharepreference = getContext().getSharedPreferences("teacherprovider", getContext().MODE_PRIVATE);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		String[]  columnNames = new String[]{"name","id"};
		MatrixCursor cursor = new MatrixCursor(columnNames);
		
		Map<String,?> map = sharepreference.getAll();   //得到sharepreference里所有数据的map
		String[]  array =new String[2];
		int i = 0;
		for(String key : map.keySet()){
			String value = (String) map.get(key);
			array[i++] = value;
		}
		cursor.addRow(array);    // 这里添加新行需要一个数组，所有构建了一个。一组一组数据添加
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {   // 向sharepreference 插入数据
		Editor editor = sharepreference.edit();
		for(String key : values.keySet()){
			editor.putString(key, values.getAsString(key));
		}
		editor.commit();
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
	
	@Override
	public String getType(Uri uri) {
		return null;
	}

}
