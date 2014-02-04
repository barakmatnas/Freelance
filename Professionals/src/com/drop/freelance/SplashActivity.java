/**
 * first activity that runs advertising picture 
 * after one second will call main menu activity
 *
 *
 *
 */
package com.drop.freelance;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


public class SplashActivity extends Activity {

	// Splash screen timer
	
	private CheckBox cb1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		cb1 = (CheckBox) findViewById(R.id.checkBox1);
	
	
	
	}
public void ok(View v) {
	if (cb1.isChecked()) {
		Intent myIntent= new Intent(SplashActivity.this,MainActivity.class);
		startActivity(myIntent);
		finish();
	}
	else{
		Toast.makeText(getApplicationContext(), "יש לאשר שקראת והינך מסכים/מה לאמור לעיל",Toast.LENGTH_LONG).show();
	}
}
public void back(View v) {
		
		
		finish();
	}


}
