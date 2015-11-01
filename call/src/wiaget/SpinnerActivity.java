package wiaget;
/**
 * 下拉框的联系
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.call.R;

public class SpinnerActivity extends Activity {
	String[]  src = {"one","two","three","fore","five"};
	String[] values ={"one","one work","one mothe","one buty","one people","mother"};
	private Spinner mSpinner;
	private AutoCompleteTextView mAutoComTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_study);
		
		mSpinner = (Spinner) findViewById(R.id.view_spinner);	//下拉框
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>
		(this,R.layout.spinner_item_layout,src);
		mSpinner.setAdapter(spinnerAdapter);
		
		mAutoComTv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView); //文本选择提示框
		ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>
		(this,android.R.layout.simple_expandable_list_item_1,values);
		mAutoComTv.setAdapter(spinnerAdapter2);
		
	}
}
