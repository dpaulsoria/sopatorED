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
import java.util.Iterator;

/**
 *
 * @author danny
 */
public class Sopator {
    private final String ABC = "ABCDEFGKIJKLMNOPQRSTUVWXYZ";
    private int FILAS;
    private int COLUMNAS;
    private final String TEMA;
    private ArrayList<String> base_palabras;
    private ArrayList<String> palabras_validas;
    private final ArrayList<Pair> directions = new ArrayList<>();
    private ArrayList<CircularLinkedList<Character>> sopa_letras = new ArrayList<>();
    
    public Sopator(int fila, int columna, String tema) {
        FILAS = fila;
        COLUMNAS = columna;
        TEMA = tema;    
        añadir_direcciones();
        añadir_base_validas();       
        generar();
    }
    
    public String getABC() {
        return ABC;
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
            CircularLinkedList<Character> tmp = new CircularLinkedList<>();
            tmp = sopa_letras.get(i);
            tmp.addLast(getRandomChar());
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
    
    private void añadir_direcciones() {
        directions.addLast(new Pair(1 , 0));
        directions.addLast(new Pair(1 ,-1));
        directions.addLast(new Pair(0 ,-1));
        directions.addLast(new Pair(-1,-1));
        directions.addLast(new Pair(-1, 0));
        directions.addLast(new Pair(-1, 1));
        directions.addLast(new Pair(0 , 1));
        directions.addLast(new Pair(1 , 1));
    }
    
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
    
    private Integer getRandomNumber(int max) { // [0, max]
        return Integer.parseInt(Math.round(Math.floor(Math.random()*max)) + "");
    }
    
    private Character getRandomChar() { // [0, max]
        return ABC.charAt(getRandomNumber(ABC.length()));
    }
    
    private String getRandomWord() {
        return base_palabras.get(this.getRandomNumber(base_palabras.size()));
    }
    
    private void insertar_palabras_validas(int num_palabras_validas) {
        palabras_validas = new ArrayList<>();
        String random_word;
        for(int i=0; i<num_palabras_validas; i++) {
            random_word = getRandomWord();
            if (random_word.length() <= FILAS - 1 || random_word.length() <= COLUMNAS - 1) {
                palabras_validas.addLast(getRandomWord());
            }
        }
    }
    
    private void fillChar() {
        Character toReplace = '*';
        for(int f = 0; f<FILAS; f++) {
            CircularLinkedList<Character> fila = sopa_letras.get(f);
            for(int i = 0; i<COLUMNAS; i++) {
                if (fila.get(i).equals(toReplace)) {
                    fila.set(i, getRandomChar());
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
        int num_palabras = (getRandomNumber(2)+1)*(getRandomNumber(4)+1); 
        // +1 para evitar 0
        
        insertar_palabras_validas(num_palabras);
        System.out.println(palabras_validas.toString());

        
        for(int i = 0; i<palabras_validas.size(); i++) {
            tmp insercion = new tmp();
            insercion.setX(getRandomNumber(COLUMNAS));
            insercion.setY(getRandomNumber(FILAS));
            
            Pair p = directions.get(getRandomNumber(directions.size()));
            insercion.setRANDOM_DIRECTION(p);
            insercion.setRANDOM_WORD(palabras_validas.get(i));
                        
            if (validar_insercion(insercion)) {
                insertar_palabra(insercion);
            } else {
                i--;
            }
        }
        System.out.println(toString());
        fillChar();
    }

    private boolean validar_insercion(tmp insert) {
        String palabra = insert.getRANDOM_WORD();
        int pos_x = insert.getX();
        int pos_y = insert.getY();
        Pair direction = insert.getRANDOM_DIRECTION();
                
        int len = palabra.length();
        int dirX = direction.getX();
        int dirY = direction.getY();
        if (
            (pos_x + len * dirX) > 0 &&
            (pos_x + len * dirX) < COLUMNAS &&
            (pos_y + len * dirY) > 0 &&
            (pos_y + len * dirY) < FILAS
        ) {
            for (int i = 0; i<len; i++) {
                CircularLinkedList<Character> tmp = sopa_letras.get(pos_y + i * dirY);
                if (tmp.get(pos_x + i * dirX) == '*' ||
                    tmp.get(pos_x + i * dirX) == palabra.charAt(i)) {
                } else {
                    return false;
                }
               
           }
            return true;
        }
        return false;
    }
    
    private void insertar_palabra(tmp insert) {
        String palabra = insert.getRANDOM_WORD();
        int pos_x = insert.getX();
        int pos_y = insert.getY();
        Pair direction = insert.getRANDOM_DIRECTION();
        
        CircularLinkedList<Character> tmp;
        Character c;
        for(int i = 0; i<palabra.length(); i++) {
            tmp = sopa_letras.get(pos_y + i * direction.getY());
            c = tmp.get(pos_x + i * direction.getX());
            if (c.equals('*') || c.equals(palabra.charAt(i))) {
                tmp.set(pos_x + i * direction.getX(), palabra.charAt(i));
            }
        }
    }
}