package study_menu;

import studytest.ImageInTextActivity;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.call.R;

public class OptionMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_optionmenu_layout);
	}
	
	@Override
	/**
	 * 创建菜单项
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.option_menu, menu);  // 解析xml文件为menu  在xml中设置弹出菜单有几项。当然也可以用代码直接添加（用的少）
		menu.add(1, 12, 3, "add添加的项");  // menu.add(groupId(属于那个菜单组), itemId（id）, order(显示位置), title（文本显示条目）)
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	/**
	 * menu 的监听事件处理方法
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();  // 取得是点击的菜单中的那个选项的id
		switch(id){
		case R.id.option_menu_item1:
			Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.option_menu_item2:
			Toast.makeText(this, "确定要退出", Toast.LENGTH_SHORT).show();
			break;
		case 12:
		//	Intent intent = new Intent(this,ImageInTextActivity.class);  //  隐式调用启动activity
			Intent intent = new Intent();
			intent.setComponent(new ComponentName(this, ImageInTextActivity.class));  //显示调用启动activity
			startActivity(intent);
			break;
		case R.id.option_menu_item3:
			Intent intent1 = new Intent();
			intent1.setAction(Intent.ACTION_DIAL);   //  设置action 启动activity   manifest里要设置相应的<intent-filter>  <action  <category  属性
            
			intent1.setData(Uri.parse("tel:123456"));
			startActivity(intent1);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
