/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;
import util.Palabra;

/**
 *
 * @author danny
 */
public class Letra {
    private Character letra;
    private Palabra palabra;
    private int fila;
    private int col;
    public Letra(Character c, Palabra p) {
        this.letra = c; this.palabra = p;
    }
    public Letra(Character c) {
        this.letra = c; this.palabra = null;
    }

    public Letra(Character letra,int fila, int col) {
        this.letra = letra;
        this.palabra = palabra;
        this.fila = fila;
        this.col = col;
    }
    
    public Character getLetra() {
        return letra;
    }
    
    public int getFila() {
        return fila;
    }
    public int getCol() {
        return col;
    }
    
    public Palabra getPalabra() {
        return palabra;
    }
    
    
    public void setLetra(Character letra) {
        this.letra = letra;
    }
    public void setFila(int f) {
        this.fila = f;
    }
    public void setCol(int c) {
        this.col = c;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    public static boolean pertenecenApalabra(Letra l1, Letra l2) {
        return l1.getPalabra().equals(l2.getPalabra());
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
        final Letra other = (Letra) obj;
        if (!Objects.equals(this.letra, other.letra)) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return letra.toString();
    }
    
}
