package com.pankaj.simpleblockcall;




import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.pankaj.simplyblockcalls.R;


public class Blockcall extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		try
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blockcall);
		
		/*boolean boolOutGoing=false;
		boolCheck=CheckBox_Checking();*/
		
		CheckBox cbCheckBlcok=(CheckBox)findViewById(R.id.cbBlockFlag);
		CheckBox cbIncomgflag=(CheckBox)findViewById(R.id.cbIncomingFlag);
		//0 for Outgoing check
		if(OutandInCall_Checking(0))
		  cbCheckBlcok.setChecked(true);
		else
		  cbCheckBlcok.setChecked(false);		
		
		//1 for Incoming check
		if(OutandInCall_Checking(1))
			cbIncomgflag.setChecked(true);
		else
			cbIncomgflag.setChecked(false);
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private boolean OutandInCall_Checking(int iCheck) {		
		
		if(iCheck==0)
			{
				SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);		
				return sprefPin.getBoolean("Block", false); //Default value false for Unchecked
			}
			else
			{
				SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);		
				return sprefPin.getBoolean("Incoming", false); //Default value false for Unchecked
			}		
	}
	


	public void btnSave_Click(View view)
	{
		boolean boolOutBlock=true,boolInBlock=true;
		int iRowCount=0;
		CheckBox cbBlockCalls=(CheckBox)findViewById(R.id.cbBlockFlag);
		CheckBox cbIncomgflag=(CheckBox)findViewById(R.id.cbIncomingFlag);
			
		try
		{
		 if(cbBlockCalls.isChecked())
		 {
		  SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);
		  SharedPreferences.Editor edit = sprefPin.edit();
	      edit.putBoolean("Block",true);
	      edit.commit();
	      boolOutBlock=true;
	      //Toast.makeText(this, "OutGoing Calls are BLOCKED now!!!", Toast.LENGTH_LONG).show();			      
		 }
		 else
		 {
		  SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);
		  SharedPreferences.Editor edit = sprefPin.edit();
		  edit.putBoolean("Block", false);
		  edit.commit();
		  boolOutBlock=false;
		  //Toast.makeText(this, "You can make Calls now!!!", Toast.LENGTH_LONG).show();		
		 }
		 
		 if(cbIncomgflag.isChecked())
		 {
		  SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);
		  SharedPreferences.Editor edit = sprefPin.edit();
	      edit.putBoolean("Incoming",true);
	      edit.commit();
	      boolInBlock=true;
	      //Toast.makeText(this, "Incoming Calls are BLOCKED now!!!", Toast.LENGTH_LONG).show();			      
		 }
		 else
		 {
		  SharedPreferences sprefPin = getSharedPreferences("PS_Checked", MODE_PRIVATE);
		  SharedPreferences.Editor edit = sprefPin.edit();
		  edit.putBoolean("Incoming", false);
		  edit.commit();
		  boolInBlock=false;
		  //Toast.makeText(this, "You can make Calls now!!!", Toast.LENGTH_LONG).show();		
		 }
		 
		 if(boolOutBlock==true && boolInBlock==true)
			 Toast.makeText(this, "Both InComing and OutGoing is Blocked now!!!", Toast.LENGTH_LONG).show();
		 else if(boolOutBlock==false && boolInBlock==false)
			 Toast.makeText(this, "Both InComing and OutGoing is Activated now!!!", Toast.LENGTH_LONG).show();
		 else if(boolOutBlock==true && boolInBlock==false)
			 Toast.makeText(this, "Only OutGoing is Blocked and InComing is Active!!!", Toast.LENGTH_LONG).show();
		 else if(boolOutBlock==false && boolInBlock==true)
			 Toast.makeText(this, "Only InComing is Blocked and OutGoing is Active!!!", Toast.LENGTH_LONG).show();
		 
		}
		catch(ClassCastException ex)
		{
			ex.printStackTrace();
			//Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void btnChangePass_Click(View view)
	{
		Intent intent=new Intent(this,com.pankaj.simpleblockcall.Changepassword.class);
		startActivity(intent);
		finish();
	}
	
	public void onBackPressed()
	{
		System.exit(0);
	}
}
