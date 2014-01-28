package com.drop.freelance;

import java.util.Vector;

public class SingelClient {
  private static SingelClient instance=null;
  public static SingelClient getinstance(){
	  if (instance==null)instance=new SingelClient();
	return instance;
  }
  private Vector<String> equipment;
  private int mfont;
  public Vector<String> getEquipment() {
	return equipment;
}
public void setEquipment(Vector<String> mVectorEquipment) {
	this.equipment = mVectorEquipment;
}
private int mdisplayType;
  public int getmFont() {
	return mfont;
}
public void setmFont(int font) {
	this.mfont = font;
}
public int getmDisplayType() {
	return mdisplayType;
}
public void setmDisplayType(int mdisplayType) {
	this.mdisplayType = mdisplayType;
}
  private String mName;
	private String mMahut;
	private String mPhone;
  private String mStreet;
	
  private String mFlag;
  private String mAccessories[];
  private String mlane[];
  
  
  
public String[] getMlane() {
	return mlane;
}
public void setMlane(String[] mlane) {
	this.mlane = mlane;
}
public String getmName() {
	return mName;
}
public void setmName(String mName) {
	this.mName = mName;
}
public String getmMahut() {
	return mMahut;
}
public void setmMahut(String mMahut) {
	this.mMahut = mMahut;
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

public String getmFlag() {
	return mFlag;
}
public void setmFlag(String mFlag) {
	this.mFlag = mFlag;
}
public String[] getmAccessories() {
	return mAccessories;
}
public void setmAccessories(String[] mAccessories) {
	this.mAccessories = mAccessories;
}
}
