package player_by_cursor;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mp3_player.R;
/**
 * 采用系统提供的contentprovider uri来扫描加载外部储存的歌曲。
 * 提供了MP3文件的很多信息字段。
 * @author scxh
 *
 */
public class MusicCursorListActivity extends Activity {
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.music_cursor_activity_listview_layout);
		
		mListView = (ListView) findViewById(R.id.cursor_listview);
		//  MediaStore.Audio.Media.EXTERNAL_CONTENT_URI--系统提供的一个contentprovider的外部储存uri 
		Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, 
				null, null, MediaStore.Audio.Media.TITLE_KEY);  //MediaStore.Audio.Media.TITLE_KEY 按这个排序
		
		MusicCursorListViewAdapter adapter = new MusicCursorListViewAdapter(this,cursor);
		mListView.setAdapter(adapter);
	}
	
	/*cursor适配器*/
	public class MusicCursorListViewAdapter extends CursorAdapter{
		private LayoutInflater inflater;
		public MusicCursorListViewAdapter(Context context, Cursor c) {
			super(context, c, true);
			inflater = LayoutInflater.from(context);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView musicNameTxt = (TextView)view.findViewById(R.id.cursor_item_musicName_txt);
			TextView musicPlayerTxt = (TextView)view.findViewById(R.id.cursor_item_musicPlayerName_txt);
			
			musicNameTxt.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));   //从MediaStore
			musicPlayerTxt.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return inflater.inflate(R.layout.muxic_cursorlist_item_layout, null);
		}
		
	}
}