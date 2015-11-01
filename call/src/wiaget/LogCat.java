package wiaget;
/**
 * 学习LogCat的输出功能
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LogCat extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.v("tag", "Log.v()");
		Log.d("tag", "Log.d()");
		Log.i("tag", "Log.i()");
		Log.w("tag", "Log.w()");
		Log.e("tag", "Log.e()");
	}
}
