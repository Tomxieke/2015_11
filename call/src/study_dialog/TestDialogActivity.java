package study_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.call.R;

public class TestDialogActivity extends Activity implements OnClickListener{
	private ProgressDialog mprogressDialog;
	private Button mprogressBtn,mDateDialogBtn,mTimeDialogBtn,mAlertDialogBtn,
					mSingleChoseDialogBtn,mMydialogBtn,mExtendsDialogBtn,mToastDialogBtn;
	private Dialog myViewDialog = null;  //用setView()方法自定义对话框  匿名内部类中要关闭对话框需要final型，但实列化有在后面。有冲突，所以定义为全局变量
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_study_layout);
		mprogressBtn = (Button) findViewById(R.id.dialog_progress_btn);
		mDateDialogBtn = (Button) findViewById(R.id.dialog_datePickerDialog_btn);
		mTimeDialogBtn = (Button) findViewById(R.id.dialog_timePickerDialog_btn);
		mAlertDialogBtn = (Button) findViewById(R.id.dialog_alertDialog_btn);
		mSingleChoseDialogBtn = (Button) findViewById(R.id.dialog_singleChoseDialog_btn);
		mMydialogBtn = (Button) findViewById(R.id.dialog_myDialog_btn);
		mExtendsDialogBtn = (Button) findViewById(R.id.dialog_myExtendsDialog_btn);
		mToastDialogBtn = (Button) findViewById(R.id.dialog_toastDialog_btn);
		
		mToastDialogBtn.setOnClickListener(this);
		mExtendsDialogBtn.setOnClickListener(this);
		mMydialogBtn.setOnClickListener(this);
		mSingleChoseDialogBtn.setOnClickListener(this);
		mAlertDialogBtn.setOnClickListener(this);
		mTimeDialogBtn.setOnClickListener(this);
		mprogressBtn.setOnClickListener(this);
		mDateDialogBtn.setOnClickListener(this);
	}
//-------------------------不调用show()方法，直接在activity上启动对话框-------------------------------------------------------	
	@Override
	protected Dialog onCreateDialog(int id) {  //activity上的对话框  以id来识别该启动那个对话框
		switch(id){
		case 1:
			return new AlertDialog.Builder(this).setTitle("标题").setMessage("是否退出系统").setPositiveButton("退出", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).create();
		case 2:
			return new MyExitDialog(this);
		}
		return null;
	}
	
	public void activityDialogOne(View v){  //启动第一个对话框的按钮事件
		showDialog(1);
	}
	
	public void activityDialogTwo(View v){  //启动第二个对话框的按钮事件
		showDialog(2);
	}
//---------------------------------------------------------------------------------------------
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_progress_btn:
			setProgressDialog();
			break;
		case R.id.dialog_datePickerDialog_btn:
			setDatePickDialog();
			break;
		case R.id.dialog_timePickerDialog_btn:
			setTimePickerDialog();
			break;
		case R.id.dialog_alertDialog_btn:
			setAlertDialog();
			break;
		case R.id.dialog_singleChoseDialog_btn:
			setSingleChoseDialog();
			break;
		case R.id.dialog_myDialog_btn:
			setMyContentDialog();
			break;
		case R.id.dialog_myExtendsDialog_btn:
			setExtendsDialog();
			break;
		case R.id.dialog_toastDialog_btn:
			setMyToastDialog();
			break;
		}
	}
	
	
	public void setMyToastDialog(){
		Toast myToast = new Toast(this);
		View toastView = LayoutInflater.from(this).inflate(R.layout.toast_mytoastview_layout, null);
		myToast.setView(toastView);  //将自定义的View加载到Toast
		myToast.setGravity(Gravity.BOTTOM, 0, 0);  //设置Toast显示的相对位置，前一个参数大体位置，后面两个参数基于该位置左右偏移
		myToast.setDuration(Toast.LENGTH_LONG);  //设置显示时间长短
		myToast.show();
		
	}
	
	/*
	 * 自定义 相对于setView()自定义而言    继承AlertDialog设置显示的布局则可以去除系统的风格。后面没有小线分割并且设置背景可完全覆盖。
	 *  
	 */
	public void setExtendsDialog(){
		MyExitDialog dialog = new MyExitDialog(this);
		dialog.show();
		/*View view = dialog.getListView();  //通过getlistView()方法得到View再来设置监听
		Button sureBtn = (Button) view.findViewById(R.id.myViewDialog_login_btn);
		sureBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(TestDialogActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
			}
		});*/
	}
	
/*
 * setView()自定义对话框 ，底部的背景也有系统自带的白色方块。设置背景后周边的菱角也存在。要取点则要
 * 自定义Dialog 重写里面的setContentView 方法
 */
	public void setMyContentDialog(){
		
		AlertDialog.Builder  alertBuilder = new AlertDialog.Builder(this);
		View contentView = LayoutInflater.from(this).inflate(R.layout.mydialog_contentview_layout, null);   //将xml文件解析成View
		alertBuilder.setView(contentView);		//设置对话框内容的View
		final EditText userName = (EditText)contentView.findViewById(R.id.myViewDialog_userName_edit);
		final EditText password = (EditText)contentView.findViewById(R.id.myViewDialog_password_edit);
		Button cancalBtn = (Button) contentView.findViewById(R.id.myViewDialog_cancal_btn);
		Button loginBtn = (Button) contentView.findViewById(R.id.myViewDialog_login_btn);
		
		loginBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userNameStr = userName.getText().toString();
				String passwordStr = password.getText().toString();
				if(userNameStr.equals("123") && passwordStr.equals("123")){
					Toast.makeText(TestDialogActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
					myViewDialog.dismiss();
				}else{
					Toast.makeText(TestDialogActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
					myViewDialog.dismiss();
				}
			}
		});
		
		cancalBtn.setOnClickListener(new View.OnClickListener() {  //设置取消按钮监听
			@Override
			public void onClick(View v) {
				myViewDialog.dismiss();
			}
		});
		
		myViewDialog = alertBuilder.create();
		myViewDialog.show();
		
	}
	
	/*
	 * 单选对话框
	 */
	public void setSingleChoseDialog(){
		AlertDialog.Builder  alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setIcon(R.drawable.img_person);  //设置Icon图标
		alertBuilder.setTitle("单选对话框");
		alertBuilder.setSingleChoiceItems(new String[]{"男","女"}, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestDialogActivity.this, "which" + which, Toast.LENGTH_SHORT).show();
			}
		});
		alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {   //要与OnClickListener上面的OnClickListener区别开，加上类名
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(TestDialogActivity.this, "which" + which, Toast.LENGTH_SHORT).show();
			}
		});
		alertBuilder.show();  // 启动对话框
	}
	
	/**
	 * 有按钮的提示对话框
	 */
	public void setAlertDialog(){
		AlertDialog.Builder  alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setIcon(R.drawable.img_person);  //设置Icon图标
		alertBuilder.setTitle("提示对话框");
		alertBuilder.setMessage("这个是提示信息显示的区域");
	//--------------------------对话框下面的三个按钮setPositiveButton ->setNeutralButton->setNegativeButton  系统自定义的自动从左到右排列
		alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {   //要与OnClickListener上面的OnClickListener区别开，加上类名
			
			@Override
			public void onClick(DialogInterface dialog, int which) {   // which ==1
				Toast.makeText(TestDialogActivity.this, "which" + which, Toast.LENGTH_SHORT).show();
			}
		});
		alertBuilder.setNeutralButton("继续", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {	// which ==3
				Toast.makeText(TestDialogActivity.this, "which" + which, Toast.LENGTH_SHORT).show();
			}
		});
		
		alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {	// which ==2
				Toast.makeText(TestDialogActivity.this, "which" + which, Toast.LENGTH_SHORT).show();
			}
		});
	//---------------------------------------------------------------------------------------------------------------------
		alertBuilder.show();
		
	}
	/*
	 * 时间对话框
	 */
	public void setTimePickerDialog(){
		TimePickerDialog timeDialog = new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Toast.makeText(TestDialogActivity.this,"time" + hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
			}
		}, 12, 24, true);
		timeDialog.show();
	}
	/**
	 * 日期对话框
	 */
	public void setDatePickDialog(){
		DatePickerDialog dateDialog = new DatePickerDialog(this, new OnDateSetListener() {  // 参数也可以用匿名内部类事项
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				Toast.makeText(TestDialogActivity.this, "year"+year+monthOfYear+dayOfMonth, Toast.LENGTH_SHORT).show();
			}
		}, 2015, 10, 14);
		dateDialog.show();  // 启动该对话框
	} 
	/*
	 * 进度条对话框progressDialog的设置方法
	 */
	public void setProgressDialog(){
		mprogressDialog = new ProgressDialog(this);
		mprogressDialog.setIndeterminate(false);  //设置进度条为确定型或不确定型
		mprogressDialog.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_background_selecter)); //设置进度条的颜色
		mprogressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);   // 设置为水平的进度条
		mprogressDialog.show();
		new Thread(new Runnable() {  // 启动线程来使进度条开始加载进度
			@Override
			public void run() {
				for (int i = 1; i <= 100; i++) {
					mprogressDialog.setProgress(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				mprogressDialog.dismiss();  // 让对话框显示
			}
		}).start();
	}
}
