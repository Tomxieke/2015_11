package music.mediaplayer;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.example.call.R;

public class MusicActivity extends Activity implements OnClickListener{
	private TextView mByIntentBtn,mbyMediaPlayerBtn,mbyMediaPlayerTwoBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.music_palyer_layout);
		
		initView();
		
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.music_byIntent_btn:
			byIntent();
			break;
		case R.id.music_byMediaPlayer_btn:
			byMediaPlayer();
			break;
		
		case R.id.music_byMediaPlayer_two_btn:
			byMediaPlayerTwo();
			break;
		}
	}
	/*系统封装的MediaPlayer方法，可以将音乐文件放到raw目录下*/
	public void byMediaPlayerTwo(){
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.beyondguanghuitime);
		mediaPlayer.start();
	}
	
	/*通过MediaPlayer类启动音乐播放*/
	public void byMediaPlayer(){
		String path = "file://" + Environment.getExternalStorageDirectory() + "/music/Beyond-never give up.mp3" ;
		MediaPlayer player = new MediaPlayer();
		try {
			
			player.setDataSource(path);  // 设置数据源
			player.prepare();  //准备播放
			player.start();  // 开始播放
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*通过Intent隐式调用启动音乐播放器*/
	public void byIntent(){
		String mp3FileDir = "file://";
		Uri uri = Uri.parse(mp3FileDir + Environment.getExternalStorageDirectory() + "/a.mp3");
		Intent intent =new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "audio/mp3");
		startActivity(intent);
	}
	/*实列化空间并注册监听*/
	public void initView(){
		mByIntentBtn = (TextView) findViewById(R.id.music_byIntent_btn);
		mByIntentBtn.setOnClickListener(this);
		mbyMediaPlayerBtn = (TextView) findViewById(R.id.music_byMediaPlayer_btn);
		mbyMediaPlayerBtn.setOnClickListener(this);
		mbyMediaPlayerTwoBtn = (TextView) findViewById(R.id.music_byMediaPlayer_two_btn);
		mbyMediaPlayerTwoBtn.setOnClickListener(this);
	}
}
