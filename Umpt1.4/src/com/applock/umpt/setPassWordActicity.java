package com.applock.umpt;

import com.example.umpt.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class setPassWordActicity extends Activity{

	private Button bt_normalt_dialog_ok,bt_normal_dialog_cancle;
	private EditText et_normal_entry_pwd;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setpass);

		sp=getSharedPreferences("AppLockSetting",Context.MODE_PRIVATE);
		
		bt_normalt_dialog_ok=(Button) findViewById(R.id.bt_normalt_dialog_ok);
		bt_normal_dialog_cancle=(Button) findViewById(R.id.bt_normal_dialog_cancle);
		et_normal_entry_pwd=(EditText) findViewById(R.id.et_normal_entry_pwd);
		
		bt_normalt_dialog_ok.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String password=et_normal_entry_pwd.getText().toString().trim();
				Editor ed = sp.edit();
				ed.putString("password", password);
				ed.commit();
				setPassWordActicity.this.finish();
						
			}
		});
		
		bt_normal_dialog_cancle.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
					finish();
			}
		});
	
		
	}
}
