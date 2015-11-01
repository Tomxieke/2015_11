package call_back;

import android.util.Log;

/**
 * 回调机制
 * @author scxh
 *
 */
public class Boos implements CallBackInterface{

	@Override
	public void call(String str) {
		Log.v("test", "Boss收到消息");
	}

}
