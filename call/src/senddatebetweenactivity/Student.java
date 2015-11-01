package senddatebetweenactivity;
/**
 * 实现Parcelable接口也是可以直接传递对象的。
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
	private String name;
	private String number;
	private int age;
	
	public Student(String name,String number,int age){
		this.name = name;
		this.number = number;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
//  接口Parcelable中的两个方法  我们这里只需要是具体实现第二个方法
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {   //参数dest是写入数据的工具。要一一对应写入。
		dest.writeString(name);
		dest.writeString(number);
		dest.writeInt(age);
	}
	
/**
 * 还需要用下面的东西来读取，要与上面的写入顺序一一对应
 * 必须使用 public static final 修饰符
 * 对象必须用  CREATOR
 */
	public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){

		@Override
		public Student createFromParcel(Parcel source) {   //该方法里实现对象的读取
			String name = source.readString();
			String number = source.readString();
			int age = source.readInt();
			Student stu = new Student(name,number,age);
			return stu;
		}

		@Override
		public Student[] newArray(int size) {
			
			return null;
		}
		
	};

}
