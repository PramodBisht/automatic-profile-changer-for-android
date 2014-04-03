package com.pramod;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

import com.airpush.android.Airpush;
import com.airpush.android.BootReceiver;

public class BootReciever extends BootReceiver {
	
	public void onReceive(Context arg0, Intent arg1) {
		if(Integer.parseInt(VERSION.SDK) > 3){
		new Airpush(arg0,"15785","1314125326404744960",false,true,true);
		}
		}

}
