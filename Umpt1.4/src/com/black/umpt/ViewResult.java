package com.black.umpt;

import java.util.ArrayList;
import java.util.List;

import com.example.umpt.R;




import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//此Activity用于显示LISTVIEW即黑名单列表
public class ViewResult extends Activity {
	MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewresult);
		dbHelper = new MyDatabaseHelper(this 
				, "mydatabase.db3" , null, 1);
		final ListView listview = (ListView)findViewById(R.id.listView1);
		listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData())); 
		if(getData().isEmpty()){
			Toast.makeText(ViewResult.this, "该黑名单中无号码，请添加"
					, 5000).show();
			Intent intent = new Intent(ViewResult.this,Choice.class);
			intent.putExtra("phone", "kong");
			startActivity(intent);
			finish();
		}
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//@SuppressWarnings("unchecked")
				//List<String> data = (List<String>)listview.getItemAtPosition(arg2);
				 

				Intent intent = new Intent(ViewResult.this,Choice.class);
				intent.putExtra("phone",arg0.getItemAtPosition(arg2).toString());
				startActivity(intent);
				finish();
			}
		});
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		Cursor cursor =dbHelper.getReadableDatabase().rawQuery(
				"select * from List",null);
		while(cursor.moveToNext()){
			data.add(cursor.getString(1));
		}
		
		return data;
	}
	
	
}
