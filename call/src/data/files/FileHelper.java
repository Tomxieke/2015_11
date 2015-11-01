package data.files;
/**
 * file 帮助类，实现对对象的写入和读出
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import data.sharedpreference.Dog;

public class FileHelper {
	private File mFlie;  // 要写入对象的文件目录
	private static FileHelper sHelper;
	public static FileHelper getInstance(Context context,String name){
		if(sHelper == null){
			sHelper = new FileHelper(context,name);
		}
		return sHelper;
	}
 
	private FileHelper(Context context,String name){   // name 为保存的文件名
		mFlie = new File(context.getFilesDir(),name);
	}
	/**
	 * 写入对象
	 * @param dog
	 */
	public void writeDogToFile(Dog dog){
		FileOutputStream out = null;
		DataOutputStream dout = null;
		try {
			out = new FileOutputStream(mFlie);
			dout = new DataOutputStream(out);
			dout.writeUTF(dog.getName()); 
			dout.writeUTF(dog.getSex());
			dout.writeInt(dog.getAge());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(dout != null){
				try {
					dout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Dog readDogFromFile() {
		FileInputStream in = null;
		DataInputStream dataIn = null;
		Dog dog = null;
		try {
			in = new FileInputStream(mFlie);
			dataIn = new DataInputStream(in);
			dog = new Dog();
			dog.setName(dataIn.readUTF());
			dog.setSex(dataIn.readUTF());
			dog.setAge(dataIn.readInt());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dataIn != null) {
				try {
					dataIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dog;
	}
}
