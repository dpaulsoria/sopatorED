/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.generator;

import espol.collection.CircularLinkedList;
import espol.collection.CircularNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author danny
 */
public class Soup {
    
    private final int FILAS;
    private final int COLUMNAS;
    private HashMap<Integer, CircularLinkedList<Character>> filas_palabras = new HashMap<>();;
    private Set<String> palabras_validas;
    private final String TEMA;
    private final String RUTA_BASE = "src\\main\\java\\espol\\base\\";
    private final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public Soup(int filas, int columnas, String tema) {
        this.FILAS = filas;
        this.COLUMNAS = columnas;
        this.TEMA = tema;
        //this.generarPalabrasValidas();|
    }
    
    public double getRandomNumber(int max) { // [0, max]
        int n = Integer.parseInt(Math.floor(Math.random()*max) + "");
        return Math.floor(Math.random()*max);
    }
//    public Character generar_random() {
//        int random_number = Math.floor(Math.random()*(ABC.length()+1));
//        return ABC.charAt(random_number);
//    }
    
    
    
//    public void generar() {
//        String file = filas + "x" + columnas + "_" + tema + ".txt";
//        CircularLinkedList<Character> linked = new CircularLinkedList();
//        String currentLine;
//        int c, k = 0;
//        try (BufferedReader br = new BufferedReader(new FileReader(ruta_base + file))) {
//            while((currentLine = br.readLine()) != null) {
//                c = 0;
//                for(String s:currentLine.split("")) {
//                    System.out.println(s.charAt(0));
//                    linked.add(c, s.charAt(0));
//                    c++;
//                }                
//                System.out.println(currentLine);
//                filas_palabras.put(k, linked);
//                k++;
//                linked.clear();
//            }
//            br.close();
//        } catch(Exception e) {
//            System.err.println(e.toString());
//        }
//    }
    private void generarPalabrasValidas() {
        switch(TEMA) {
            case "ANIMALES":
                Set<String> set = new HashSet<>();
                set.add("PERRO");
                set.add("GATO");
                set.add("MAPACHE");
                set.add("OSO");
                set.add("LEON");
                set.add("TIGRE");
                set.add("ELEFANTE");
                this.palabras_validas = set;
                break;
            default:
                break;
        }
        
    }
}
