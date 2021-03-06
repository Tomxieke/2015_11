package mylog;

import mp3_main.MainActivity;
import mp3_main.MusicListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.mp3_player.R;

public class Mp3TabHostActivity extends TabActivity {
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mp3_tabhost_main_layout);
		initView();
	}
	
	public void initView(){
		mTabHost = getTabHost();
		
		TabSpec tabSpec1 = mTabHost.newTabSpec("tab1");
		tabSpec1.setIndicator("主页");
		tabSpec1.setContent(new Intent(this,MainActivity.class));
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2 = mTabHost.newTabSpec("tab2");
		tabSpec2.setIndicator("本地曲目");
		tabSpec2.setContent(new Intent(this,MusicListActivity.class));
		mTabHost.addTab(tabSpec2);
	}
}
