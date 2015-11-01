package study_menu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.example.call.R;

public class ContentMenuActivity extends Activity {
	private ListView mListView;
	ArrayAdapter<String>  adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_content_menu_layout);
		
	
		
		mListView = (ListView) findViewById(R.id.content_menu_listview);
		String[] strData = {"长按弹出context菜单","第二项","第三项","第四项"};
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strData);
		mListView.setAdapter(adapter);
		registerForContextMenu(mListView);
	}
	@Override
	/**
	 * 上下文菜单  当长按选中项时才会弹出菜单
	 */
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.content_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	/**
	 * 弹出菜单的监听事件
	 */
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		String itemData = adapter.getItem(info.position);    // 找到点击的是哪一项 才弹出来的context菜单
		switch(item.getItemId()){   //  点击的是弹出菜单的哪一项
		
		
		
		case R.id.content_item1_menu:
			Toast.makeText(this, "context菜单   修改成功" + itemData, Toast.LENGTH_SHORT).show();
			break;
		case R.id.content_iten2_menu:
			Toast.makeText(this, "context菜单   删除成功" + itemData, Toast.LENGTH_SHORT).show();
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	/**
	 * 点击按钮  弹出popmenu菜单  
	 * @param v  按钮
	 * 二级菜单  在xml中某一项菜单里面添加item设置二级菜单
	 */
	public void menuBtnListener(View  v){
		PopupMenu pop = new PopupMenu(this, v);  //V 该弹出对话框添加在那个view上。
	//	MenuInflater menuInflater = getMenuInflater();    //这种方式是3.0之前的用法  
	//	menuInflater.inflate(R.menu.popmenu_menu, pop.getMenu());  //3.0之后可以采用下面的方式
		pop.inflate(R.menu.popmenu_menu);
		pop.show();  //调用show()让菜单显示  
		pop.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch(item.getItemId()){
				case R.id.pop_menu_one:
					Toast.makeText(ContentMenuActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
					break;
				case R.id.pop_menu_two:
					Toast.makeText(ContentMenuActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
					break;
				}
				return false;
			}
		});
	}
	
	
	/**
	 * 二级菜单监听  在xml中嵌套menu
	 * @param v
	 */
	public void twoMenuListener(View v){
		PopupMenu pop = new PopupMenu(this, v);  //V 该弹出对话框添加在那个view上。
		pop.inflate(R.menu.two_menu);
		pop.show();  //调用show()让菜单显示  
	}
}
