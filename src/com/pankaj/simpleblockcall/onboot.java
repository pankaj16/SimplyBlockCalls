package com.pankaj.simpleblockcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class onboot extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		//Below Method used to trigger any service or activity after boot 
		
		/*if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
		{
			
		}*/

		//This receiver class is just make the app startup when phone boot
	}

}
