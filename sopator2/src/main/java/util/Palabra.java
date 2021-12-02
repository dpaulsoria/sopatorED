/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import collection.CircularLinkedList;
import java.util.Objects;

/**
 *
 * @author danny
 */
public class Palabra {
    private final String word;
    private final int size;
    private boolean encontrada;
    private CircularLinkedList<Letra> listaLetras = new CircularLinkedList();
    
    public Palabra(String word, boolean encontrada) {
        this.word = word;
        this.size = word.length();
        this.encontrada = encontrada;
        fillListaLetras(word);
    }
    public Palabra(String word) {
        this.word = word;
        this.size = word.length();
        this.encontrada = false;
        fillListaLetras(word);
    }

    public String getWord() {
        return word;
    }

    public int getSize() {
        return size;
    }
    
    private void fillListaLetras(String w) {
        for(int i = 0; i<w.length(); i++) {
            Letra l = new Letra(w.charAt(i));
            listaLetras.addLast(l);
        }
    }
    
    public CircularLinkedList<Letra> getListaLetras() {
        return listaLetras;
    }
    public boolean isEncontrada() {
        return encontrada;
    }
    
    public Letra getLetra(int index) {
        return listaLetras.get(index);        
    }

    public Character charAt(int index) {
        return word.charAt(index);
    }
    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Palabra other = (Palabra) obj;
        if (this.size != other.size) {
            return false;
        }
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }
    public Character getFirstChar() {
        return word.charAt(0);
    }
    
    public static Palabra formarPalabra(CircularLinkedList<Letra> palabra) {
        if (palabra.isEmpty()) {
            return null;
        }
        String tmp = "";
        for(Letra c:palabra) {
            tmp += c;
        }
        return new Palabra(tmp);
    }
    
}
