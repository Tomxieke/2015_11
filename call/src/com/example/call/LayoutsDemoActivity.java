package com.example.call;
/**
 * java代码实现一个简单的布局管理器
 */
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class LayoutsDemoActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);	//实列化一个linearlayout对象
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		//设置layout的宽和高度
		Resources res = getResources();  //得到该工程的资源文件
		layout.setBackgroundColor(res.getColor(R.color.mybule));  //将背景颜色设置为资源文件中的颜色
		
		TextView textView = new TextView(this);//实列化Textview对象
		textView.setText(res.getString(R.string.demo_text_them));  //通过资源来设置显示的文字
		textView.setTextColor(res.getColor(android.R.color.white));  //通过调用android中的颜色资源来设置字体颜色
		
		
		layout.addView(textView);//将textview添加到layout.
		setContentView(layout);   //将该布局添加到该Activity上
		
	}
}
