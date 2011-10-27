package com.fluproject.FluWifiCracker;

public class Element {
	private String ssid;
	private String security;
	private String power_signal;
	
	public Element(String t, String s, String p){
		this.ssid=t;
		this.security=s;
		this.power_signal=p;
	}
	
	public String getSSID(){
		return this.ssid;
	}
	
	public String getSecurity(){
		return this.security;
	}
	
	public String getPower(){
		return this.power_signal;
	}

}
