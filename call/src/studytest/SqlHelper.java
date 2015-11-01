package studytest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "database.db";
	private static final String TABLE_ID = "_id";
	private static final int DB_VERSION = 1;
	private static final String PRIMARY_KAY = "   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
	public SqlHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user (" + TABLE_ID + PRIMARY_KAY + ",username varchar(20),password varchar(50))";  //若要把Id设为自增 怎类型应该为INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
		db.execSQL(sql);  //执行创建表的SQL语句
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //数据库版本跟新时执行
		
	}

}
