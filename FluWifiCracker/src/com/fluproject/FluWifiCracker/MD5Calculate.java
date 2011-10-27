package com.fluproject.FluWifiCracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Calculate {
    public String md5(String clave){
        String res = "";		// Una variable de tipo cadena para almacenar el MD5
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("MD5");		// Definimos un objeto "algoritmo" para recoger el MD5
            algoritmo.reset();					// Reseteamos el algoritmo
            algoritmo.update(clave.getBytes());		// Le pasamos los bytes de la clave al algoritmo usado para generar el MD5
            byte[] md5 = algoritmo.digest();		// Creamos un array de bytes para almacenar el hash
    		for (int i=0; i < md5.length; i++) {		// En este for calculamos el hash almacenándolo en la variable "res"
    			res +=
    				Integer.toString( ( md5[i] & 0xff ) + 0x100, 16).substring( 1 );
    		}
        } catch (NoSuchAlgorithmException ex) {}
        return res;		// Y devolvemos el MD5
    }
}
