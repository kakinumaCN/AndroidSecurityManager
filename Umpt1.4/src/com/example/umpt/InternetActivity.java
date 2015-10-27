package com.example.umpt;

import com.cleanmaster.umpt.CleanActivity;
import com.internet.umpt.InternetInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InternetActivity extends Activity{
	
	private Button btnInternetBack;
	private Button btnInternetInfo;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_internet);      
        TextView Titletext;
        Titletext=(TextView)findViewById(R.id.title_text);
        Titletext.setText("Á÷Á¿¿ØÖÆ");
        
        btnInternetBack=(Button)findViewById(R.id.back);
        btnInternetBack.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				InternetActivity.this.finish();
			}});
        
        btnInternetInfo=(Button)findViewById(R.id.internetinfo);
        btnInternetInfo.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				Intent intent=new Intent(InternetActivity.this,InternetInfoActivity.class);
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
