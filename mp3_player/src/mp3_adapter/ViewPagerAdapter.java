package mp3_adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
	
	private ArrayList<View> data = new ArrayList<View>();
	public void refreshData(ArrayList<View> data){
		this.data = data;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = data.get(position);
		container.addView(v);
		return v;
	}
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View v = data.get(position);
		container.removeView(v);
	}

}
