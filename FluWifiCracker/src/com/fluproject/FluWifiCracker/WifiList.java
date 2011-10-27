package com.fluproject.FluWifiCracker;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WifiList extends Activity implements OnItemClickListener {

	private Element[] nets;
	private WifiManager manWifi;
	private List<ScanResult> wifiList;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Configura escaner.
        this.manWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        this.manWifi.startScan();
        this.wifiList = this.manWifi.getScanResults();
	    
        //Configura lista adaptadora de wifis.
        this.nets = new Element[wifiList.size()];
	    for(int i = 0; i < wifiList.size(); i++){
	    	  String item = wifiList.get(i).toString();
	    	  String[] vector_item = item.split(",");
	    	  String item_essid = vector_item[0];
	    	  String item_capabilities = vector_item[2];
	    	  String item_level = vector_item[3];
	    	  String ssid = item_essid.split(":")[1];
	    	  String security = item_capabilities.split(":")[1];
	    	  String power = item_level.split(":")[1];
		      nets[i]=new Element(ssid, security, power);    
	   	}    
        //Configura lista final de wifis.
        setContentView(R.layout.wifilist);
        AdapterElements adapter = new AdapterElements(this);
        ListView netlist = (ListView)findViewById(R.id.listView1);
        netlist.setAdapter(adapter);
        netlist.setOnItemClickListener(this);
	}
	
	class AdapterElements extends ArrayAdapter<Object> {

		Activity context;
		
		public AdapterElements(Activity context) {
    		super(context, R.layout.elements, nets);
    		this.context = context;
		}
    	    	
    	public View getView(int position, View convertView, ViewGroup parent) {
		    
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.elements, null);
			TextView lblTitle = (TextView)item.findViewById(R.id.str_ssid);
			lblTitle.setText(nets[position].getSSID());
			TextView lblSubtitle = (TextView)item.findViewById(R.id.str_security);
			lblSubtitle.setText(nets[position].getSecurity());
			ImageView imgPower = (ImageView)item.findViewById(R.id.img_signal);
			String cad_pwr = nets[position].getPower().split(" ")[1].toString();
			Integer pwr = Integer.parseInt(cad_pwr);
			//Selección de icono de cobertura.
			if (pwr >= -70){	//Potencia alta.
				imgPower.setImageResource(R.drawable.wsignal4);
			}
			else if ((pwr < -71) && (pwr >= -80)){ //Potencia media-alta.
				imgPower.setImageResource(R.drawable.wsignal3);
			}
			else if ((pwr < 81) && (pwr >= 85)){ //Potencia media-baja.
				imgPower.setImageResource(R.drawable.wsignal2);
			}
			else{	//Potencia baja.
				imgPower.setImageResource(R.drawable.wsignal1);
			}
			return(item);
		}
    }

	@Override
	public void onItemClick(AdapterView<?> av, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		//Sacamos toda la información de la red en una Activity nueva.
		String item =this.wifiList.get(position).toString();
		String[] vector_item = item.split(",");
  	  	String item_ssid = vector_item[0];
  	  	String item_bssid = vector_item[1];
  	  	String item_capabilities = vector_item[2];
  	  	String item_level = vector_item[3];
  	  	String item_frecuency = vector_item[4];
  	  	String ssid = item_ssid.split(":")[1].substring(1);
  	  	String bssid = item_bssid.substring(7).split(" ")[1];
  	  	String sec_aux = item_capabilities.split(":")[1];
  	  	String security=null;
  	  	if ((sec_aux).equals(" ")){
  	  		security = "No security";
  	  	}
  	  	else{
  	  		security = sec_aux.split(" ")[1];
  	  	}
  	  	String level = item_level.split(":")[1].split(" ")[1];
  	  	String frecuency = item_frecuency.split(":")[1].split(" ")[1];
  	  	//Creamos Intent nueva
  	  	Intent wifiinfo = new Intent(this, WifiInfo.class);
  	  	wifiinfo.putExtra("ssid", ssid);
  	  	wifiinfo.putExtra("bssid", bssid);
  	  	wifiinfo.putExtra("security", security);
  	  	wifiinfo.putExtra("power", level);
  	  	wifiinfo.putExtra("frecuency", frecuency);
  	  	startActivity(wifiinfo); 	
	}
	
}
