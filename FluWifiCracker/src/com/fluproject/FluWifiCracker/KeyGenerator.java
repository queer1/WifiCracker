package com.fluproject.FluWifiCracker;

public class KeyGenerator {
	MD5Calculate obtenerMd5;		// Necesario para llamar a la clase encargada de calcular la suma MD5
	
	public String calcularClave(String essid,String bssid){		
		obtenerMd5 = new MD5Calculate();		// Creamos el nuevo objeto
		String newBssid = bssid.substring(0,8);		// Del BSSID nos quedamos con los primeros 8 dígitos y los almacenamos en una variable
		String clave = "bcgbghgg" + newBssid + essid + bssid;	// Concatenamos la cadena "bcgbghgg" a los 8 primeros dígitos del BSSID y le añadimos el ESSID y el BSSID (Determinado por el algoritmo utilizado para generar las claves)
		String resultado = obtenerMd5.md5(clave);			// Calculamos la suma MD5 del resultado de concatenar los datos necesarios
		return resultado.substring(0,20);			// Devolvemos los 20 primeros caracteres de la suma MD5 (que forman la clave de la red)
	}
}
