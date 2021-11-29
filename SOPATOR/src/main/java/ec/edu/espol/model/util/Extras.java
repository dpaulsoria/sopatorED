package ec.edu.espol.model.util;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Extras {
    //MÉTODO EXTRA UTILITARIO
    public static byte[] getSHA(String i) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-512").digest(i.getBytes(StandardCharsets.UTF_8));
    }
    public static String toHexString(byte[] hash) { 
        while ((new StringBuilder((new BigInteger(1, hash)).toString(16))).length() < 32) {  
            (new StringBuilder((new BigInteger(1, hash)).toString(16))).insert(0, '0');  
        }
        return (new StringBuilder((new BigInteger(1, hash)).toString(16))).toString();  
    } 
    public static String claveHex(String Clave){
        try {
            return toHexString(getSHA(Clave));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Excepción arroja un algoritmo incorrecto: " + e);
        }
        return null;
    }
    public static String capitalize(String str) {
        str = str.toLowerCase();
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static void serializar(HashMap<String, Object> mapa, String tipo) {
        System.out.println("Serializando");
        try(ObjectOutputStream streamOut = new ObjectOutputStream(new FileOutputStream(tipo.toUpperCase()+".ser"));){
            streamOut.writeObject(mapa); System.out.println("Mapeando");
            streamOut.flush(); System.out.println("Flush");
        } catch(IOException e) {
            System.out.println(e.toString()); System.out.println("Error serializando");
        }
        
    }
    
    public static HashMap<String, Object> deserializar(String tipo) {
        System.out.println("Deserializando");
        try(ObjectInputStream streamIn = new ObjectInputStream(new FileInputStream(tipo.toUpperCase()+".ser"));){
            HashMap<String, Object> mapa = (HashMap<String, Object>)streamIn.readObject();
            System.out.println("Retornando mapa");
            return mapa;
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Mapa = NULL");
            return null;
        }
    }
}

