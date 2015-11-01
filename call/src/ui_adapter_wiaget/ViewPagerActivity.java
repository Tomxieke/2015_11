package ui_adapter_wiaget;
/**
 * 左右滑动viewpager。。首次安装app的导航界面
 */
import java.util.ArrayList;

import progess_bar.ProgressBarActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.call.R;

public class ViewPagerActivity extends Activity {
	private ViewPager mViewPager;
	private ImageView mImage1,mImage2,mImage3,mImage4;
	private ArrayList<View> listData ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_layout);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);  //  实列化ViewPager  v4包中。包名加类名导入
		mImage1 = (ImageView) findViewById(R.id.viewpager_img1);
		mImage2 = (ImageView) findViewById(R.id.viewpager_img2);
		mImage3 = (ImageView) findViewById(R.id.viewpager_img3);
		mImage4 = (ImageView) findViewById(R.id.viewpager_img4);
	//-------------------------数据源为View对象----------------------------------------------------------	
		listData = new ArrayList<View>(); 
		View v1 = LayoutInflater.from(this).inflate(R.layout.viewpager_item1_layout, null);
		listData.add(v1);
		
		View v2 = LayoutInflater.from(this).inflate(R.layout.viewpager_item2_layout, null);
		listData.add(v2);
		
		View v3 = LayoutInflater.from(this).inflate(R.layout.viewpager_item3_layout, null);
		listData.add(v3);
		
		View v4 = LayoutInflater.from(this).inflate(R.layout.viewpager_item4_layout, null);
		listData.add(v4);
		
		MyPagerAdapter adapter = new MyPagerAdapter();
		adapter.setData(listData);  
		
		mViewPager.setAdapter(adapter);
		this.listener(mViewPager);
	}
	
	public void btnListener(View v){   //  点击按钮跳过引导界面 启动新的activity
		Intent intent = new Intent(this,ProgressBarActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void listener(ViewPager viewPager){   //ViewPager监听事件
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {	
			@Override
			public void onPageSelected(int arg0) {   //选中的时候执行
				Log.w("test", "--------onPageSelected--------"  + arg0);
				switch(arg0){
					case 0:
						mImage1.setImageResource(R.drawable.viewpager_icon_on);
						mImage2.setImageResource(R.drawable.viewpager_icon_off);
						break;
					case 1:
						mImage1.setImageResource(R.drawable.viewpager_icon_off);
						mImage2.setImageResource(R.drawable.viewpager_icon_on);
						mImage3.setImageResource(R.drawable.viewpager_icon_off);
						break;
					case 2:
						mImage2.setImageResource(R.drawable.viewpager_icon_off);
						mImage3.setImageResource(R.drawable.viewpager_icon_on);
						mImage4.setImageResource(R.drawable.viewpager_icon_off);
						break;
					case 3:
						mImage3.setImageResource(R.drawable.viewpager_icon_off);
						mImage4.setImageResource(R.drawable.viewpager_icon_on);
						break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {  // 滚动切换的时候执行
				Log.d("test", "--------onPageScrolled--------"  + arg0 +"   " + arg2);
				/*if(arg0 == listData.size()-1 && arg2 == 0){   //当界面处于引导界面的最后一页，并且有滑动操作时跳转新的Activity
					Intent intent = new Intent(ViewPagerActivity.this,ProgressBarActivity.class);
					startActivity(intent);
				}*/
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {  //滚动切换后执行
				
			}
		});
	}
	
	public class MyPagerAdapter extends PagerAdapter{
		ArrayList<View> data = new  ArrayList<View>();
		public void setData(ArrayList<View> data ){
			this.data = data;
			notifyDataSetChanged();   //  s刷新数据
		}
		@Override
		public int getCount() {   //子数据有多少项
			return data.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		//	return super.instantiateItem(container, position);
		//	Log.v("test", "--------instantiateItem()--------------" + position);
			View v = data.get(position);      //  得到数据源对应位置的数据，就是一个view对象 
			container.addView(v);   //  将该view对象添加到这个ViewGroup上
			return v;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {  // 销毁对象view
			//super.destroyItem(container, position, object);
		//	Log.v("test", "--------destroyItem()--------------" + position);
			View v = data.get(position);  // 得到要销毁的view对象
			container.removeView(v);   //将其移除container中。
		}
	}
	
}
