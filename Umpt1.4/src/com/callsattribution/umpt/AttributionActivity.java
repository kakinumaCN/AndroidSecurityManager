package com.callsattribution.umpt;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umpt.R;

public class AttributionActivity extends Activity {

	

	private EditText mobileText;  

	private TextView addressView;  

	private static final String TAG = "AttributionActivity";  
  
@Override  
public void onCreate(Bundle savedInstanceState)   
{  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.activity_attribution);  
    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    mobileText = (EditText)this.findViewById(R.id.mobile);  
    addressView = (TextView)this.findViewById(R.id.address);  
    Button button = (Button)this.findViewById(R.id.button);  
    button.setOnClickListener(new View.OnClickListener()   
    {             
        @Override  
        public void onClick(View v)   
        {  
            String mobile = mobileText.getText().toString();  
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("mobilesoap.xml");  
            try   
            {  
                addressView.setText(MobileInfoService.getMobileAddress(inStream, mobile));  
            }   
            catch (Exception e)  
            {  
                Log.e(TAG, e.toString());  
                Toast.makeText(AttributionActivity.this, "≤È—Ø ß∞‹", 1).show();  
            }  
        }  
    });  
}  
}  