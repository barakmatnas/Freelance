package com.drop.freelance;
public class Product {
	private int accCount=0;
	private String mName;
	private String mMahut;
	private String mPhone;
    private String mStreet;
	private String mFlag;
    private String mAccessories[];
    
    
	public String getmFlag(){
		return mFlag;
	}

	public void setmFlag(String mFlag) {
		this.mFlag = mFlag;
	}
	
	
	
	
	

	public String getmMahut() {
		return mMahut;
	}

	public void setmMahut(String mMahut) {
		this.mMahut = mMahut;
	}

	public String[] getmAccessories() {
		return mAccessories;
	}
	public String getmAccessories(int i) {
		return mAccessories[i];
	}

	public void setmAccessories(String string) {
		mAccessories[accCount] = string;
		accCount++;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	

	

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmStreet() {
		return mStreet;
	}

	public void setmStreet(String mStreet) {
		this.mStreet = mStreet;
	}

	



	public Product() {
		super();
		this.mAccessories = new String [6];
	}
	public Product(String mName, String mPhone, String mStreet,
			 String mMarks) {
		super();
		this.mName = mName;
		this.mPhone = mPhone;
		this.mStreet = mStreet;
		this.mAccessories = new String [6];
		this.mMahut = mMarks;
	}
}
