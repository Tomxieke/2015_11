package data.sharedpreference;

import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.Toast;

public class HelperTest extends AndroidTestCase {
	SharedPreferenceHelper helper;
	@Override
	protected void setUp() throws Exception {  // 最想执行的方法
		super.setUp();
		helper = SharedPreferenceHelper.getInstance(getContext());
	}
	
	@Override
	protected void tearDown() throws Exception {  //最后执行的方法
		super.tearDown();
	}
	
	public void TestAddDog(){
		Dog dog = new Dog();
		dog.setName("kit");
		dog.setAge(10);
		dog.setSex("mu");
		helper.addDog(dog);
	}
	
	public void testFindByName(){
		if(helper.findDogByName("k") == null){
			Log.v("test", "找不到");
		}else{
			Dog dog = helper.findDogByName("kit");
			Log.v("test", dog.getName()+"	"+dog.getAge()+"	"+dog.getSex());
		}
	}
	
}
