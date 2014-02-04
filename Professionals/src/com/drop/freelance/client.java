package com.drop.freelance;



import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class client extends Activity{

	ProductAdapter adapter;
	private String[] accessories;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		//Button button =(Button) findViewById(R.id.button4);
		TextView eAdress = (TextView) findViewById(R.id.editText1);
		TextView eName = (TextView) findViewById(R.id.editText2);
		TextView edit3 = (TextView) findViewById(R.id.editText3);
		TextView edit4 = (TextView) findViewById(R.id.editText4);
		TextView edit5 = (TextView) findViewById(R.id.editText5);
		TextView edit6 = (TextView) findViewById(R.id.editText6);
		edit4.setMovementMethod(LinkMovementMethod.getInstance());
		edit5.setMovementMethod(LinkMovementMethod.getInstance());
		edit3.setMovementMethod(LinkMovementMethod.getInstance());
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
        
	
	public void back(View v) {
		
		Intent myIntent = new Intent();
		setResult(Activity.RESULT_OK, myIntent);
		//
		finish();
	}
	
public void esend(View v) {
	if (!accessories[0].equals("no")){
	Intent i = new Intent(Intent.ACTION_SEND);
	i.setType("message/rfc822");
	i.putExtra(Intent.EXTRA_EMAIL  , new String[]{accessories[0]});
	i.putExtra(Intent.EXTRA_SUBJECT, "הפנייה מאפליקציית פרילנסר");
	i.putExtra(Intent.EXTRA_TEXT   , "");
	try {
	    startActivity(Intent.createChooser(i, "Send mail..."));
	} catch (android.content.ActivityNotFoundException ex) {
	    Toast.makeText(client.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	}}
	}
	
public void elink(View v) {
	
	if (!accessories[2].equals("no")){
	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(accessories[2]));
	try {
		startActivity(browserIntent);
	} catch (android.content.ActivityNotFoundException ex) {
	    Toast.makeText(client.this, "מצטער ארעה תקלה", Toast.LENGTH_SHORT).show();
	}
	
	}
}


	public void phone(View v) {
		if (!SingelClient.getinstance().getmPhone().equals("no")){
		String tel = "tel:" + SingelClient.getinstance().getmPhone() ;
		  Intent intent = null;
		  intent = new Intent(Intent.ACTION_DIAL,Uri.parse(tel));
		  startActivity(intent);
         
         
		finish();
	}}
}

