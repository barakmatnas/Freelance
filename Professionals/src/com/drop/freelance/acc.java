package com.drop.freelance;

import com.drop.cloud.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class acc extends Activity{

	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acc);
		
    	EditText edit = (EditText) findViewById(R.id.e1);
		EditText edit1 = (EditText) findViewById(R.id.e2);
		EditText edit2 = (EditText) findViewById(R.id.e3);
		EditText edit3 = (EditText) findViewById(R.id.e4);
		EditText edit4 = (EditText) findViewById(R.id.e5);
		EditText edit5 = (EditText) findViewById(R.id.e6);
		EditText edit6 = (EditText) findViewById(R.id.e7);
		
		edit1.setTextColor(Color.RED);
		
		if (!SingelClient.getinstance().getmAccessories()[0].equals("no"))
		{edit.setText(SingelClient.getinstance().getmAccessories()[0]);}
		if (!SingelClient.getinstance().getmAccessories()[1].equals("no"))
		{edit2.setText(SingelClient.getinstance().getmAccessories()[1]);}
	   if  (!SingelClient.getinstance().getmAccessories()[2].equals("no"))
	   {edit3.setText(SingelClient.getinstance().getmAccessories()[2]);}		
		   if (!SingelClient.getinstance().getmAccessories()[3].equals("no"))
		   {edit4.setText(SingelClient.getinstance().getmAccessories()[3]);}
			   if (!SingelClient.getinstance().getmAccessories()[4].equals("no"))
			   {edit5.setText(SingelClient.getinstance().getmAccessories()[4]);}
				   if (!SingelClient.getinstance().getmAccessories()[5].equals("no"))
				   {edit6.setText(SingelClient.getinstance().getmAccessories()[5]);}
				   else edit6.setVisibility(View.GONE); 
				  
	
	//Toast.makeText(this,SingelClient.getinstance().getmCity(),Toast.LENGTH_LONG).show();
	}

	
       
	
	public void bback(View v) {
		
		Intent myIntent = new Intent();
		setResult(Activity.RESULT_OK, myIntent);
		//
		finish();
	}
}


