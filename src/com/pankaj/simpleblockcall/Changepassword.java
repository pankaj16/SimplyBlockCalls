package com.pankaj.simpleblockcall;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.pankaj.simplyblockcalls.R;

public class Changepassword extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);
	}

	public void btnChange_Click(View view)
	{
		String strNewPass="";
		String strRepeat="";
		boolean boolFlag=true;
		
		EditText edtOldPass=(EditText)findViewById(R.id.edtCurrentPass);
		EditText edtNewPass=(EditText)findViewById(R.id.edtNewPass);
		EditText edtRepeat=(EditText)findViewById(R.id.edtRepeat);
		
		strNewPass=edtNewPass.getText().toString();
		strRepeat=edtRepeat.getText().toString();
		
		if(strNewPass.equalsIgnoreCase(""))
		{
			Toast.makeText(this, "New Password Can't be Empty!!!", Toast.LENGTH_LONG).show();
			boolFlag=false;
		}
		else
		{
		  if(strNewPass.equals(strRepeat))
		  {
			if(Check_OldPass(edtOldPass.getText().toString()))
			{
				SharedPreferences sprefPin = getSharedPreferences("PS_Storage", MODE_PRIVATE);
				SharedPreferences.Editor edit = sprefPin.edit();
			    edit.putString("PS",strNewPass);
			    edit.commit();
			    Toast.makeText(this, "New Password Saved!!!", Toast.LENGTH_LONG).show();
			    boolFlag=false;
			}
			else
			{
				Toast.makeText(this, "Current Password is Wrong!!!", Toast.LENGTH_LONG).show();
				boolFlag=false;
			}
		  }		
	    }
		
		if(boolFlag==true)
			Toast.makeText(this, "New and Repeat Password are not same!!!", Toast.LENGTH_LONG).show();
	}
	
	public boolean Check_OldPass(String strOldPass)
	{
		String strTmp;
		SharedPreferences sprefPin = getSharedPreferences("PS_Storage", MODE_PRIVATE);
		strTmp=sprefPin.getString("PS", "777777");
		if(strTmp.equals(strOldPass))
			return true;
		else
			return false;
	}
	
	public void btnCancel_Click(View view)
	{
		Intent intent=new Intent(this,com.pankaj.simpleblockcall.Blockcall.class);
	    startActivity(intent);
	    finish();
	}
}


