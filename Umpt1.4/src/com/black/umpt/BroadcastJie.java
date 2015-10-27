package com.black.umpt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastJie extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// TODO Auto-generated method stub
		//判断电话状态，如果是拨打电话，什么都不做，否则执行else,启动其监听的Service
		if (intent.getAction().equals(
				Intent.ACTION_NEW_OUTGOING_CALL)){
		}
		else{
			Intent Sbintent = new Intent(context,blacklistService.class);
			context.startService(Sbintent);
		}
	}

}
