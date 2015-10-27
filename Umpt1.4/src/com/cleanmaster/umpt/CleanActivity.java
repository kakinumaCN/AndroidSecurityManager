

package com.cleanmaster.umpt;




import android.app.Activity;

import android.os.Bundle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.example.umpt.R;
//import com.nd.padrom.cleanmaster.CleanActivity;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;


public class CleanActivity extends Activity {
    private static final String TAG = "MyLog";
    private ImageView rotateImage;
    private Animation animation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_clean);       
       
        rotateImage=(ImageView)findViewById(R.id.clean_imageView_rotate);        
        animation=AnimationUtils.loadAnimation(CleanActivity.this, R.anim.clean_anim);             
        animation.setAnimationListener(new AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                killAll(getApplicationContext());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
                
            }   
        });    
        rotateImage.setAnimation(animation);            
    }

  
    public void killAll(Context context){

        
        ActivityManager activityManager = (ActivityManager)
                getSystemService(Context.ACTIVITY_SERVICE);
        
        List<RunningAppProcessInfo> appProcessInfos = activityManager
                .getRunningAppProcesses();
        int count=0;
        String nameList="";
        long beforeMem = getAvailMemory(CleanActivity.this);
        Log.i(TAG, "清理前可用内存为 : " + beforeMem);
        
        for (RunningAppProcessInfo appProcessInfo:appProcessInfos) {
            nameList="";          
            if( appProcessInfo.processName.contains("com.android.system")
                    ||appProcessInfo.pid==android.os.Process.myPid())
                continue;
            String[] pkNameList=appProcessInfo.pkgList;
            for(int i=0;i<pkNameList.length;i++){
                String pkName=pkNameList[i];
                activityManager.killBackgroundProcesses(pkName);
                count++;
                nameList+="  "+pkName;               
            } 
            Log.i(TAG, nameList+"---------------------");
        }              
        
        long afterMem = getAvailMemory(CleanActivity.this);
        Toast.makeText(CleanActivity.this, "杀死 " + (count+1) + " 个进程, 释放"
                + formatFileSize(afterMem - beforeMem) + "内存", Toast.LENGTH_LONG).show();
        Log.i(TAG, "清理后可用内存为 : " + afterMem);
        Log.i(TAG, "清理进程数量为 : " + count+1);
        
    }

    
  
    private long getAvailMemory(Context context) {
        
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo mi = new MemoryInfo();
        am.getMemoryInfo(mi); 
        return mi.availMem;
    }
    
    
    private String formatFileSize(long number){
        return Formatter.formatFileSize(CleanActivity.this, number);
    }
   
}