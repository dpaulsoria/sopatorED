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
import java.util.HashMap;
import java.util.Map;

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
    private int cantidad_validas = 0;
    private final ArrayList<Pair> directions = new ArrayList<>();
    private Map<Integer, CircularLinkedList<Character>> sopa_letras = new HashMap<>();
    
    public Sopator(int fila, int columna, String tema) {
        FILAS = fila;
        COLUMNAS = columna;
        TEMA = tema;
        System.out.println("Creando sopa de letras de: " + FILAS + "x" + COLUMNAS + " con el tema de " + TEMA);
        rellenar();
        /*
        añadir_direcciones();
        añadir_base_validas();
        
        generar();
        */
    }
    
    @Override
    public String toString() {
        String result = "";
        CircularLinkedList tmp;
        for(int i = 0; i<FILAS; i++) {
            tmp = sopa_letras.get(i);
            for(int j = 0; j<COLUMNAS; j++) {
                result += tmp.get(j) + " ";
            }
            result += "\n";
            System.out.println(result);
            tmp.clear();
        }
        return result;
    }
    
    private void añadir_direcciones() {
        directions.add(0, new Pair(1, 0));
        directions.add(1, new Pair(1,-1));
        directions.add(2, new Pair(0, -1));
        directions.add(3, new Pair(-1,-1));
        directions.add(4, new Pair(-1, 0));
        directions.add(5, new Pair(-1, 1));
        directions.add(6, new Pair(0, 1));
        directions.add(7, new Pair(1, 1));
    }
    
    private void añadir_base_validas() {
        base_palabras = new ArrayList<>();
        String current_word;
        String ruta = "C:\\Users\\danny\\Documents\\SOPA\\SOPATOR\\src\\" + TEMA;
        int c = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(ruta + ".txt"))) {
            while((current_word = br.readLine()) != null) {
                base_palabras.add(c, current_word);
                System.out.println(current_word);
                c++;
            }
            cantidad_validas = c;
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
    
    private void insertar_palabras_validas(int num_palabras_validas) {
        int random_pos;
        for(int i=0; i<num_palabras_validas; i++) {
            random_pos = getRandomNumber(num_palabras_validas);
            palabras_validas.add(i, base_palabras.get(random_pos));
        }
        
    }
    
    private void generar() {
        rellenar();
        
        CircularLinkedList<Character> fila = new CircularLinkedList<>();
        int num_palabras = (getRandomNumber(2)+1)*(getRandomNumber(4)+1); 
        // +1 para evitar 0
        insertar_palabras_validas(num_palabras);

        tmp insercion;
        for(int i = 0; i<palabras_validas.size(); i++) {
            insercion = new tmp();
            insercion.setX(getRandomNumber(COLUMNAS));
            insercion.setY(getRandomNumber(FILAS));
            insercion.setRANDOM_DIRECTION(
                    directions.get(getRandomNumber(directions.size()))
            );
            insercion.setRANDOM_WORD(palabras_validas.get(i));
            if (validar_insercion(insercion)) {
                insertar_palabra(insercion);
            } else {
                i--;
            }
        }
        
        for(int i = 0; i<COLUMNAS; i++) {
            fila = sopa_letras.get(i);
            for(int j = 0; j<FILAS; j++) {
                if (fila.get(j) == '*') {
                    fila.set(j, getRandomChar());
                }
            }
            sopa_letras.put(i, fila);
            fila.clear();
        }
    }
    
    private void rellenar() {
        CircularLinkedList tmp = new CircularLinkedList();
        for(int i = 0; i<FILAS; i++) {
            for(int j = 0; j<COLUMNAS; j++) {
                tmp.add(j, '*');
            }
            System.out.println(tmp.toString());
            sopa_letras.put(i, tmp);
            tmp.clear();
        }
        
    }

    private boolean validar_insercion(tmp insert) {
        String palabra = insert.getRANDOM_WORD();
        int pos_x = insert.getX();
        int pos_y = insert.getY();
        Pair direction = insert.getRANDOM_DIRECTION();
        
        
        int len = ABC.length();
        int dirX = direction.getX();
        int dirY = direction.getY();
        CircularLinkedList<Character> tmp;
        if (
            (pos_x + len * dirX) > 0 &&
            (pos_x + len * dirX) < COLUMNAS &&
            (pos_y + len * dirY) > 0 &&
            (pos_y + len * dirY) < FILAS
        ) {
            for (int i = 0; i<len; i++) {
                tmp = sopa_letras.get(pos_y + i * dirY);
                return tmp.get(pos_x + i * dirX) == '*' ||
                       tmp.get(pos_y + i * dirY) == palabra.charAt(i);
               
           } 
        }
        return false;
    }
    
    private void insertar_palabra(tmp insert) {
        String palabra = insert.getRANDOM_WORD();
        int pos_x = insert.getX();
        int pos_y = insert.getY();
        Pair direction = insert.getRANDOM_DIRECTION();
        
        CircularLinkedList<Character> tmp;
        for(int i = 0; i<palabra.length(); i++) {
            tmp = sopa_letras.get(pos_y + i * direction.getY());
            tmp.add(pos_x + i * direction.getX(), palabra.charAt(i));
        }
    }
}