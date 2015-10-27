package com.dialog.umpt;

import com.example.umpt.CommunicationActivity;
import com.example.umpt.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogActivity extends Activity {

	
	private Button btnDialogOut;
	private Button btnDialogback;
	private TextView dntext;
	private Button dn1;
	private Button dn2;
	private Button dn3;
	private Button dn4;
	private Button dn5;
	private Button dn6;
	private Button dn7;
	private Button dn8;
	private Button dn9;
	private Button dn0;
	private Button dnx;
	private Button dnj;
	private Button dnr;
	
	private String mobile="";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        
        
        
        
        
        
        btnDialogback=(Button)findViewById(R.id.back);
        btnDialogback.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	DialogActivity.this.finish();
            	
			}});
        
        
        
        dntext=(TextView)findViewById(R.id.dntext);
        
        
        dnr=(Button)findViewById(R.id.dnr);
        dnr.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile="";
            	dntext.setText(mobile);
            	
			}});
        dn1=(Button)findViewById(R.id.dn1);
        dn1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"1";
            	dntext.setText(mobile);
            	
			}});
        
        dn2=(Button)findViewById(R.id.dn2);
        dn2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"2";
            	dntext.setText(mobile);
            	
			}});
        dn3=(Button)findViewById(R.id.dn3);
        dn3.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"3";
            	dntext.setText(mobile);
            	
			}});
        dn4=(Button)findViewById(R.id.dn4);
        dn4.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"4";
            	dntext.setText(mobile);
            	
			}});
        dn5=(Button)findViewById(R.id.dn5);
        dn5.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"5";
            	dntext.setText(mobile);
            	
			}});
        dn6=(Button)findViewById(R.id.dn6);
        dn6.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"6";
            	dntext.setText(mobile);
            	
			}});
        dn7=(Button)findViewById(R.id.dn7);
        dn7.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"7";
            	dntext.setText(mobile);
            	
			}});
        dn8=(Button)findViewById(R.id.dn8);
        dn8.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"8";
            	dntext.setText(mobile);
            	
			}});
        dn9=(Button)findViewById(R.id.dn9);
        dn9.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"9";
            	dntext.setText(mobile);
            	
			}});
        dn0=(Button)findViewById(R.id.dn0);
        dn0.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"0";
            	dntext.setText(mobile);
            	
			}});
        dnj=(Button)findViewById(R.id.dnj);
        dnj.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"#";
            	dntext.setText(mobile);
            	
			}});
        dnx=(Button)findViewById(R.id.dnx);
        dnx.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
            	mobile=mobile+"*";
            	dntext.setText(mobile);
            	
			}});
        
        
        
        
        
        
        btnDialogOut= (Button)this.findViewById(R.id.dialogbutton);
        // 点击button的事件
        btnDialogOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// 获取电话号码
				// 参数1：动作。在这指打电话
				// 参数2：提供给应用的数据。在这指电话号	
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
				// 将意图传给操作系统
				// startActivity方法专门将意图传给操作系统
				DialogActivity.this.startActivity(intent);
			}
		});
        
    }
}