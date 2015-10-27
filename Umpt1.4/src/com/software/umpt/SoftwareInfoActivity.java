package com.software.umpt;


import java.util.List;

import com.example.umpt.R;
import com.example.umpt.R.id;
import com.example.umpt.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;

public class SoftwareInfoActivity extends Activity {

	private static final int GET_ALL_APP_FINISH = 1;  
    
    private ListView lv_app_manager;//应用信息列表  
    private LinearLayout ll_app_manager_progress; //进度条 
    private AppInfoProvider provider;  
    private AppManagerAdapter adapter;  
    private List<AppInfo> list;  
      
    @SuppressLint("HandlerLeak")  
    private Handler handler = new Handler()  
    {  
        public void handleMessage(Message msg)   
        {  
            switch(msg.what)  
            {  
                case GET_ALL_APP_FINISH :   
                    //进度条设置为不可见  
                    ll_app_manager_progress.setVisibility(View.GONE);  
                    adapter = new AppManagerAdapter();  
                    lv_app_manager.setAdapter(adapter);  
                    break;  
                      
                default :   
                    break;  
            }  
        };  
    };  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_softwareinfo);  
          
        lv_app_manager = (ListView) findViewById(R.id.lv_app_manager);  
        ll_app_manager_progress = (LinearLayout) findViewById(R.id.ll_app_manager_progress);  
        ll_app_manager_progress.setVisibility(View.VISIBLE);  
          
        /**
         * //开一个线程用于完成对所有应用程序信息的搜索  
         * 当搜索完成之后，就把一个成功的消息发送给Handler，
         * 然后handler把搜索到的数据设置进入listview里面  .
         * */
        new Thread()  
        {  
            public void run()   
            {  
                provider = new AppInfoProvider(SoftwareInfoActivity.this);  
                list = provider.getAllApps();  
                Message msg = new Message();  
                msg.what = GET_ALL_APP_FINISH;  
                handler.sendMessage(msg);  
            };  
        }.start();  
    }  
      
    //======================================================================  
      
    private class AppManagerAdapter extends BaseAdapter  
    {  
  
        @Override  
        public int getCount()  
        {  
            return list.size();  
        }  
  
        @Override  
        public Object getItem(int position)  
        {  
            return list.get(position);  
        }  
  
        @Override  
        public long getItemId(int position)  
        {  
            return position;  
        }  
        @Override  
        public View getView(int position, View convertView, ViewGroup parent)  
        {  
            AppInfo info = list.get(position);  
            if(convertView == null)  
            {  
                View view = View.inflate(SoftwareInfoActivity.this, R.layout.app_manager_item, null);  
                AppManagerViews views = new AppManagerViews();  
                views.iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_manager_icon);  
                views.tv_app_name = (TextView) view.findViewById(R.id.tv_app_manager_name);  
                views.iv_app_icon.setImageDrawable(info.getIcon());  
                views.tv_app_name.setText(info.getAppName());  
                view.setTag(views);  
                return view;  
            }  
            else  
            {  
                AppManagerViews views = (AppManagerViews) convertView.getTag();  
                views.iv_app_icon.setImageDrawable(info.getIcon());  
                views.tv_app_name.setText(info.getAppName());  
                return convertView;  
            }  
        }  
          
    }  
    private class AppManagerViews  
    {  
        ImageView iv_app_icon;  
        TextView tv_app_name;  
    }  
}
