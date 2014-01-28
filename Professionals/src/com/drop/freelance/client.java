package com.drop.freelance;



import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class client extends Activity{

	ProductAdapter adapter;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		Button button =(Button) findViewById(R.id.button3);
    	EditText edit2 = (EditText) findViewById(R.id.editText1);
		EditText edit = (EditText) findViewById(R.id.editText2);
		EditText edit3 = (EditText) findViewById(R.id.editText3);
		EditText edit4 = (EditText) findViewById(R.id.editText4);
		EditText edit5 = (EditText) findViewById(R.id.editText5);
	    

		edit.setText( SingelClient.getinstance().getmName());
		edit2.setText(SingelClient.getinstance().getmMahut());
		edit3.setText(SingelClient.getinstance().getmPhone());
		edit4.setText( SingelClient.getinstance().getmStreet());
		if (!SingelClient.getinstance().getmAccessories()[0].equals("no"))
		{edit5.setText(SingelClient.getinstance().getmAccessories()[0]);
		
		}
		else 
		{edit5.setText("No equipment needed");
		
		}
		if (!SingelClient.getinstance().getmAccessories()[1].equals("no"))button.setVisibility(View.VISIBLE);
		else button.setVisibility(View.GONE);
	//Toast.makeText(this,SingelClient.getinstance().getmCity(),Toast.LENGTH_LONG).show();
	}

	public void waze(View v) {
		
		try
		{
		   String url ="waze://?q= "+ SingelClient.getinstance().getmStreet();
		
		   Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
		   startActivity( intent );
		}
		catch ( ActivityNotFoundException ex  )
		{
		  Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
		  startActivity(intent);
		}
		finish();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
        

	}
        public void acc(View v) {
		
		
	
    	Intent myIntent= new Intent(this,acc.class);
	    startActivityForResult(myIntent, 1);
		finish();
	}
	
	public void back(View v) {
		
		Intent myIntent = new Intent();
		setResult(Activity.RESULT_OK, myIntent);
		//
		finish();
	}public void phone(View v) {
		
		String tel = "tel:" + SingelClient.getinstance().getmPhone() ;
		  Intent intent = null;
		  intent = new Intent(Intent.ACTION_DIAL,Uri.parse(tel));
		  startActivity(intent);
         
         
		finish();
	}
}

