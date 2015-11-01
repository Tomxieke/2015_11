package senddatebetweenactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.call.R;

public class OneActivity extends Activity {
	private String mParameter;		//要穿的数据
	private TextView mOneText;
	private Button mOneBtn1,mOneBtn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_databetweenactivity_one);
		mOneText = (TextView) findViewById(R.id.sendMassgaeToActivityTextOne);
		mOneBtn1 = (Button) findViewById(R.id.sendMassgaeToActivityBtnOne1);
		mOneBtn2 = (Button) findViewById(R.id.sendMassgaeToActivityBtnOne2);
		mParameter = mOneText.getText().toString();
		sendDateByBundle();  //调用监听方式的方法
		
	}
	
	public void sendStringToActivityByIntent(View v){				//传基本数据类型  采用android让其跳转
		Intent intent = new Intent(this,TwoActivity.class);		//声明要传递给那个activity
//		intent.putExtra("type", 1);                            //作为标记，以便于下一个Activity判断其是通过什么类型传递过来的。。测试专用
		intent.putExtra(ToolClass.TYPE, ToolClass.ParameterType.TYPE_BASEDATE); //为使代码封装性好，可以采用工具类ToolClass中的一些常量来定义这些标签比如
//		intent.putExtra("data", mParameter);		//调用方法传数据并有标识，跟Map类似。一个key值对应一个Values值。
		intent.putExtra(ToolClass.PARAMETER, mParameter);	//所有的数据的key值都用工具类ToolClass中的常量来定义，那么代码就应该是前面这样了
		startActivity(intent);				//开始activity
	}
	
	public void sendDateByBundle(){  //通过Bundle对象传递数据到下一个activity  采用监听的方式
		
		mOneBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();//新建一个Bundle对象
		//		bundle.putString("name", "老王"); 		//向Bundle对象中添加我们的数据
				bundle.putString(ToolClass.PARAMETER, "老王");	//所有的数据的key值都用工具类ToolClass中的常量来定义，那么代码就应该是前面这样了
				Intent intent = new Intent(OneActivity.this,TwoActivity.class);  //实例化意图对象
		//		intent.putExtra("type", 2);   //为使代码健壮，各常量采用工具类ToolClass里定义的常量  如下。
				intent.putExtra(ToolClass.TYPE, ToolClass.ParameterType.TYPE_BUNDLE);
				intent.putExtras(bundle);  //向意图对象中直接添加带数据的Bundle对象
				startActivity(intent);   //启动
			}
		});
	}
	
	
	public void sendDataBySerializable(View v){     //通过实现Serializable接口直接传递对象到下一个activity
		Intent intent = new Intent(this,TwoActivity.class);		//声明要传递给那个activity
	//	intent.putExtra("type", 3);                            //作为标记，以便于下一个Activity判断其是通过什么类型传递过来的。。测试专用
		intent.putExtra(ToolClass.TYPE, ToolClass.ParameterType.TYPE_SERIALIZABLE);   
		Person person = new Person("张三",25,"男");
	//	intent.putExtra("Serializable", person);          //还是调用putExtra()并加载数据
		intent.putExtra(ToolClass.PARAMETER, person);
		startActivity(intent);
	}
	
	
	public void sendDataByParcelable(View v){
		Intent intent = new Intent(this,TwoActivity.class);		//声明要传递给那个activity
	//	intent.putExtra("type", 4);                            //作为标记，以便于下一个Activity判断其是通过什么类型传递过来的。。测试专用
		intent.putExtra(ToolClass.TYPE, ToolClass.ParameterType.TYPE_PARCELABLE);
		Student stu = new Student("小红","1234567", 15);
	//	intent.putExtra("Parcelable", stu);
		intent.putExtra(ToolClass.PARAMETER, stu);
		startActivity(intent);
	}
	
	/**
	 * 有回传值的方法实现1、将activity设置成单任务模式  2、启动方式不一样。有回传值的采用startActivityForResult(intent, requestCode)方法启动
	 * 3、读取回传值用onActivityResult(int requestCode, int resultCode, Intent data)方法。
	 */
	public void isReturnDataMethod(View v){  //有回传值的按钮监听
		Intent intent = new Intent(this,TwoActivity.class);
	//	intent.putExtra("TYPE",5); 
		intent.putExtra(ToolClass.TYPE, ToolClass.ParameterType.TYPE_HAVERESULT); // 先加载类型参数
		String str = "有回传值的启动Activity方式->将数据传递给two";
		intent.putExtra("RESULT", str);
		int requestCode = 1;  //请求码，任意int型数字，在two中回传数据会用到
		startActivityForResult(intent, requestCode);   // 启动方法不一样。为startActivityForResult(intent, requestCode)。
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   //该方法用来接收回传值。通过设置的requestCode 和 resultCode
		super.onActivityResult(requestCode, resultCode, data);             //来确定是哪个activity传递来的回传值。暂时这么理解
		String result = data.getStringExtra("RETURN");   //通过getStringExtra(key)取对应的vaules
		mOneText.setText(result);
	}
	
}
