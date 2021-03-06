package com.drop.freelance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static String NO_INTERNET_RECEPTION = "NO INTERNET RECEPTION";
	public static String ISRAEL_LOCALE = "עברית (ישראל)";// Contains israelLocal.toString() - the example database in Hebrew or English
	String mDigitalDate;  						// Contains the date of today
	private EditText mSearchBox;				// Contains the EditText at the top of the main screen
	public enum asinqJob { doLane,doAsinq,}     // What to do when returning from AsyncTask
	// Contains are we on equipmentAdapter or productAdapter
	String netFile;	    						// Contains the dropBox shareLink, will be saved at SharedPreferences
	// What day is today?
	private SQLiteDatabase mDb;					// Contains Database info
	Cursor cursor;								// Contains mDb info
	Set<String> lane ;							// Contains route list
	asinqJob job;			 					// What to do when returning from AsyncTask
	private Vector<Product> mVectorClient;		// Contains client list
	private Vector <String > mVectorEquipment;  // Contains Equipment list

	SharedPreferences Pref;	
	ListView lv; 
	ProductAdapter adapter;
	ProductOpenHelper helper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		lane = new HashSet<String>();
		job=asinqJob.doAsinq;

		mVectorClient = new Vector<Product>();
		mVectorEquipment= new Vector<String>();
		setContentView(R.layout.activity_main);
		mSearchBox = (EditText) findViewById(R.id.android_search_box);
		mSearchBox.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				fillFilteredData(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String font=Pref.getString("fontkey","20");
		SingelClient.getinstance().setmFont(Integer.parseInt(font));
		netFile="https://dl.dropboxusercontent.com/s/1bgklxjn25borom/client.txt?dl=1&token_hash=AAGxCS7TfRqiHB0pE-CdAjKczXBsqYkj6ancA3-L9CKj1g";
		helper = new ProductOpenHelper(getApplicationContext());
		if (!isOnline())  Toast.makeText(getApplicationContext(), NO_INTERNET_RECEPTION,Toast.LENGTH_LONG).show();
		mDb = helper.getWritableDatabase();
		cursor = mDb.rawQuery("SELECT * FROM CLIENTS;", null);
		if (cursor.getCount()<1) {  new ShowDialogAsyncTask(this).execute();}
		else { sqlWay(cursor);}


	}////////////////////////////////////////								/////////////ON     CREATE END//////////////////////////////


	public void sqlWay(Cursor cur){ 			//deals retrieving information from the hard disk (mobile memory)
		if (cur !=null){                           //no filter data
			cursor=cur;
			cursor.moveToFirst();
			mVectorClient.clear();
			int row=cursor.getCount();
			row--;
			for (int i = 0; i < cursor.getCount() ; i++) {
				System.out.print(i);
				Product Product = new Product();
				Product.setmName(cursor.getString(1));
				Product.setmMahut(cursor.getString(2));
				Product.setmPhone(cursor.getString(3));
				Product.setmStreet(cursor.getString(4));
				Product.setmFlag(cursor.getString(5));
				for (int j=6;j<12;j++){
						Product.setmAccessories(cursor.getString(j));
					
				} //if for j
				mVectorClient.add(Product);
				if (i<row) cursor.moveToNext();
			}  // for i

			
				adapter = new ProductAdapter(getApplicationContext());
				adapter.setVector(mVectorClient); //take the vector to the adapter
				lv = (ListView)findViewById(android.R.id.list);
				lv.setAdapter(adapter);
				registerForContextMenu(lv);
				lv.setTextFilterEnabled(true);
				lv.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View myview,
							int position, long viewid) {
						SingelClient.getinstance().setmMahut(adapter.getmMahut(position));
						SingelClient.getinstance().setmStreet(adapter.getmStreet(position));
						SingelClient.getinstance().setmPhone(adapter.getmPhone(position));
						SingelClient.getinstance().setmName(adapter.getmName(position));
						SingelClient.getinstance().setmAccessories(adapter.getmAccessories(position));
						Intent myIntent= new Intent(MainActivity.this,client.class);
						startActivity(myIntent);

					}
				});//nItemClickListener()

				adapter.notifyDataSetChanged();
								
		}//if (cur==null)

	}//sqlway//////////////////////////////  SQLWAY END  //////////////////



	private void fillFilteredData(CharSequence s) {				// deals filtering rows using the editText on the top of the screen
		
		Cursor mCursor = mDb.rawQuery("SELECT * FROM CLIENTS WHERE mahut LIKE '%" + s + "%';", null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		sqlWay(mCursor);
	}

	public boolean isOnline() {								// check if Internet connection onLine 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	public class ShowDialogAsyncTask extends AsyncTask<Void, Void, Void>{// deals retrieving information from DropBob server

		private ProgressDialog dialog;
		private Context context;
		public ShowDialogAsyncTask(Context cxt) {
			context = cxt;
			dialog = new ProgressDialog(context);
		}	

		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setTitle("Please wait");
			dialog.show();
			if (!isOnline())  Toast.makeText(getApplicationContext(), "אין חיבור לאינטרנט - NO INTERNET RECEPTION",Toast.LENGTH_LONG).show();
			mDb = helper.getWritableDatabase();
			mDb.delete("CLIENTS", null, null);
			mVectorClient.clear();
			mVectorEquipment.clear();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {

			HttpClient client = new DefaultHttpClient(); //
			HttpGet request = new HttpGet(netFile);

			try {
				HttpResponse response = client.execute(request);// 
				HttpEntity entity = response.getEntity();
				InputStreamReader in = new InputStreamReader(entity.getContent(),"UCS-2");
				BufferedReader reader = new BufferedReader(in);
				StringTokenizer tokens;
				String sCurrentLine  ;



				while ((sCurrentLine = reader.readLine()) != null) {
					sCurrentLine = sCurrentLine.trim().replace('"',' ');
					tokens = new StringTokenizer(sCurrentLine, ",");
					
					Product product = new Product();
					product.setmDate(tokens.nextToken());
					product.setmName(tokens.nextToken());
					product.setmMahut(tokens.nextToken());
					product.setmPhone(tokens.nextToken());
					product.setmStreet(tokens.nextToken());
					if (tokens.nextToken().equals("no")){
						product.setmFlag("0");
					}
					else product.setmFlag("2");
					for (int mone=0 ;mone < 6; mone++ ){
						String s;
						s =	tokens.nextToken();
						product.setmAccessories(s);
						if (!s.equals("no")) mVectorEquipment.add(s) ;//separate vector that for the use of equipment adapter

					}

					mDb.execSQL("INSERT INTO CLIENTS VALUES ('" + product.getmDate() + "','" + product.getmName()
							+ "','" + product.getmMahut() +"','" + product.getmPhone() +"','" + product.getmStreet() 
							+"','" + product.getmFlag() +"','" +product.getmAccessories(0) +"','" + product.getmAccessories(1) +
							"','" + product.getmAccessories(2)+"','" + 
							product.getmAccessories(3)+"','" + product.getmAccessories(4)+"','" + product.getmAccessories(5)+ "')"); 	



					mVectorClient.add(product);

				}

			} 
			catch (UnknownHostException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {

			super.onProgressUpdate(values);
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (job==asinqJob.doLane) {
				String[] result1 = new String[lane.size()];
				lane.toArray(result1);
				SingelClient.getinstance().setMlane(result1);
				Intent intent1 = new Intent(MainActivity.this,Lane.class);
				startActivityForResult(intent1, 5);
				job=asinqJob.doAsinq;
			}
			adapter = new ProductAdapter(getApplicationContext());
			adapter.setVector(mVectorClient); //take the vector to the adapter
			lv = (ListView)findViewById(android.R.id.list);
			lv.setAdapter(adapter);
			registerForContextMenu(lv);
			lv.setTextFilterEnabled(true);
			dialog.dismiss();
			dialog = null;
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View myview,
						int position, long viewid) {
					SingelClient.getinstance().setmMahut(adapter.getmMahut(position));
					SingelClient.getinstance().setmStreet(adapter.getmStreet(position));
					SingelClient.getinstance().setmPhone(adapter.getmPhone(position));
					SingelClient.getinstance().setmName(adapter.getmName(position));
					SingelClient.getinstance().setmAccessories(adapter.getmAccessories(position));
					Intent myIntent= new Intent(MainActivity.this,client.class);
					startActivity(myIntent);

				}
			});
		}
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);

		if   (resultCode==Activity.RESULT_OK && requestCode==5){

			new ShowDialogAsyncTask(this).execute();
			adapter.notifyDataSetChanged();
		}
		else{
			Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
			edit.apply();
			SharedPreferences Pref = PreferenceManager.getDefaultSharedPreferences(this);
			String font=Pref.getString("fontkey","20");
			String displayType=Pref.getString("displaykey","2" );
			SingelClient.getinstance().setmDisplayType(Integer.parseInt(displayType));
			SingelClient.getinstance().setmFont(Integer.parseInt(font));


			lv.invalidateViews();   
			adapter.notifyDataSetChanged();     
		}
		if (!isOnline()) { Toast.makeText(getApplicationContext(), NO_INTERNET_RECEPTION,Toast.LENGTH_LONG).show();
		sqlWay(cursor);
		}
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu, menu);
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		//adapter.setListindex(info.position);
		int itemId = item.getItemId();
		if (itemId == R.id.buynotbuy) {
			int pos=info.position;
			if ((mVectorClient.get(pos).getmFlag().equals("0"))){
				mVectorClient.get(pos).setmFlag("2");
				mDb.execSQL("UPDATE CLIENTS SET flag='1' WHERE NAME=" + "'"+ mVectorClient.get(pos).getmName() + "';");
			} else {
				mVectorClient.get(pos).setmFlag("0");

				mDb.execSQL("UPDATE CLIENTS SET flag='0' WHERE NAME=" + "'"+ mVectorClient.get(pos).getmName() + "';");
			}
			adapter.notifyDataSetChanged();
			return true;
		} else if (itemId == R.id.call) {
			String tel = "tel:" + adapter.getmPhone(info.position) ;
			Intent intent = null;
			intent = new Intent(Intent.ACTION_DIAL,Uri.parse(tel));
			startActivity(intent);
			adapter.notifyDataSetChanged();
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		
		int itemId = item.getItemId();
		if (itemId == R.id.defsettings) {
			Intent intent = new Intent(MainActivity.this,
					PrefsActivity.class);
			startActivityForResult(intent, 0);
		} else if (itemId == R.id.ic_loadlist) {
			mVectorClient.clear();
			if (!isOnline()) { Toast.makeText(getApplicationContext(), NO_INTERNET_RECEPTION,Toast.LENGTH_LONG).show();}
			else { new ShowDialogAsyncTask(this).execute();
			adapter.notifyDataSetChanged();
			}
		} 
		else if (itemId == R.id.lane) {

			Intent intent1 = new Intent(MainActivity.this,Lane.class);
			startActivityForResult(intent1, 5);
		}    




		return super.onOptionsItemSelected(item);

	}
	@Override
	protected void onStop() {


		SharedPreferences.Editor editor =Pref.edit();
		editor.commit();
		//mDb.close();
		super.onStop();
	}
	public void onBackPressed(){


		cursor =null;
		lane =null;
		adapter=null;
		
		mVectorClient=null;
		mVectorEquipment=null;
		mDb.close();
		System.gc();
		finish();


	}//onback
}//class
