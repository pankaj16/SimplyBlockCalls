package com.pankaj.simpleblockcall;

import java.io.InputStream;
import java.net.URL;



import android.os.Bundle;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.pankaj.simplyblockcalls.R;

public class MainActivity extends Activity 
{	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
	}
	
	public void btnEnter_Click(View view)
	{	
		try
		{
		boolean boolResult=false;
		EditText edtPass=(EditText)findViewById(R.id.edtPass); 
		String strPass;
		
		strPass=edtPass.getText().toString();
		
		//Method Call
		boolResult=Check_Pass(strPass);
		
	      if(boolResult==true)	
	      {
		      Intent intent=new Intent(this.getApplicationContext(),com.pankaj.simpleblockcall.Blockcall.class);
		      startActivity(intent);
		      finish();
		  }
		  else
		   	Toast.makeText(this, "Wrong Pass!!!", Toast.LENGTH_LONG).show();
		}
		catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
		 
	}//end of Method
				
	public boolean Check_Pass(String strPass)
	{		
		String strTmp="";
		
		SharedPreferences sprefPin = getSharedPreferences("PS_Storage", MODE_PRIVATE);
		strTmp=sprefPin.getString("PS", "777777");
		
		if(strTmp.equals(strPass))
			return true;
		else
			return false;
	}
}
		
