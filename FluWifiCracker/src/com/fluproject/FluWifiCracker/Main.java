package com.fluproject.FluWifiCracker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private Button button_scan;
//	private Button button_flu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        this.button_scan = (Button)findViewById(R.id.button1);
        //this.button_flu = (Button)findViewById(R.id.button2);
        this.button_scan.setOnClickListener(this);
        //this.button_flu.setOnClickListener(this);

    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case (R.id.button1):
			Toast.makeText(this, "Escaneando redes wifi...", Toast.LENGTH_LONG).show();
			Intent wifiList = new Intent(this, WifiList.class);
			startActivity(wifiList);
			break;
		
//		case(R.id.button2):
//			Intent fluproject = new Intent(this, Navegador.class);
//			startActivity(fluproject);
//			break;
		
		}
	}
}