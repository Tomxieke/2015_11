package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.call.R;

public class ExpandableListActivity extends Activity {
	private ExpandableListView exListView;
	List<String> parentList; // 父标题。
	Map<String, List<String>> childMap; // 子标题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable_list_layout);
		exListView = (ExpandableListView) findViewById(R.id.expandableList);
		
		getData();  //设置数据源
		exListView.setAdapter(new MyExpandableListAdapter());
		

	}

	public void getData() {  //设置数据源
		parentList = new ArrayList<String>();
		parentList.add("parent1");
		parentList.add("parent2");
		parentList.add("parent3");

		childMap = new HashMap<String, List<String>>();

		List<String> list1 = new ArrayList<String>();
		list1.add("child1-1");
		list1.add("child1-2");
		list1.add("child1-3");
		childMap.put("parent1", list1);

		List<String> list2 = new ArrayList<String>();
		list2.add("child2-1");
		list2.add("child2-2");
		list2.add("child2-3");
		childMap.put("parent2", list2);

		List<String> list3 = new ArrayList<String>();
		list3.add("child3-1");
		list3.add("child3-2");
		list3.add("child3-3");
		childMap.put("parent3", list3);

	}
	
	class MyExpandableListAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {  //返回父标题的个数
			return parentList.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {  // 获取当前父标题下子标题的个数
			String key = parentList.get(groupPosition);
			return childMap.get(key).size();
		}

		@Override
		public Object getGroup(int groupPosition) {  //获取当前父Item的数据
			return parentList.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {  // 返回子Item需要关联的数据
			String key = parentList.get(groupPosition);
			return childMap.get(key).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {  //返回父Item的id
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) { //返回子Item的id
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		/**
		 * 父Item布局
		 */
		public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = LayoutInflater.from(ExpandableListActivity.this).inflate(R.layout.expanlistview_parent_layout, null);
			}
			
			TextView txt = (TextView) convertView.findViewById(R.id.parent_textview);
			txt.setText(parentList.get(groupPosition));
			return txt;
		}

		@Override
		/*子Item布局
		 * (non-Javadoc)
		 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View, android.view.ViewGroup)
		 */
		public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
			String key = parentList.get(groupPosition);
			String info = childMap.get(key).get(childPosition);
			if(convertView == null){
				convertView = LayoutInflater.from(ExpandableListActivity.this).inflate(R.layout.expanlistview_child_layout, null);
			}
			TextView txt = (TextView) convertView.findViewById(R.id.child_textview);
			txt.setText(info);
			return txt;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
	}
	
}
