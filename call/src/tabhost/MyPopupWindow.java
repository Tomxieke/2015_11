package tabhost;

import com.example.call.R;

import android.content.Context;

import android.support.v4.view.ViewPager.LayoutParams;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;

import android.widget.PopupWindow;

public class MyPopupWindow extends PopupWindow {
	private View mRootLayout;
	private OnKeyListener mOnKeyListener;
	
	public MyPopupWindow(Context context){
		LayoutInflater inflater = LayoutInflater.from(context); 
		mRootLayout = inflater.inflate(R.layout.my_popwindow_layout, null);//popupwindow的布局文件
		setContentView(mRootLayout);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		mOnKeyListener = new OnKeyListener() {
			
			@Override
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				//Log.d("hzb", "MediaControlerPopupWindow  --- OnKeyListener --- "+keyCode);
				
				if(keyCode == KeyEvent.KEYCODE_BACK){
					dismiss();
				}
				return true;
			}

			
		};
		mRootLayout.setOnKeyListener(mOnKeyListener);
		mRootLayout.setFocusable(true);
		mRootLayout.setFocusableInTouchMode(true);
		setFocusable(true);
	}
}
