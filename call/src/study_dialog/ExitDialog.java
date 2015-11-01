package study_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.call.R;

public class ExitDialog extends AlertDialog {
	private Context context;
	private Activity activity;
	private Button mCancleBtn,mSureBtn;
	public ExitDialog(Context context,Activity activity) {  //构造方法传必要参数
		super(context);
		this.context = context;
		this.activity = activity;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { //  onCreate()方法设置自定义的布局
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_mydialog_exit);
		mCancleBtn = (Button) findViewById(R.id.main_cancal_btn);
		mSureBtn = (Button) findViewById(R.id.main_sure_btn);
		
		setCancleBtnListener(mCancleBtn);  //  取消监听
		setSureBtnListener(mSureBtn); // 确定监听
	}
	
	public void setSureBtnListener(Button button){  // 确定事件
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				activity.finish();
			}
		});
	}
	
	public void setCancleBtnListener(Button button){
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	

}
