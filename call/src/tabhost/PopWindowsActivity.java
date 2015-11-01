package tabhost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.call.R;

public class PopWindowsActivity extends Activity {
	private TextView mtextView;
	private PopupWindow mPopupWindow;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popwindow_layout);
		mtextView = (TextView) findViewById(R.id.popwindow_btn);

		mPopupWindow = new PopupWindow(getPopView(), 300, 600); // 实列化PopupWindow
		mtextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				} else {
					mPopupWindow.showAsDropDown(v); // 显示PopupWindow
				}
			}
		});
		mPopupWindow.setFocusable(true); // 给popupWindow焦点后。外面的所有控件都不能获得焦点了。
		mPopupWindow.update();
		mPopupWindow.setOutsideTouchable(true);

		mPopupWindow.getContentView().setFocusableInTouchMode(true);
		mPopupWindow.getContentView().setFocusable(true);
		/*mPopupWindow.getContentView().setOnTouchListener(new OnTouchListener() { // mPopupWindow.getContentView()
																					// 得到pop的父视图并给其触摸
					// 或者点击事件让pop失去焦点

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						mPopupWindow.setFocusable(false); // 在pop窗口外点击或触摸时让其失去焦点
						mPopupWindow.dismiss();
						return true;
					}
				});*/
		
		mListView.setOnKeyListener(new OnKeyListener() { // 当pop框显示时点击返回键时没有用的。必须重新处理

					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {

						switch (keyCode) {
						case KeyEvent.KEYCODE_BACK:
							if (mPopupWindow != null
									&& mPopupWindow.isShowing()) {
								mPopupWindow.dismiss();
							}
							break;

						}
						return true;
					}
				});
	}
	
	
	
	public void showMypopListener(View v){
		MyPopupWindow pop = new MyPopupWindow(this);
		pop.showAtLocation(v, Gravity.CENTER, 0, 0);
		pop.setFocusable(true);
	}

	/**
	 * PopupWindow显示的View
	 * 
	 * @return
	 */
	public View getPopView() {
		View v = LayoutInflater.from(this).inflate(
				R.layout.popwindow_item_layout, null);
		mListView = (ListView) v;
		String[] city = { "北京", "上海", "成都", "重庆" };
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, city);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Toast.makeText(PopWindowsActivity.this,
							"1231414" + position, Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});

		return mListView;
	}
}



