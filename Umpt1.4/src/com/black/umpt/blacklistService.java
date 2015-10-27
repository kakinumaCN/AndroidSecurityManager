package com.black.umpt;

import java.lang.reflect.Method;


import com.android.internal.telephony.ITelephony;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class blacklistService extends Service {
	MyDatabaseHelper dbHelper = new MyDatabaseHelper(this 
			, "mydatabase.db3" , null, 1);
	TelephonyManager tManager;
	CustomPhoneCallListener cpListener;
	public class CustomPhoneCallListener extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String incomingNumber)
		{
			switch (state)
			{
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				// 当电话呼入时
				case TelephonyManager.CALL_STATE_RINGING:
					// 如果该号码属于黑名单
					System.out.println("this is 1") ;
					if (isBlock(incomingNumber))
					{
						System.out.println("this is 2") ;
						try
						{
							Method method = Class.forName(
								"android.os.ServiceManager").getMethod(
								"getService", String.class);
							// 获取远程TELEPHONY_SERVICE的IBinder对象的代理
							IBinder binder = (IBinder) method.invoke(null,
								new Object[] { TELEPHONY_SERVICE });
							// 将IBinder对象的代理转换为ITelephony对象
							ITelephony telephony = ITelephony.Stub
								.asInterface(binder);
							// 挂断电话
							telephony.endCall();
						}
						catch (Exception e)
						{
							
							e.printStackTrace();
						}
					}
					break;
					
			}
			super.onCallStateChanged(state, incomingNumber);
		}
		public boolean isBlock(String phone)
		{
			Cursor cursor =dbHelper.getReadableDatabase().rawQuery(
					"select * from List",null);
			while(cursor.moveToNext()){
				if (cursor.getString(1).equals(phone)){
					return true;
				}
			}
			return false;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		tManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		cpListener = new CustomPhoneCallListener();
		// 通过TelephonyManager监听通话状态的改变
		tManager.listen(cpListener, PhoneStateListener.LISTEN_CALL_STATE);
		super.onStart(intent, startId);
	}

}
