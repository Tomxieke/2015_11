package database;
/**
 * 路径为data/data/应用名
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class JUiteTestDB extends AndroidTestCase {
	private SQLiteDatabase db;
	@Override
	protected void setUp() throws Exception {  // 测试的初始化操作，每次执行测试代码是将先执行这里面的代码
		super.setUp();
		MySQLiteDBHelper dbHelper = new MySQLiteDBHelper(getContext());  //得到helper对象
		db = dbHelper.getReadableDatabase(); //两个方法都可以打开数据库，readable方法在空间充足时打开可写不报错，空间不足时打开不可写不报错
	//	db = dbHelper.getWritableDatabase();  //Writable方法在空间充足时打开可写不报错，不足时直接报错
	}
	
	public void testDataBase(){
		SQLiteDatabase db = getContext().openOrCreateDatabase("call.db", getContext().MODE_PRIVATE, null);  //创建数据库
		db.execSQL("create table teacher (id int,name varchar(50))"); //向该数据库中添加表
		db.execSQL("insert into teacher  values(1,'小红')");  //向表中插入记录
	}
	
	
	public void testDBHelper(){   //通过系统的SQLiteOpenHelper 来创建数据库
		MySQLiteDBHelper dbHelper = new MySQLiteDBHelper(getContext());  //得到helper对象
		SQLiteDatabase db = dbHelper.getReadableDatabase();  // 通过helper对象得到数据库
	}
	// 增
	public void testAddUser(){   // 插入语句 操作的是mydatabase.db数据库
		//每次测试都会执行setUp()方法里的代码。得到db数据库
		ContentValues values = new ContentValues();  // andorid 系统定义的类用来拆解SQL语句
		values.put("id", 2);
		values.put("username", "张三");
		values.put("password", "10010");
		db.insert("user", null, values);  // 执行插入语句 ，参数分别为表名、null、插入内容
		
		String sql = "insert into user values(1,'李四','10011')";
		db.execSQL(sql);   // 用原始SQL语句进行插入
	}
	
	
	
	public void testDeleteUser(){//删
		String whereClause = "id = ?";  //  条件字段
		String[] whereArgs = new String[]{"2"}; //条件值
		db.delete("user", whereClause, whereArgs);
	}
	
	public void testUpdataUser(){  // 改
		ContentValues  values = new ContentValues();
		values.put("username", "王五");  // 要修改的字段以及修改的内容
		String whereClause = "password = ?";  //标识字段
		String[] whereArgs = new String[]{"10011"};  //标识字段的值
		db.update("user", values, whereClause, whereArgs);  //执行
	}
	
	public void findUser(){  // 查找  返回的是一个集合。。需要遍历，跟原SQL返回的结果集类似
		String[] columns = new String[]{"id","username","password"};  // 查找后需要返回的字段
		String selection = new String("id = ?");  //查找的条件字段
		String[] selectionArgs = new String[]{"1"};//查找条件的值
		Cursor curser = db.query("user", columns, selection, selectionArgs, null, null, "id asc"); 
		if(curser.getCount() > 0){  // 取出来的结果有元素时才执行下面的代码
			Log.v("db", "id----" +"name"+"------password");
			while(curser.moveToNext()){
				int id = curser.getInt(curser.getColumnIndex("id"));
				String name = curser.getString(curser.getColumnIndex("username"));
				String password = curser.getString(curser.getColumnIndex("password"));
				
				Log.v("db", id +"    " +name + "    "+password);
			}
		}
	}
}
