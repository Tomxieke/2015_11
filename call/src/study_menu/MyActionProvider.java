package study_menu;

import com.example.call.R;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class MyActionProvider extends ActionProvider {
	private Context mContext;

	public MyActionProvider(Context context) {
		super(context);
		this.mContext = context;
	}

	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		super.onPrepareSubMenu(subMenu);
		subMenu.clear();  //  刷新
		subMenu.add("item1").setIcon(R.drawable.ic_search_autocomplete)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(mContext, "该项被选中了", Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});

		subMenu.add("item2").setIcon(R.drawable.ic_search_autocomplete)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(mContext, "该项被选中了222", Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});
	}
	
	@Override
	public boolean hasSubMenu() {
		return true;
	}
}
