package com.drop.freelance;

import com.drop.cloud.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Lane extends Activity implements OnItemSelectedListener{ 
	String name ;
	String name2;
	TextView text;
	TextView text2;
	private String array_spinner[];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lane);
		name="";
		name2="";
		String[] result= SingelClient.getinstance().getMlane();
		int i = result.length+1;
		array_spinner=new String[i];

		text = (TextView) findViewById(R.id.textView2);
		text2 = (TextView) findViewById(R.id.textView3);
		array_spinner[0]="Choose schedule";
		for (int j=1;j<i;j++) {
			array_spinner[j]=result[j-1];
		}
		Spinner s2 = (Spinner) findViewById(R.id.Spinner02);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array_spinner);
		s2.setAdapter(adapter2);

		s2.setOnItemSelectedListener(this);

	}


	public void cancel(View v) {
		Intent myIntent = new Intent();
		setResult(Activity.RESULT_CANCELED, myIntent);
		finish();
	}
	public void back(View v) {


		if (name.contentEquals("")&name2.contentEquals("")) Toast.makeText(Lane.this,"enter lane please",Toast.LENGTH_LONG).show();
		else{

			Intent myIntent = new Intent();
			myIntent.putExtra("name2",name);
			myIntent.putExtra("name",name2);

			setResult(Activity.RESULT_OK,myIntent);
			finish();
		}	



	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		if (arg2!=0){
			if (name.equals("")){
				name=array_spinner[arg2];
				text.setText(name);

			}
			else if (name2.equals("")){
				name2=array_spinner[arg2];
				text2.setText(name2);
			}
			else Toast.makeText(Lane.this,"ניתן לטעון שני מסלולים בלבד",Toast.LENGTH_LONG).show();



		}}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}	



}
