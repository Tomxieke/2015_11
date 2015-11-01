package observer;
/**
 * 实现被观察者接口。  那么这就是一个具体的被观察者了
 */
import java.util.ArrayList;
import java.util.Observer;

public class ObserveredOne implements ObserveredInter {
	 ArrayList<ObserverInter> list = new ArrayList<ObserverInter>();  //用来装观察了该被观察者的观察者
	 String message;

	public void addObserver(ObserverInter o) {   // 可参照源码package java.util.Observable来设计代码的逻辑性
		if (o == null) {              //为空抛出空指针异常，不为空且不包括的时候才添加
            throw new NullPointerException();
        }
        synchronized (this) {
            if (!list.contains(o))
            	list.add(o);
        }
	}


	public void removeObserver(ObserverInter o) {
		list.remove(o);  // 移除观察者
	}

	
	public void notifyMessage() {  //向所有观察者发送消息
		for (ObserverInter o: list) {
			o.getMessage(message);
		}
	}
	
	public void creatMessage(String msg){  //创建要向观察者发送的消息
		this.message = msg;
	}
}
