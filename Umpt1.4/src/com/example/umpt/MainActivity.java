package com.example.umpt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.applock.umpt.AppLockActivity;
import com.cleanmaster.umpt.CleanActivity;
import com.security.umpt.LostProtectedActivity;
import com.software.umpt.SoftwareInfoActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	private Button btnCommunication;
	private Button btnSystem;
	private Button btnInternet;
	private Button btnSoftware;
	private Button btntest;
	
	
	
	private Button btnchangecolor;
	private ImageView maincolor1;
	private ImageView maincolor2;
	private ImageView maincolor3;

	
	 private Button openButton;
	 private Button closeButton;
	 private SlidingMenu mSlidingMenu;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
        
        
        mSlidingMenu = new SlidingMenu(this, LayoutInflater.from(this).inflate(R.layout.activity_main, null), LayoutInflater.from(this).inflate(R.layout.activity_fragment, null));
        
        
        
        
        setContentView(mSlidingMenu);
        //setContentView(R.layout.activity_main);
        
        
        
        
        openButton = (Button) findViewById(R.id.fragment_open);
        closeButton = (Button) findViewById(R.id.fragment_back);
        
        
        openButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mSlidingMenu.open();
            }
        });
        
        closeButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mSlidingMenu.close();
            }
        });
        
        

        maincolor1=(ImageView)findViewById(R.id.maincolor1);
        maincolor2=(ImageView)findViewById(R.id.maincolor2);
        maincolor3=(ImageView)findViewById(R.id.maincolor3);
        btnchangecolor=(Button)findViewById(R.id.button5);
        
        maincolor1.setVisibility(View.GONE);
		maincolor2.setVisibility(View.VISIBLE);
		maincolor3.setVisibility(View.GONE);
        btnchangecolor.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(maincolor2.getVisibility()==View.GONE&&maincolor3.getVisibility()==View.GONE)
				{
					maincolor1.setVisibility(View.GONE);
					maincolor2.setVisibility(View.VISIBLE);
				}
				else if(maincolor1.getVisibility()==View.GONE&&maincolor3.getVisibility()==View.GONE)
				{
					maincolor2.setVisibility(View.GONE);
					maincolor3.setVisibility(View.VISIBLE);
				}
				else if(maincolor1.getVisibility()==View.GONE&&maincolor2.getVisibility()==View.GONE)
				{
					maincolor3.setVisibility(View.GONE);
					maincolor1.setVisibility(View.VISIBLE);
				}
				else
				{
				
				}

			}});
        
        btntest=(Button)findViewById(R.id.maintest);
        btntest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(MainActivity.this,AppLockActivity.class);
				startActivity(intent);
			}
		});
        
        
        btnCommunication=(Button)findViewById(R.id.button3);
        btnSystem=(Button)findViewById(R.id.button4);
        btnInternet=(Button)findViewById(R.id.button1);
        btnSoftware=(Button)findViewById(R.id.button2);
        
        
        
        btnCommunication.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,CommunicationActivity.class);
				startActivity(intent);
				
			}});
        
        
      
        btnSoftware.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,SoftwareActivity.class);
				startActivity(intent);
				
			}});
        
       
        btnSystem.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,SystemActivity.class);
				startActivity(intent);
				
				
				
		
			}});
        
        btnInternet.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(MainActivity.this,InternetActivity.class);
				startActivity(intent);
				
				
				
		
			}});
        

        
        TextView cupinfo;
        cupinfo = (TextView)findViewById(R.id.cupinfo);
        cupinfo.setText(getCpuInfo());
        
        TextView display;
        display = (TextView)findViewById(R.id.display);
        display.setText(getWeithAndHeight());
        
        TextView siminfo;
        siminfo = (TextView)findViewById(R.id.siminfo);
        siminfo.setText(getSimCardInfo());
        
        TextView memoryinfo;
        memoryinfo = (TextView)findViewById(R.id.memoryinfo);
        memoryinfo.setText(getSystemMemory());
    }
    
 // 获取手机cpu型号
    private String getCpuInfo() {  
        String str1 = "/proc/cpuinfo";  
        String str2 = "";  
        String[] cpuInfo = { "", "" }; // 1-cpu型号 //2-cpu频率  
        String[] arrayOfString;  
        try {  
            FileReader fr = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            for (int i = 2; i < arrayOfString.length; i++) {  
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";  
            }  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            cpuInfo[1] += arrayOfString[2];  
            localBufferedReader.close();  
        } catch (IOException e) {  
        }  
        // Log.i(TAG, "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);  
        return "1-cpu型号:" + cpuInfo[0] + "\n2-cpu频率:" + cpuInfo[1];  
    } 
    
    
    
    
    // 获取手机屏幕高度  
    private String getWeithAndHeight() {  
        // 这种方式在service中无法使用，  
        DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        int width = dm.widthPixels; // 宽  
        int height = dm.heightPixels; // 高  
        float density = dm.density; // 屏幕密度（0.75 / 1.0 / 1.5）  
        int densityDpi = dm.densityDpi; // 屏幕密度DPI（120 / 160 / 240）  
        // 在service中也能得到高和宽  
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
        width = mWindowManager.getDefaultDisplay().getWidth();  
        height = mWindowManager.getDefaultDisplay().getHeight();  
     
        // 居中显示Toast  
        /*Toast msg = Toast.makeText(this, "宽=" + width + "   高=" + height,  Toast.LENGTH_LONG);  
        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,  
                msg.getYOffset() / 2);  
        msg.show();  
        */
        return "屏幕：" + width + "x" + height  + "\n屏幕密度DPI:" + densityDpi + " ";  
    }  
    
    // 获取手机sim卡信息
    public String getSimCardInfo() {        
        TelephonyManager tm = (TelephonyManager) this .getSystemService(TELEPHONY_SERVICE);  
        /*String deviceied = tm.getDeviceId();
        String tel = tm.getLine1Number();
        String imei = tm.getSimSerialNumber();
        String imsi = tm.getSubscriberId();*/ 
        tm.getCallState();// int  
        CellLocation location = tm.getCellLocation();          
        location.requestLocationUpdate();               
        tm.getDataActivity();            
        tm.getDataState();  
        String Imei = tm.getDeviceId();// String            
        tm.getDeviceSoftwareVersion();// String        
        String phoneNum = tm.getLine1Number();// String    
        tm.getNetworkCountryIso();// String      
        tm.getNetworkOperator();// String     
        tm.getNetworkOperatorName();// String  
        tm.getNetworkType();// int       
        tm.getPhoneType();// int  
        tm.getSimCountryIso();// String  
        tm.getSimOperator();// String  
        tm.getSimOperatorName();// String  
        tm.getSimSerialNumber();// String  
        tm.getSimState();// int     
        tm.getSubscriberId();// String 
        tm.getVoiceMailAlphaTag();// String  
        tm.getVoiceMailNumber();// String   
        tm.hasIccCard();// boolean   
        tm.isNetworkRoaming();//   
        String ProvidersName = null;  // 返回唯一的用户ID;
        
        String IMSI = tm.getSubscriberId(); // 国际移动用户识别码  
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。  
        System.out.println(IMSI);  
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {  
            ProvidersName = "中国移动";  
        } else if (IMSI.startsWith("46001")) {  
     
            ProvidersName = "中国联通";  
     
        } else if (IMSI.startsWith("46003")) {  
     
            ProvidersName = "中国电信";
        }  
        List<NeighboringCellInfo> infos = tm.getNeighboringCellInfo();  
        for (NeighboringCellInfo info : infos) {  
            // 获取邻居小区号  
            int cid = info.getCid();  
            // 获取邻居小区LAC，LAC:  
            // 位置区域码。为了确定移动台的位置，每个GSM/PLMN的覆盖区都被划分成许多位置区，LAC则用于标识不同的位置区。  
            info.getLac();  
            info.getNetworkType();  
            info.getPsc();  
            // 获取邻居小区信号强度  
            info.getRssi();  
        }  
        return "手机号码:" + phoneNum + " " + "\n服务商：" + ProvidersName+" " + "\nIMEI：" + Imei;  
    }  
    
    
    // 获取手机可用内存和总内存  
    private String getSystemMemory() {        
        String availMemory = getAvailMemory();  
        String totalMemory = getTotalMemory();  
        return "可用内存=" + availMemory + "\n" + "总内存=" + totalMemory;     
    }
    
    // 获取android当前可用内存大小
    private String getAvailMemory() {  
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);  
        MemoryInfo mi = new MemoryInfo();  
        am.getMemoryInfo(mi);  
        // mi.availMem; 当前系统的可用内存  
        return Formatter.formatFileSize(getBaseContext(), mi.availMem);// 将获取的内存大小规格化  
    }  
     
    private String getTotalMemory() {  
        String str1 = "/proc/meminfo";// 系统内存信息文件  
        String str2;  
        String[] arrayOfString;  
        long initial_memory = 0;  
        try {  
            FileReader localFileReader = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(  
                    localFileReader, 8192);  
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小  
     
            arrayOfString = str2.split("\\s+");  
            for (String num : arrayOfString) {  
                Log.i(str2, num + "\t");  
            }  
     
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte  
            localBufferedReader.close();  
     
        } catch (IOException e) {  
        }  
        return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byte转换为KB或者MB，内存大小规格化  
    }  
    
     private long getAvailMemory(Context context) {
        
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi); 
        return mi.availMem;
    }
     private String formatFileSize(long number){
         return Formatter.formatFileSize(MainActivity.this, number);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
