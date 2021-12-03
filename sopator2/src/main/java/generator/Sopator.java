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
import util.Letra;
import util.Palabra;

/**
 *
 * @author danny
 */
public class Sopator {
    private String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int FILAS;
    private int COLUMNAS;
    private final String TEMA;
    private Map<Character, ArrayList<String>> base_palabras;
    private ArrayList<String> palabras_validas;
    private ArrayList<CircularLinkedList<Letra>> sopa_letras = new ArrayList<>();
    
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
    
    public Map<Character, ArrayList<String>> getBase_Palabras() {
        return base_palabras;
    }
    
    public ArrayList<String> getPalabras_Validas() {
        return palabras_validas;
    }
    
    public ArrayList<CircularLinkedList<Letra>> getSopa_Letras() {
        return sopa_letras;
    }
    
    public void reemplazarLetra(int y, int x, Character letra) {
        CircularLinkedList<Letra> fila = sopa_letras.get(y);
        fila.set(x, new Letra(letra));
    }
    
    public void reorganizar() {
        for(int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Letra> fila = sopa_letras.get(i);
            for(int j = 0; j<fila.size(); j++) {
                Letra l = fila.get(j);
                if (!l.noUnselect)
                    fila.set(j, new Letra(getRandomChar()));
            }
        }
    }
    
    public boolean confirmarPalabraEnBase(Palabra p) {
        String word = p.getWord();
        ArrayList<String> palabras;
        Character primeraLetra = p.getFirstChar();
        if (base_palabras.containsKey(primeraLetra)) {
            palabras = base_palabras.get(primeraLetra);
            for(String s:palabras) {
                if (word.equals(s))
                    return true;
            }
        }
        return false;
    }
    
    private void añadir_base_validas() {
        base_palabras = new HashMap<>();
        String current_word;
        String ruta = "src\\" + TEMA;
        Character key;

        try(BufferedReader br = new BufferedReader(new FileReader(ruta + ".txt"))) {
            while((current_word = br.readLine()) != null) {
                key = current_word.charAt(0);
                if (base_palabras.containsKey(current_word.charAt(0))) {
                    ArrayList<String> palabras = base_palabras.get(key);
                    palabras.addLast(current_word);
                } else {
                     ArrayList<String> palabras = new ArrayList<>();
                     palabras.addLast(current_word);
                     base_palabras.put(key, palabras);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        System.out.println(base_palabras.toString());
    }
    
    public Letra getLetra(int fila, int columna) {
        return sopa_letras.get(fila).get(columna);
    }
    
    public CircularLinkedList<Letra> getFila(int fila) {
        return sopa_letras.get(fila);
    }

    public void desplazarFilaDer(int fila) {
        int i = 1;
        for(CircularLinkedList<Letra> c:sopa_letras) {
            if (i == fila) {
                c.desplazarDer();
                break;
            }
            i++;
        }
    }
    
    public void desplazarFilaIzq(int fila) {
        int i = 1;
        for(CircularLinkedList<Letra> c:sopa_letras) {
            if (i == fila) {
                c.desplazarIzq();
                break;
            }
            i++;
        }
    }
    
    private ArrayList<Letra> generarTmp(int c) {
        ArrayList<Letra> tmp = new ArrayList<>();
        for (int i=0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Letra> fila = sopa_letras.get(i);
            for (int j = 0; j<fila.size(); j++) {
                tmp.addLast(fila.get(j));
            }
        }
        return tmp;
    }
        
    public void desplazarColUp(int c) {
        ArrayList<Letra> tmp = generarTmp(c);
        Letra first = tmp.removeFirst();
        tmp.addLast(first);
        setDesplazamiento(tmp);
    }
    
    public void desplazarColDown(int c) {
        ArrayList<Letra> tmp = generarTmp(c);
        Letra last = tmp.removeLast();
        tmp.addFirst(last);
        setDesplazamiento(tmp);
    }
    
    private void setDesplazamiento(ArrayList<Letra> tmp) {
        int k = 0;
        for (int i=0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Letra> fila = sopa_letras.get(i);
            for (int j = 0; j<fila.size(); j++) {
                fila.set(j,tmp.get(k)); k++;
            }
        }
    }
    
    public void añadirFila() {
        FILAS++;
        CircularLinkedList<Letra> filaNueva = new CircularLinkedList<>();
        for (int i = 0; i<COLUMNAS; i++) {
            filaNueva.addLast(new Letra(getRandomChar()));
        }
        sopa_letras.addLast(filaNueva);
    }
    
    public void añadirColumna() {
        COLUMNAS++;        
        for (int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Letra> tmp = sopa_letras.get(i);
            tmp.addLast(new Letra(getRandomChar()));
        }
    }
    public void añadirFila(int fila) {
        FILAS++;
        CircularLinkedList<Letra> filaNueva = new CircularLinkedList<>();
        for (int i = 0; i<COLUMNAS; i++) {
            filaNueva.addLast(new Letra(getRandomChar()));
        }
        sopa_letras.add(fila, filaNueva);
    }
    
    public void añadirColumna(int col) {
        COLUMNAS++;        
        for (int i = 0; i<sopa_letras.size(); i++) {
            CircularLinkedList<Letra> tmp = sopa_letras.get(i);
            tmp.add(col, new Letra(getRandomChar()));
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
            CircularLinkedList<Letra> tmp = sopa_letras.get(i);
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

    private void generar() {
        for(int i = 0; i<FILAS; i++) {
            CircularLinkedList<Letra> tmp = new CircularLinkedList();
            for(int j = 0; j<COLUMNAS; j++) {
                tmp.addLast(new Letra(getRandomChar()));
            }
            sopa_letras.addLast(tmp);
        }
    }
}