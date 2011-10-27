package com.fluproject.FluWifiCracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WifiInfo extends Activity implements OnClickListener {
	
    private Button button_copy;
    String str_key;
    private WifiManager manWifi;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.wifiinfo);
        Bundle extras = getIntent().getExtras();
        String ssid=null;
        String bssid=null;
        String security=null;
        String power=null;
        String frecuency=null;
        if (extras != null){
        	ssid = extras.getString("ssid");
        	bssid = extras.getString("bssid");
        	security = extras.getString("security");
        	power = extras.getString("power");
        	frecuency = extras.getString("frecuency");
        }
        TextView t_ssid = (TextView)findViewById(R.id.str_i_ssid);
        t_ssid.setText(ssid);
        TextView t_bssid = (TextView)findViewById(R.id.str_i_bbsid);
        t_bssid.setText(bssid);
        TextView t_security = (TextView)findViewById(R.id.str_i_sec);
        t_security.setText(security);
        TextView t_power = (TextView)findViewById(R.id.str_i_pw);
        t_power.setText(power);
        TextView t_frec = (TextView)findViewById(R.id.str_i_frec);
        t_frec.setText(frecuency);
     
        //Viendo si es crackeable:
    	TextView t_crack = (TextView)findViewById(R.id.str_i_crack);
        if (ssid.contains("_")){
	        String [] item_ssid = ssid.split("_");
	        String ssid_name = item_ssid[0];
	        String ssid_code = item_ssid[1];
	        String[] bssid_final = bssid.split(":");
	        String bssid_bueno =( bssid_final[0]+bssid_final[1]+bssid_final[2]+bssid_final[3]+bssid_final[4]+bssid_final[5]);
        	if (ssid_name.equals("JAZZTEL") || (ssid_name.equals("WLAN"))){ //Nombre crackeable.
	        	if (ssid_code.length() == 4){	//Código crackeable.
		        	KeyGenerator key = new KeyGenerator();
		        	String bssid_mayus = bssid_bueno.toUpperCase();
		        	str_key = key.calcularClave(ssid_code, bssid_mayus);
		        	t_crack.setText("\"+str_key+"\");
		        	t_crack.setTextColor(Color.GREEN);
		        	//Boton para copiar clave		        	
		            this.button_copy = (Button)findViewById(R.id.button2);
		            this.button_copy.setOnClickListener(this);

	        	}
	        	else{
	        		t_crack.setText("NO");
		        	t_crack.setTextColor(Color.RED);
	        	}
	        }
	        else{
	        	t_crack.setText("NO");
	        	t_crack.setTextColor(Color.RED);
	        }
        }
        else{
        	t_crack.setText("NO");
        	t_crack.setTextColor(Color.RED);
        }
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		
		case(R.id.button2):
        	ClipboardManager clipboard = 
		      (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
	        clipboard.setText(str_key);
	        this.manWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	        WifiConfiguration wc = new WifiConfiguration();
	        wc.SSID = "\"SSIDName\"";
	        wc.preSharedKey  = str_key;
	        wc.hiddenSSID = true;
	        wc.status = WifiConfiguration.Status.ENABLED;        
	        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
	        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
	        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
	        wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
	        int res = manWifi.addNetwork(wc);
//	        Log.d("WifiPreference", "add Network returned " + res );
	        boolean b = manWifi.enableNetwork(res, true);        
//	        Log.d("WifiPreference", "enableNetwork returned " + b );

			break;
		
		}
	}
}
