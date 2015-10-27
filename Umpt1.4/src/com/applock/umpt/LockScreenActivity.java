package com.applock.umpt;



import com.example.umpt.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LockScreenActivity extends Activity {
	private ImageView iv_app_lock_pwd_icon;
	private TextView tv_app_lock_pwd_name;
	private EditText et_app_lock_pwd;
	private String realpwd;
	private SharedPreferences sp;
	private IService iservice;
	private Intent i;
	private String packname;
	private MyConn myconn;
	
	private ActivityManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogpass);
		  i=new Intent();
		   i=LockScreenActivity.this.getIntent();
		manager =(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		myconn = new MyConn();
		
		Intent stopIntent = new Intent(this,WatchDogService.class);
		bindService(stopIntent, myconn, BIND_AUTO_CREATE); //绑定
		
		sp=getSharedPreferences("AppLockSetting", MODE_PRIVATE);
		
		//拿到激活这个activity程序 传过来的包名
	    packname=getIntent().getStringExtra("packname"); 
		realpwd=sp.getString("password","");
		
		iv_app_lock_pwd_icon=(ImageView) findViewById(R.id.iv_app_lock_icon);
		tv_app_lock_pwd_name=(TextView) findViewById(R.id.tv_app_lock_pwd_name);
		et_app_lock_pwd=(EditText) findViewById(R.id.et_app_lock_pwd);
		
		//完成桌面的初始化
		ApplicationInfo appinfo;
		try {
			appinfo=getPackageManager().getPackageInfo(packname, 0).applicationInfo;
			Drawable appicon=appinfo.loadIcon(getPackageManager());
			String appname=appinfo.loadLabel(getPackageManager()).toString();
			iv_app_lock_pwd_icon.setImageDrawable(appicon);
			tv_app_lock_pwd_name.setText(appname);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 确定按钮对应的点击事件
	 */
	public void confirm(View view){
		//得到用户输入的密码
		String password=et_app_lock_pwd.getText().toString().trim();
		if(TextUtils.isEmpty(password)){
			Toast.makeText(this, "密码不能为空", 1).show();
			return;
		}else{
			if(password.equals(realpwd)){
			
				//临时停止
				iservice.callAppProtectStop(packname);
				//正确
				finish();
				
			}else{
				Toast.makeText(this, "密码错误", 1).show();
			}
		}
	}
	public void noconfirm(View view){
		if (packname.equals("") == false) {
			System.out.println("要kill掉的包名是:" + packname);
			manager.killBackgroundProcesses(packname);
			Intent backhome = new Intent("android.intent.action.MAIN");
			backhome.addCategory("android.intent.category.HOME");
			backhome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(backhome);
			LockScreenActivity.this.finish();
			this.finish();
		//	}
			
			
		}

	}
	
	/**
	 * 阻止按键事件继续向下分发
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
			return false;
		}
		if(KeyEvent.KEYCODE_HOME==keyCode){
			System.out.println("按下home键位");
			
			manager.killBackgroundProcesses(packname);
			Intent backhome = new Intent("android.intent.action.MAIN");
			backhome.addCategory("android.intent.category.HOME");
			backhome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(backhome);
			this.finish();
			LockScreenActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public void onAttachedToWindow() {
		  this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	       super.onAttachedToWindow();
	}
	
	/**
	 * 显示的解除绑定
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(myconn);
	}
	
	private class MyConn implements ServiceConnection{
		//当服务被绑定的时候
		public void onServiceConnected(ComponentName name, IBinder service) {
			iservice=(IService) service;
		}

		public void onServiceDisconnected(ComponentName name) {
			
		}
	}
}
