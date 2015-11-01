package call_back;

import android.util.Log;

public class Work {
	public CallBackInterface mcallback;  // 声明接口类，以便于另一个类实现通信
	
	public void setCallBack(CallBackInterface callBackInterface){  //声明接口类
		this.mcallback = callBackInterface;   
	}
	
	public void doSomeThing(){  // 员工开始做工作，通过接口通知老板
		for (int i = 0; i < 10; i++) {
			Log.v("test", "员工工作"+i);
		}
		
		mcallback.call("工作完成了，通知老板"); //通过接口方法 来与另一个类进行通信
	}
	
}
