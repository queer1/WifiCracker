package com.fluproject.FluWifiCracker;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Navegador extends Activity{
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navegador);
        
        WebView navegador = (WebView) findViewById(R.id.navegador);
        WebSettings settings = navegador.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        navegador.loadUrl("http://www.flu-project.com");

        
    }

}
