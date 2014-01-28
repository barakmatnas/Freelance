package com.drop.freelance;

import java.util.Vector;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ProductAdapter extends BaseAdapter {
	int imageSize;
	int fontSize;
	Boolean changeFont;
	int flagDisplay;
	Boolean line;
	private Vector<Product> mVectorProduct;
	protected Context mContext = null;
	private static int listindex=0;

	public static int getListindex() {

		return listindex;
	}

	public static void setListindex(int listindex) {
		ProductAdapter.listindex = listindex;
	}

	public void setVector(Vector<Product> vec) {
		mVectorProduct = new Vector<Product>();
		mVectorProduct =vec ;
	}

	public String getmName(int position) {
		return mVectorProduct.get(position).getmName();
	}public String getmMahut(int position) {
		return mVectorProduct.get(position).getmMahut();
	}public String getmPhone(int pos) {
		return mVectorProduct.get(pos).getmPhone();
	}
	public String getmStreet(int position) {
		return mVectorProduct.get(position).getmStreet();
	}
	public String[] getmAccessories(int position) {
		return mVectorProduct.get(position).getmAccessories();
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVectorProduct.size();
	}
	public void setBuyNotBuy(int pos) {
		if ((mVectorProduct.get(pos).getmFlag().equals("0"))|(mVectorProduct.get(pos).getmFlag().equals("2"))) {
			mVectorProduct.get(pos).setmFlag("1");

		} else {
			mVectorProduct.get(pos).setmFlag("0");
		}

	}
	public ProductAdapter(Context context) {
		super();


		fontSize =SingelClient.getinstance().getmFont();
		mContext = context;
		changeFont=false;
		if (fontSize>25) imageSize=fontSize*2;
		else imageSize=fontSize*1;
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
		flagDisplay=SingelClient.getinstance().getmDisplayType();
		if (fontSize !=SingelClient.getinstance().getmFont()){
			fontSize =SingelClient.getinstance().getmFont();

			changeFont=true;

		}
		if (changeFont) convertView = null;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


			if (fontSize<25) rowView = inflater.inflate(R.layout.linearone, parent, false);
			else rowView = inflater.inflate(R.layout.linearlayout, parent, false);


		} else {
			rowView = convertView;
		}

		ImageView imageView = (ImageView) rowView.findViewById(R.id.flag);
		TextView textView2 = (TextView) rowView.findViewById(R.id.name);
		TextView textView1 = (TextView) rowView.findViewById(R.id.mahut);
		textView1.setTextSize(fontSize);
		textView2.setTextSize(fontSize);


		if (mVectorProduct.get(position).getmFlag().equals("0")) {
			imageView.setImageResource(R.drawable.x);
		} else if (mVectorProduct.get(position).getmFlag().equals("1")) {
			imageView.setImageResource(R.drawable.v);
		}
		else imageView.setImageResource(R.drawable.barcode);

		if (flagDisplay == 1 ) {

			textView1.setText(mVectorProduct.get(position).getmName());
			textView2.setText(mVectorProduct.get(position).getmMahut());

		}
		else{

			textView1.setText(mVectorProduct.get(position).getmName());
			textView2.setText(mVectorProduct.get(position).getmStreet());   
		}
		textView1.setWidth(140);


		textView2.setWidth(150);


		return rowView;
	}

	public void delProduct(int pos) {
		mVectorProduct.remove(pos);
	}

}
