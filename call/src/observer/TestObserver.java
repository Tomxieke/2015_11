package observer;
/**
 * 单元测试 。首先配置Manifest  
 * <instrumentation android:targetPackage="com.example.call" android:name="android.test.InstrumentationTestRunner"></instrumentation>
 * <uses-library android:name="android.test.runner"/>
 * 让后在测试类中继承AndroidTestCase类
 * 
 */
import android.test.AndroidTestCase;

public class TestObserver extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testObserver(){
		ObserverOne one = new ObserverOne();  //实列化一个观察者
		ObserverTwo two = new ObserverTwo();//实列化第二个观察者
		
		ObserveredOne edOne = new ObserveredOne();  // 实列化被观察者
		 edOne.addObserver(one);  // 被观察者添加观察者
		 edOne.addObserver(two); 
		 
		 edOne.creatMessage("谢谢大家的关注"); // 被观察者创建消息
		 edOne.notifyMessage();  //被观察者发送消息
		 
	}
}
