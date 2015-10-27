package com.example.umpt;

import com.cleanmaster.umpt.CleanActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SystemActivity extends Activity{
	
	private Button btnCleanMaster;
	private Button btnSystemBack;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_system);   
        TextView Titletext;
        Titletext=(TextView)findViewById(R.id.title_text);
        Titletext.setText("系统优化");
        
        
        
        
      
        btnSystemBack=(Button)findViewById(R.id.back);
        btnCleanMaster=(Button)findViewById(R.id.cleanmaster);
        btnCleanMaster.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				Intent intent=new Intent(SystemActivity.this,CleanActivity.class);
				SystemActivity.this.finish();
				startActivity(intent);
				
			}});
        
        
         btnSystemBack.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				SystemActivity.this.finish();
			}});
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}