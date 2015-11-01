package study_dialog;
/**
 * 自定义的Dialog  设置背景后可去掉边上的菱角
 */
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.call.R;

public class MyExitDialog extends AlertDialog {
	private Context context ;
	private Button mCancalBtn,mLoginBrn;
	public MyExitDialog(Context context) {
		super(context);
		this.context = context;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mydialog_contentview_layout);
		mLoginBrn = (Button) findViewById(R.id.myViewDialog_login_btn);
		mCancalBtn = (Button) findViewById(R.id.myViewDialog_cancal_btn);
		setCancalBtnListener(mCancalBtn);
		setLoginBtnListener(mLoginBrn);
	}
	
	public void setCancalBtnListener(Button button){
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
				dismiss();  //销毁提示框
			}
		});
	}
	
	public void setLoginBtnListener(Button button){
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
				dismiss();  //销毁提示框
			}
		});
	}

}
