package app.login.ui;

import layout.TableLayoutActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.call.R;

public class WeiXinLogin extends Activity {
	private  EditText mLoginUserEditText ;
	private EditText mLoginPasswordEditText;
	private Button mLoginUserDeleteBtn;
	private Button mLoginPasswordDeleteBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixin_login_layout);
		mLoginUserEditText = (EditText) findViewById(R.id.edittext1_weixin);
		mLoginUserDeleteBtn = (Button) findViewById(R.id.weixin_delete_Button1);
		mLoginPasswordEditText = (EditText) findViewById(R.id.edittext2_weixin);
		mLoginPasswordDeleteBtn = (Button) findViewById(R.id.weixin_delete_Button2);
		isLookDelete();
		userDeleteBtn();
		passwordDeleteBtn();
		
	}
	
	public void isLookDelete(){ 			//输入框后面的删除按钮要等到edittext中有内容的时候才显示出来
		mLoginPasswordEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!mLoginPasswordEditText.getText().toString().equals("")){
					mLoginPasswordDeleteBtn.setVisibility(BIND_AUTO_CREATE);
				}else{
					mLoginPasswordDeleteBtn.setVisibility(TRIM_MEMORY_UI_HIDDEN);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		mLoginUserEditText.addTextChangedListener(new TextWatcher() {   //用户名框的删除键的处理
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {  //输入时调用的方法
				if(!mLoginUserEditText.getText().toString().equals("")){
					mLoginUserDeleteBtn.setVisibility(View.VISIBLE);  //设置按钮可见,参数有两种方式，1、View.VISIBLE  2、BIND_AUTO_CREATE  
				}else{														//好像第一种方法是比较普遍的一种方式
					mLoginUserDeleteBtn.setVisibility(View.INVISIBLE);		//设置按钮不可见
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,		//输入前调用的方法
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {  //输入后调用的方法
				
			}
		});
	}
	
	
	
	public void userDeleteBtn(){
		mLoginUserDeleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mLoginUserEditText.setText("");	
			}
		});
	}
	
	public void passwordDeleteBtn(){
		mLoginPasswordDeleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mLoginPasswordEditText.setText("");	
			}
		});
	}
}
