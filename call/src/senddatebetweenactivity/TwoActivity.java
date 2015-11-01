package senddatebetweenactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.call.R;

public class TwoActivity extends Activity {
	private TextView mTwoText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_databetweenactivity_two);
		mTwoText = (TextView) findViewById(R.id.sendMassgaeToActivityTextTwo);
		Intent intent = getIntent();			//得到上一个Activity传来的Intent对象。里面有我们传递的数据
		int type = intent.getIntExtra(ToolClass.TYPE, 2);   //得到类型以辨别数据是通过什么方式传过来的。
		switch(type){
		case ToolClass.ParameterType.TYPE_BASEDATE:     //ToolClass.ParameterType.TYPE_BASEDATE  工具类里定义的常量
			getDataByIntent(intent);
			break;
		case ToolClass.ParameterType.TYPE_BUNDLE:		//ToolClass.ParameterType.TYPE_BUNDLE  工具类里定义的常量
			getDataByBundle(intent);
			break;
		case ToolClass.ParameterType.TYPE_SERIALIZABLE:			//ToolClass.ParameterType.TYPE_SERIALIZABLE  工具类里定义的常量
			getObjectBySerializable(intent);
			break;
		case ToolClass.ParameterType.TYPE_PARCELABLE:			//ToolClass.ParameterType.TYPE_PARCELABLE  工具类里定义的常量
			getObjectByParcelable(intent);
			break;
		case ToolClass.ParameterType.TYPE_HAVERESULT:   //ToolClass.ParameterType.TYPE_HAVERESULT  工具类里定义的常量
			hasResultMethods(intent);
			break;
		}
		
	}
	
	public void getDataByIntent(Intent intent){  //  直接通过Intent加载数据传递
		if(intent != null){
		//	String  paramter = intent.getStringExtra("data");   //通过getStringExtra()方法和key值得到对应的values
			String  paramter = intent.getStringExtra(ToolClass.PARAMETER);  //ToolClass.PARAMETER 为工具类ToolClass中的常量
			mTwoText.setText("One传来的信息是" +"\r"+ paramter);
		}
		
	}
	
	public void getDataByBundle(Intent intent){  //接收通过Bundle加载的数据
		Bundle bundle = intent.getExtras();   //通过Intent得到的Bundle对象
		if(bundle != null){
	//		String data = bundle.getString("name");  //为让代码的封装性好，用常量来代替name
			String data = bundle.getString(ToolClass.PARAMETER);  //ToolClass.PARAMETER 为工具类ToolClass中的常量
			mTwoText.setText("One传来的信息是" +"\r"+ data);
		}
	}
	
	public void getObjectBySerializable(Intent intent){   //通过Serializable传来的对象在这个方法取得
	//	Person person = (Person) intent.getSerializableExtra("Serializable");   //通过Intent取到传来的对象并向下转型
		Person person = (Person) intent.getSerializableExtra(ToolClass.PARAMETER);//为让代码的封装性好，用常量来代替name  ToolClass.PARAMETER 为工具类ToolClass中的常量
		mTwoText.setText("姓名：" +person.getName() + ",性别:" + person.getSex() + ",年龄:" + person.getAge());  //文本框显示提示信息
		
	}
	
	public void  getObjectByParcelable(Intent intent){		//通过Parcelable传来的对象在这个方法取得
	//	Student stu = intent.getParcelableExtra("Parcelable");   //通过Intent取到传来的对象并向下转型
		Student stu = intent.getParcelableExtra(ToolClass.PARAMETER);
		mTwoText.setText("姓名：" +stu.getName() + ",数字:" + stu.getNumber() + ",年龄:" + stu.getAge());  //文本框显示提示信息
	}
	/**
	 * 有回传值的处理如下
	 * @param intent
	 */
	public void hasResultMethods(Intent intent){
		String str = intent.getStringExtra("RESULT");  //通过key取传递的数据
		mTwoText.setText(str);
	}
	
	public void returnResult(View v){  //向one返回数据
		String str = "这是two返回来的数据======";
		Intent intent = getIntent();  //可以将其定义为全局变量
		intent.putExtra("RETURN", str);  //一样采用意图对象返回。
		int resultCode = 2;  //请求码
		setResult(resultCode, intent);  //调用setResult()方法传递回传值。
		finish();   // 将该activityfinish掉
	}
	
}
