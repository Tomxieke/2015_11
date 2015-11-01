package observer;



/**
 * 被观察者接口  主题对象。所有的观察者都应该实现这个接口
 * @author scxh
 *
 */
public interface ObserveredInter {
	
	public void addObserver(ObserverInter o);  // 添加观察者
	
	public void removeObserver(ObserverInter o); // 移除观察者
	
	public void notifyMessage();  // 向所有观察者发送消息
}
