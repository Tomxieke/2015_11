package mp3_adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import android.widget.TextView;

public class MyTextView extends TextView {

	public MyTextView(Context context) {
		super(context);
	}
	
	
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public boolean isFocused() {
		return true;
	}
}
