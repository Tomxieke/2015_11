package database;
/**除了用命令行新建数据库之外。还可以用代码来操作
 * SQLiteOpenHelper android系统提供的操作数据库的类
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteDBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "database.db";//数据库名字
	private static final int DB_VERSION = 1;  //数据库版本号
	/*
	 * 初始化操作 Context context , String name（数据库名字）,CursorFactory factory, int version（版本号方便修改数据库）
	 */
	public MySQLiteDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);  // 对要建的数据库进行初始化
	}

	@Override
	/**
	 * 初始化创建数据库
	 */
	public void onCreate(SQLiteDatabase db) {  //创建数据库要执行的代码 。。只执行一次
		Log.v("db", "onCreate-----------------");
		String sql = "create table user (id int,username varchar(20),password varchar(50))";  //若要把Id设为自增 怎类型应该为INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
		db.execSQL(sql);  //执行创建表的SQL语句
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  // 
		
	}

}
