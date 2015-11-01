package senddatebetweenactivity;
/**
 * 实现Serializable接口(序列化)就可以直接通过Intent直接在Activity之间传递对象了
 */
import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private int age;
	private String sex;
	
	public Person(String name,int age,String sex){
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
