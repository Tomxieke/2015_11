package studytest;
/**
 * TextView文本上包含图片的操作的三种方式测试
 * 1、在TextView的xml布局中设置derwable属性并设置图片位置。弊端   图片位置单一，不灵活
 * 2、借用SpnnableString向文本之间加图片。灵活方便 常用
 * 3、新建ImageView加图片。没有什么实用性。
 */
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.call.R;

public class ImageInTextActivity extends Activity {
	private TextView mTextView;
	private EditText mEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageintext_layout);
		mTextView = (TextView) findViewById(R.id.imageInText_textview);
		setText(mTextView);
		
		mEditText = (EditText) findViewById(R.id.imageInText_editText);
	}
	
	
	public void setText(TextView textView){  //通过SpannableString向文本之间添加图片
		String srcString = "通过SpannableString向文本之间添加图片";
		
		Drawable d = getResources().getDrawable(R.drawable.weixin_icon2);  //取得图片资源
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());   // 设置重绘图片资源大小
		
		SpannableString spString = new SpannableString(srcString);
		spString.setSpan(new ImageSpan(d, ImageSpan.ALIGN_BASELINE), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //设置图片位置
		
		textView.setText (spString);
		
	}
	
	public void setImageListener(View v){
		 SpannableString spanString = new SpannableString(" ");  
		 Drawable d = getResources().getDrawable(R.drawable.img_buetyful);  
		// d.setBounds(left, top, right, bottom)   //三个int参数含义  left right距左右多少像素   top和bottom 距上下多少像素
		 d.setBounds(10, 0, 60, 60);  
		 ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);  
		 spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
		 mEditText.append(spanString);  
	}
	
}
