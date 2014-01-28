package com.drop.freelance;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProductOpenHelper extends SQLiteOpenHelper {

	public ProductOpenHelper (Context context) {
		super(context,"CLIENTS",null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE CLIENTS ( mDate TEXT NOT NULL , name TEXT NOT NULL, mahut TEXT NOT NULL , phone TEXT NOT NULL " +
				", street TEXT NOT NULL , flag TEXT NOT NULL , e1 TEXT NOT NULL , e2 TEXT NOT NULL , e3 TEXT NOT NULL ," +
				" e4 TEXT NOT NULL , e5 TEXT NOT NULL  , e6 TEXT NOT NULL)");




	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
