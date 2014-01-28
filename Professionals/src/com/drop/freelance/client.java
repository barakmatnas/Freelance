package com.drop.freelance;



import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class client extends Activity{

	ProductAdapter adapter;
	private String[] accessories;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		//Button button =(Button) findViewById(R.id.button4);
    	EditText eAdress = (EditText) findViewById(R.id.editText1);
		EditText eName = (EditText) findViewById(R.id.editText2);
		EditText edit3 = (EditText) findViewById(R.id.editText3);
		EditText edit4 = (EditText) findViewById(R.id.editText4);
		EditText edit5 = (EditText) findViewById(R.id.editText5);
		EditText edit6 = (EditText) findViewById(R.id.editText6);

		eName.setText( SingelClient.getinstance().getmName());
		eAdress.setText(SingelClient.getinstance().getmStreet());
		edit3.setText(SingelClient.getinstance().getmPhone());
		accessories=SingelClient.getinstance().getmAccessories();
		edit4.setText(accessories[0]);
		edit5.setText(accessories[2]);
		edit6.setText( SingelClient.getinstance().getmMahut());
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
	}
	
public void esend(View v) {
		
	Intent i = new Intent(Intent.ACTION_SEND);
	i.setType("message/rfc822");
	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{accessories[1]});
	i.putExtra(Intent.EXTRA_SUBJECT, "הפנייה מאפליקציית פרילנסר");
	i.putExtra(Intent.EXTRA_TEXT   , "");
	try {
	    startActivity(Intent.createChooser(i, "Send mail..."));
	} catch (android.content.ActivityNotFoundException ex) {
	    Toast.makeText(client.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	}
	}
	
	
	public void phone(View v) {
		
		String tel = "tel:" + SingelClient.getinstance().getmPhone() ;
		  Intent intent = null;
		  intent = new Intent(Intent.ACTION_DIAL,Uri.parse(tel));
		  startActivity(intent);
         
         
		finish();
	}
}

