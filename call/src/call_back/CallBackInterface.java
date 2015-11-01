package call_back;
/**
 * 回调机制接口，用来实现两个类之间的通信
 * @author scxh
 *
 */
public interface CallBackInterface {
	
	public void call(String str);  //一个类实现方法，决定类之间联系的方式。另一个类声明接口并调用方法通知实现接口方法类
}
