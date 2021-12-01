/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import collection.ArrayList;
import collection.CircularLinkedList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author danny
 */
public class Sopator {
    private String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int FILAS;
    private int COLUMNAS;
    private final String TEMA;
    private ArrayList<String> base_palabras;
    private ArrayList<String> palabras_validas;
    private ArrayList<CircularLinkedList<Character>> sopa_letras = new ArrayList<>();
    
    public Sopator(int fila, int columna, String tema) {
        FILAS = fila;
        COLUMNAS = columna;
        TEMA = tema;
        añadir_base_validas();
        generar();
    }

    
    public int getCant_Validas() {
        return palabras_validas.size();
    }
    
    public int getFilas() {
        return FILAS;
    }
    
    public int getColumnas() {
        return COLUMNAS;
    }
    
    public String getTema() {
        return TEMA;
    }
    
    public ArrayList<String> getBase_Palabras() {
        return base_palabras;
    }
    
    public ArrayList<String> getPalabras_Validas() {
        return palabras_validas;
    }
    
    public ArrayList<CircularLinkedList<Character>> getSopa_Letras() {
        return sopa_letras;
    }
    
    public void reemplazarLetra(int y, int x, Character letra) {
        CircularLinkedList<Character> fila = sopa_letras.get(y);
        fila.set(x, letra);
    }
    
//    public void reorganizarAleatoriamente() {
//        for(int f = 0; f<FILAS; f++) {
//            CircularLinkedList<Character> fila = sopa_letras.get(f);
//            for(int c = 0; c<COLUMNAS; c++) {
//                fila.set(c, getRandomChar());
//            }
//        }
//    }
    private void añadir_base_validas() {
        base_palabras = new ArrayList<>();
        String current_word;
        String ruta = "src\\" + TEMA;
        try(BufferedReader br = new BufferedReader(new FileReader(ruta + ".txt"))) {
            while((current_word = br.readLine()) != null) {
                base_palabras.addLast(current_word);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public Character getLetra(int fila, int columna) {
        return sopa_letras.get(fila).get(columna);
    }

    public void desplazarFilaDer(int fila) {
        int i = 1;
        for(CircularLinkedList<Character> c:sopa_letras) {
            if (i == fila) {
                c.desplazarDer();
                break;
            }
            i++;
        }
    }
    
    public void desplazarFilaIzq(int fila) {
        int i = 1;
        for(CircularLinkedList<Character> c:sopa_letras) {
            if (i == fila) {
                c.desplazarIzq();
                break;
            }
            i++;
        }
    }
    
    public void añadirFila() {
        FILAS++;
        CircularLinkedList<Character> filaNueva = new CircularLinkedList<>();
        for (int i = 0; i<COLUMNAS; i++) {
            filaNueva.addLast(getRandomChar());
        }
        sopa_letras.addLast(filaNueva);
    }
    
    public void añadirColumna() {
        COLUMNAS++;        
        for (int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Character> tmp = sopa_letras.get(i);
            tmp.addLast(getRandomChar());
        }
    }
    public void añadirFila(int fila) {
        FILAS++;
        CircularLinkedList<Character> filaNueva = new CircularLinkedList<>();
        for (int i = 0; i<COLUMNAS; i++) {
            filaNueva.addLast(getRandomChar());
        }
        sopa_letras.add(fila, filaNueva);
    }
    
    public void añadirColumna(int col) {
        COLUMNAS++;        
        for (int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Character> tmp = sopa_letras.get(i);
            tmp.add(col, getRandomChar());
        }
    }
    public void eliminarFila(int fila) {
        sopa_letras.remove(fila-1);
    }
    
    public void eliminarColumna(int columna) {
        for (int i = 0; i<sopa_letras.size(); i++) {
            sopa_letras.get(i).remove(columna-1);
        }
    }
    
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Character> tmp = sopa_letras.get(i);
            for(int j = 0; j<tmp.size(); j++) {
                result += tmp.get(j) + " ";
                
            }
            result += "\n";
        }
        return result;
    }
    
    private Integer getRandomNumber(int max) { // [0, max]
        return Integer.parseInt(Math.round(Math.floor(Math.random()*max)) + "");
    }
    
    private Character getRandomChar() { // [0, max]
        return ABC.charAt(getRandomNumber(ABC.length()));
    }
    
    private void fillChar() {
        Character toReplace = '*';
        for(int f = 0; f<FILAS; f++) {
            CircularLinkedList<Character> fila = sopa_letras.get(f);
            for(int c = 0; c<COLUMNAS; c++) {
                if (fila.get(c).equals(toReplace)) {
                    fila.set(c, getRandomChar());
                }
            }
        }
    }
    

    private void rellenar() {
        Character c = '*';
        for(int i = 0; i<FILAS; i++) {
            CircularLinkedList<Character> tmp = new CircularLinkedList();
            for(int j = 0; j<COLUMNAS; j++) {
                tmp.addLast(c);
            }
            sopa_letras.addLast(tmp);
        }
    }
    
    private void generar() {
        rellenar();
        fillChar();
    }

}