package com.drop.freelance;

import java.util.Vector;



import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Equipments extends Activity { 

	EquipmentAdapter Vadapter;
	private Vector <String > mEquipment;
	private ListView lv;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mEquipment= SingelClient.getinstance().getEquipment();
		setContentView(R.layout.equipments);
		Vadapter = new EquipmentAdapter(getApplicationContext());
		Vadapter.setVector(mEquipment); //take the vector to the adapter
		lv = (ListView)findViewById(android.R.id.list);
		lv.setAdapter(Vadapter);




	}
}










