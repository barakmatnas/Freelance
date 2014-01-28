package com.drop.freelance;

import java.util.Vector;

import com.drop.cloud.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EquipmentAdapter  extends BaseAdapter {
	int fontSize;
	private Vector<String> mVec;
	protected Context mContext = null;


	public void setVector(Vector<String> vec) {
		mVec = new Vector<String>();

		mVec =vec ;
		fontSize =SingelClient.getinstance().getmFont();

	}

	public EquipmentAdapter(Context context) {
		super();


		mContext = context;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView;


		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.equipment, parent, false);
		} else {
			rowView = convertView;
		}


		TextView textView1 = (TextView) rowView.findViewById(R.id.equip1);
		textView1.setText(mVec.get(position));
		textView1.setTextSize(fontSize);
		textView1.setWidth(230);
		return rowView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVec.size();
	}

}
