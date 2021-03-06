package test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp3_player.R;

public class ViewPagerActivity extends Activity {
	private ViewPager mViewPager;
//	private static final int MSG_PAUSE_STATE = 1;  //暂停
//	private static final int MSG_XUNHUAN_STATE = 2; //循环
//	private static final int MSG_KEEP_STATE = 3;
	
	
	private Handler mHandler = new Handler(){   //这里获取所有消息并处理
		private int currentPager = 0; //当前先中页
		boolean flag = true;
		public void handleMessage(android.os.Message msg) {
			if(mHandler.hasMessages(1) && !flag){    //过滤掉第一条消息
				mHandler.removeMessages(1);
				flag = false;
			}
			switch(msg.what){
			case 1:
				currentPager++;
				mViewPager.setCurrentItem(currentPager);
				mHandler.sendEmptyMessageDelayed(1, 2000);
				
				break;
			case 2:
				currentPager = msg.arg1;
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_viewpager_layout);
		mViewPager = (ViewPager) findViewById(R.id.test_viewpager);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		ArrayList<View> data = new ArrayList<View>();
		
		View v1 = inflater.inflate(R.layout.test_viewpager_item1, null);
		View v2 = inflater.inflate(R.layout.test_viewpager_item2, null);
		View v3 = inflater.inflate(R.layout.test_viewpager_item3, null);
		data.add(v1);
		data.add(v2);
		data.add(v3);
		MyPagerAdapter adapter = new MyPagerAdapter();
		mViewPager.setAdapter(adapter);
		adapter.setData(data);
		
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mHandler.sendMessage(Message.obtain(mHandler,2,arg0,0)); //这里发送消息保证用户手动切换后也可以自动循环起来
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		/** 
         * 2147483647 / 2 = 1073741820 - 1  
         * 设置ViewPager的当前项为一个比较大的数，以便一开始就可以左右循环滑动 
         */  
        int n = Integer.MAX_VALUE / 2 % data.size();  
        int currentPager = Integer.MAX_VALUE / 2 - n;  
        
        mViewPager.setCurrentItem(currentPager);   //这里设置进入时这处于那个页卡
        
        mHandler.sendEmptyMessageDelayed(1, 2000); //每隔2秒切换一次
		
	}
	
	
	
	/*适配器*/
	public class MyPagerAdapter extends PagerAdapter{
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
}
