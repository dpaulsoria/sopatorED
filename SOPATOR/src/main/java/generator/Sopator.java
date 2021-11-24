/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import collection.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author danny
 */
public class Sopator {
    private final String ABC = "ABCDEFGKIJKLMNOPQRSTUVWXYZ";
    private int FILAS;
    private int COLUMNAS;
    private final String TEMA;
    private Set<String> palabras_validas;
    private final ArrayList<Direction> directions = new ArrayList<>();
    
    public Sopator(int fila, int columna, String tema) {
        añadir_direcciones();
        
        FILAS = fila;
        COLUMNAS = columna;
        TEMA = tema;
        
    }
    
    private void añadir_direcciones() {
        directions.add(0, new Direction(1, 0));
        directions.add(0, new Direction(1,-1));
        directions.add(0, new Direction(0, -1));
        directions.add(0, new Direction(-1,-1));
        directions.add(0, new Direction(-1, 0));
        directions.add(0, new Direction(-1, 1));
        directions.add(0, new Direction(0, 1));
        directions.add(0, new Direction(1, 1));
    }
    
    private void añadir_palabras_validas() {
        palabras_validas = new HashSet<>();
        String current_word;
        try(BufferedReader br = new BufferedReader(new FileReader(TEMA + ".txt"))) {
            while((current_word = br.readLine()) != null) {
                if (palabras_in_soup()) {
                    palabras_validas.add(current_word);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    private boolean palabras_in_soup() {
        return true;
    }
    
    private int getRandomNumber(int max) { // [0, max]
        int n = max;
        return Integer.valueOf(Math.floor(Math.random()*max) + "");
    }
}
