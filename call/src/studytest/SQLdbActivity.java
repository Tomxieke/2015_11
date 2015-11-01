package studytest;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class SQLdbActivity extends Activity {
	private EditText mNameEdit,mPasswordEdit;
	private Button mInsertBtn,mSearchBtn,mSearchCursorBtn;
	SQLiteDatabase db;
	private ListView mListview;
	Cursor cursor ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlone_layout);
		mNameEdit = (EditText) findViewById(R.id.sql_name_edit);
		mPasswordEdit = (EditText) findViewById(R.id.sql_password_edit);
		mInsertBtn = (Button) findViewById(R.id.sql_insert_btn);  //插入
		mSearchBtn = (Button) findViewById(R.id.sql_search_btn);	//查询
		
		mSearchCursorBtn = (Button) findViewById(R.id.sql_search_cursor_btn);
		mListview = (ListView) findViewById(R.id.sql_cursorAdapter_listview);
		
		createDataBase();  //创建数据库
		
		mInsertBtn.setOnClickListener(new OnClickListener() {   // 插入记录监听
			@Override
			public void onClick(View v) {
				insertStudent();
				Toast.makeText(SQLdbActivity.this, "数据录入成功", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		mSearchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<Student> list = searchStudent();   //查询到数据集 并保存到ArrayList
				Intent intent = new Intent(SQLdbActivity.this,SqlTwoActivity.class);
				intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) list);   // 将取得的值传给第二个activity
				startActivity(intent);
				finish();
			}
		});
		
		
		
		mSearchCursorBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//String[] columns = new String[]{"_id","username"};
				cursor = db.query("user", null, null, null, null, null, null);
			//	Log.v("test", ""+cursor.getInt(cursor.getColumnIndex("_id")));
				
				MyCursorAdapter adapter = new MyCursorAdapter(SQLdbActivity.this,cursor);
				mListview.setAdapter(adapter);
			}
		});
		
		
		
	}
	
	
	/*
	 * 查询数据库中的条目
	 */
	public ArrayList<Student> searchStudent(){
		ArrayList<Student> list = new ArrayList<Student>();
		String[] columns = new String[]{"username","password"};
		cursor = db.query("user", columns, null, null, null, null, null);
		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				Student stu = new Student();
				stu.setName(cursor.getString(cursor.getColumnIndex("username")));
				stu.setPassword(cursor.getString(cursor.getColumnIndex("password")));
				list.add(stu);
			}
		}
		return list;
	}
	
	/*
	 * 创建数据库 student
	 */
	public void createDataBase(){  
		SqlHelper dbHelper = new SqlHelper(this);  //得到helper对象
		db = dbHelper.getReadableDatabase();
	//	db.execSQL("insert into teacher  values(1,'小红')");  //向表中插入记录
	}
	
	public void insertStudent(){   // 插入语句 操作的是mydatabase.db数据库
		//每次测试都会执行setUp()方法里的代码。得到db数据库
		ContentValues values = new ContentValues();  
		values.put("username", mNameEdit.getText().toString());    
		values.put("password",mPasswordEdit.getText().toString());
		db.insert("user", null, values);  // 执行插入语句 ，参数分别为表名、null、插入内容
	}
	
	
	@Override
	protected void onDestroy() {  //关闭数据库
		super.onDestroy();
		if(db != null){
			db.close();
		}
	}
	
	/*
	 * CursorAdapter android系统自带的工具类，专门用来处理查询数据库后得到的数据集Cursor对象的类
	 * 用这个类来处理查询结果的话，数据库对应表中的第一个字段必须是  _id 字段。不然报错
	 */
	public class MyCursorAdapter extends CursorAdapter{  
		private LayoutInflater mInflater;
		public MyCursorAdapter(Context context, Cursor c) {
			super(context, c, true);
			
			mInflater = LayoutInflater.from(context);
		}
		

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return mInflater.inflate(R.layout.sql_liseview_layout, null);
		}

		@SuppressLint("CutPasteId") @Override
		public void bindView(View view, Context context, Cursor cursor) {  // view 为item的view
			TextView txtName = (TextView) view.findViewById(R.id.list_name);
			TextView txtpassword = (TextView) view.findViewById(R.id.list_password);
			String name = cursor.getString(cursor.getColumnIndex("username"));
			String password = cursor.getString(cursor.getColumnIndex("password"));
			txtName.setText(name);
			txtpassword.setText(password);
		}
		
	}
}
