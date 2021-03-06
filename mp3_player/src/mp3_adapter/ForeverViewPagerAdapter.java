package mp3_adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ForeverViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> data = new ArrayList<View>();
	public void setData(ArrayList<View> data) {
		this.data = data;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;   //这里返回一个最大数
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		position = position % data.size();  // 0%3=0,1%3=1,2%3=2,3%3=0,4%3=1,5%3=2,6%3=0,........
		
		View v = data.get(position);
		if(v.getParent() != null){
			container.removeView(v);
		}
		container.addView(v);
		return v;
	}
}


