package com.pankaj.simpleblockcall;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.Toast;

public class MyPhoneReciever extends BroadcastReceiver {

	public static String phoneNumber=null;
	public static String OUTGOING_CALL_ACTION = "android.intent.action.NEW_OUTGOING_CALL";
	public static String INTENT_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";	
		
    @Override
    public void onReceive(Context context, Intent intent) {    	   	
    	boolean boolResult=false;
    	
    	/*if(ActiveBlockOutGoingCalls(context))
    	{
    		if (intent.getAction().equals(MyPhoneReciever.OUTGOING_CALL_ACTION))
   	        {   	       
    			setResultData(null);
   	         }  
   	     }	
	        
    	try
    	{
    	phoneNumber = intent.getExtras().getString(MyPhoneReciever.INTENT_PHONE_NUMBER);
        	boolResult=Check_Pin(phoneNumber,context);
                     
             if (boolResult) 
             { 
            	 
                 intent.setClassName("com.pankaj", "com.pankaj.Blockcall");
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
                 setResultData(null); 
                 context.startActivity(intent);             
             }
    	 }
        catch(Exception ex)
        	{
        		//Toast.makeText(context, ex.toString() + "Receive", Toast.LENGTH_LONG).show();
        		ex.printStackTrace();
        	}
    	     return;     
             
    	}*/
   try
    {
    	if (intent.getAction().equals(MyPhoneReciever.OUTGOING_CALL_ACTION))
    	{
    	    if(ActiveBlockOutGoingCalls(context))
    	     {
    		  setResultData(null); 
    	     }
    	   
    	    	phoneNumber = intent.getExtras().getString(MyPhoneReciever.INTENT_PHONE_NUMBER);
    	    	boolResult=Check_Pin(phoneNumber,context);
                      
    	    	if (boolResult) 
    	    	{ 
             	 
                  intent.setClassName("com.pankaj", "com.pankaj.Blockcall");
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
                  setResultData(null); 
                  context.startActivity(intent);             
    	    	}
              
    	 }
       else
    	 {
    	   if (intent.getAction().equals("android.intent.action.PHONE_STATE")) 
           { 
               String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
    	   if(ActiveBlockIncomingCalls(context) && state.equals(TelephonyManager.EXTRA_STATE_RINGING))
  	     	{
    		   //setResultData(null);
    		   killCall(context);
  	     	}
    	  }    	   
    	 
        }
    	
    }
   catch(Exception ex)
   {
	   Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show();
   }
	  /* if (intent.getAction().equals("android.intent.action.PHONE_STATE"))
	    {

		   String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
           
		  
           if (state.equals(TelephonyManager.EXTRA_STATE_RINGING))
 		      {
        	   setResultData(null);
        	   Toast.makeText(context, "Result", Toast.LENGTH_LONG).show();
 		      }
	    }*/
     
    }
    
    public boolean killCall(Context context) {
        try {
           // Get the boring old TelephonyManager
           TelephonyManager telephonyManager =
              (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
     
           // Get the getITelephony() method
           Class classTelephony = Class.forName(telephonyManager.getClass().getName());
           Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");
     
           // Ignore that the method is supposed to be private
           methodGetITelephony.setAccessible(true);
     
           // Invoke getITelephony() to get the ITelephony interface
           Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);
     
           // Get the endCall method from ITelephony
           Class telephonyInterfaceClass =  
               Class.forName(telephonyInterface.getClass().getName());
           Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");
     
           // Invoke endCall()
           methodEndCall.invoke(telephonyInterface);
     
       } catch (Exception ex) { // Many things can go wrong with reflection calls
          //Logger.e("PhoneStateReceiver **" + ex.toString());
          return false;
       }
       return true;
    }
    
    private boolean ActiveBlockOutGoingCalls(Context context)
    {
    	try
    	{
    	SharedPreferences sprefPin = context.getSharedPreferences("PS_Checked", Context.MODE_PRIVATE);		
		return sprefPin.getBoolean("Block", false);  // Default false for not blocking the calls
    	}
    	catch(Exception ex)
    	{
    		//Toast.makeText(context, ex.toString() + "Receive", Toast.LENGTH_LONG).show();
    		ex.printStackTrace();
    	}
    	return false;
    }
    
    private boolean ActiveBlockIncomingCalls(Context context)
    {
    	try
    	{
    	SharedPreferences sprefPin = context.getSharedPreferences("PS_Checked", Context.MODE_PRIVATE);		
		return sprefPin.getBoolean("Incoming", false);  // Default false for not blocking the calls
    	}
    	catch(Exception ex)
    	{
    		//Toast.makeText(context, ex.toString() + "Receive", Toast.LENGTH_LONG).show();
    		ex.printStackTrace();
    	}
    	return false;
    }
    
    private boolean Check_Pin(String strPin,Context context) {
    	SharedPreferences sprefPin;
    	String strTmp=null;
    	boolean boolResult=false;
    	try
    	{
    	 sprefPin = context.getSharedPreferences("PS_Storage", 0);    	
		 strTmp=sprefPin.getString("PS","777777");	
		
				
		  if(strPin.equals(strTmp))
			boolResult=true;		
		  else
		   boolResult=false;    	
    	}
    	 catch(Exception ex)
     	{
     		//Toast.makeText(context, ex.toString() + "Pin", Toast.LENGTH_LONG).show();
    		 ex.printStackTrace();
     	}
    	return boolResult;
    }
}