package com.example.umpt;

import com.appinformation.umpt.AppSizeActivity;
import com.software.umpt.SoftwareInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SoftwareActivity extends Activity{
	
	private Button btnSoftwareBack;
	private Button btnSoftwareInfo;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_software);   
        TextView Titletext;
        Titletext=(TextView)findViewById(R.id.title_text);
        Titletext.setText("»Ìº˛π‹¿Ì");
        
        btnSoftwareBack=(Button)findViewById(R.id.back);
        btnSoftwareBack.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				SoftwareActivity.this.finish();
			}});
        
        btnSoftwareInfo=(Button)findViewById(R.id.softwareinfo);
        btnSoftwareInfo.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	Intent intent=new Intent(SoftwareActivity.this,AppSizeActivity.class);
				startActivity(intent);
			}});
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
