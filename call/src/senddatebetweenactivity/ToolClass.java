package senddatebetweenactivity;
/**
 * 该类中为一些常量值，让其他几个类调用  类似于android的R类
 * public static final 的运用
 * @author scxh
 *
 */

public class ToolClass {
	
	public interface ParameterType{ 		// 参数类型常量
		public static final int TYPE_BASEDATE = 1;  //基本数据类型传递
		public static final int TYPE_BUNDLE = 2;	//Bundle传递基本数据类型
		public static final int TYPE_SERIALIZABLE = 3;		//实现serializable接口来传递对象
		public static final int TYPE_PARCELABLE = 4;		//实现parcelable接口来传递对象
		public static final int TYPE_HAVERESULT = 5;    //有回传值的类型
	}
	
	public static final String TYPE = "TYPE";
	public static final String PARAMETER = "PARAMETER";
}
