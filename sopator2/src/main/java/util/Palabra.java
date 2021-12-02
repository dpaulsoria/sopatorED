/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Objects;

/**
 *
 * @author danny
 */
public class Palabra {
    private final String word;
    private final int size;
    private boolean encontrada;
    public Palabra(String word, boolean encontrada) {
        this.word = word;
        this.size = word.length();
        this.encontrada = encontrada;
    }
    public Palabra(String word) {
        this.word = word;
        this.size = word.length();
        this.encontrada = false;
    }

    public String getWord() {
        return word;
    }

    public int getSize() {
        return size;
    }

    public boolean isEncontrada() {
        return encontrada;
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
    
}