package observer;

import android.util.Log;

/**
 *观察者一。 实现观察者接口。
 * @author scxh
 *
 */
public class ObserverOne implements ObserverInter {

	@Override
	public void getMessage(String msg) {
		Log.v("ob", "ObserverOne"+msg);    // 观察者观察到被观察者行为后要执行的动作
	}

}
