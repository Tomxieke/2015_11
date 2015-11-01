package study_menu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.call.R;

public class ActionBarActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar_layout);
		ActionBar actionBar = getActionBar();   
//		actionBar.hide();  //  设置ActionBar为隐藏
		actionBar.setDisplayHomeAsUpEnabled(true);  // 显示系统中actionbar的返回键
		
	}
	
	@Override
	/**
	 * actionProvider  应用
	 */
	public boolean onCreateOptionsMenu(Menu menu) {  
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);   //  实列化ActionBar菜单
	//	MenuItem searchItem = menu.findItem(R.id.actionbar_one);    
		MenuItem shareItem = menu.findItem(R.id.actionbar_share);  //得到实列
	//	ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();    //  调用系统的一个分享的Provider
		MyActionProvider provider = (MyActionProvider) shareItem.getActionProvider();    // 自定义的Provider
	//	provider.setShareIntent(getDefaultIntent());  // 调用系统的方法
		return super.onCreateOptionsMenu(menu);
	}
	
	private Intent getDefaultIntent() {  
		Intent intent = new Intent(Intent.ACTION_SEND);  
		intent.setType("image/*");  
		return intent;
	}

	
	@Override
	/**
	 * menu的点击事件
	 */
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch(item.getItemId()){
		
		case android.R.id.home:   // 点击的是返回键时
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if(NavUtils.shouldUpRecreateTask(this, upIntent)){
				TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
			}else{
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
		//	finish();
			break;
		case R.id.actionbar_one:
		//	startActivity(new Intent(this,ContentMenuActivity.class));   //启动Activity但不消失 。一边下一个dame来区别ActionBar的返回键和屏幕下方的返回键的区别
			
			break;
		case R.id.actionbar_share:
			
			Toast.makeText(this, "actionbar  该选项已点击", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actionbar_two:
			Toast.makeText(this, "actionbar  该选项已点击", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actionbar_three:
			Toast.makeText(this, "actionbar  该选项已点击", Toast.LENGTH_SHORT).show();
			break;
		case R.id.actionbar_four:
			startActivity(new Intent(this,ActionBarActivity.class));    // 再一次启动该activity 直接点击Actionbar的返回键就能直接退到主界面
			//而界面下方的返回键就只弹出栈顶的一个activity
			Toast.makeText(this, "actionbar  该选项已点击", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
