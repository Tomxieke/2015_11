package mp3_adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicBean implements Parcelable{
	private String musicPath;
	private String musicName;
	
	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(musicPath);
		arg0.writeString(musicName);
	}
	
	/**
	 * 还需要用下面的东西来读取，要与上面的写入顺序一一对应
	 * 必须使用 public static final 修饰符
	 * 对象必须用  CREATOR
	 */
		public static final Parcelable.Creator<MusicBean> CREATOR = new Parcelable.Creator<MusicBean>(){

			@Override
			public MusicBean createFromParcel(Parcel source) {   //该方法里实现对象的读取
				String path = source.readString();
				String name= source.readString();
				MusicBean bean = new MusicBean();
				bean.setMusicPath(path);
				bean.setMusicName(name);
				return bean;
			}

			@Override
			public MusicBean[] newArray(int size) {
				return null;
			}
			
		};

}
