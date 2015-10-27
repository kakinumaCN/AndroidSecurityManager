package com.example.umpt;

import com.black.umpt.JieListActivity;
import com.callsattribution.umpt.AttributionActivity;
import com.dialog.umpt.DialogActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CommunicationActivity extends Activity{
	
	private Button btnCommunicationBack;
	
	private Button btnDialog;
	
	private Button btnblack;
	
	private Button btnattribution;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_communication);   
        
        
        
        btnDialog=(Button)findViewById(R.id.communication_to_dialog);
        
        
        
        
        btnCommunicationBack=(Button)findViewById(R.id.back);
        btnblack=(Button)findViewById(R.id.black_menu);
        btnattribution=(Button)findViewById(R.id.calls_attribution);
        btnCommunicationBack.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
				CommunicationActivity.this.finish();
            	
			}});
        btnblack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(CommunicationActivity.this,AttributionActivity.class);
				startActivity(intent);
            	
			}
		});
        btnattribution.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(CommunicationActivity.this,JieListActivity.class);
				startActivity(intent);
            	
			}
		});
        TextView Titletext;
        Titletext=(TextView)findViewById(R.id.title_text);
        Titletext.setText("通讯保护");
        
        btnDialog.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	Intent intent=new Intent(CommunicationActivity.this,DialogActivity.class);
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


