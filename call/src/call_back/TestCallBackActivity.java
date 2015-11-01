package call_back;

import android.app.Activity;
import android.os.Bundle;

public class TestCallBackActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Boos boss = new Boos();
		Work work = new Work();
		work.setCallBack(boss); //调用对应方法。参数为接口实现类 这样两个类就建立联系了
		work.doSomeThing();  //消息是在这个方法里面发送的
	}
}
