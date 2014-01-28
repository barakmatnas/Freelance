package com.drop.freelance;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

public class Lane extends Activity { 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lane);
		

	}


	public void cancel(View v) {
		Intent myIntent = new Intent();
		setResult(Activity.RESULT_CANCELED, myIntent);
		finish();
	}
	public void send(View v) {


		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
		i.putExtra(Intent.EXTRA_TEXT   , "body of email");
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(Lane.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
		
			//setResult(Activity.RESULT_OK,myIntent);
			//finish();
		}	



	

	


}
