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
    private Pair ubi;
    private boolean selected;
    public boolean noUnselect;
    public Letra(Character c, Palabra p) {
        this.letra = c; this.palabra = p;
        this.selected = false;
        this.noUnselect = false;
    }
    public Letra(Character c) {
        this.letra = c; this.palabra = null;
        selected = false;
        this.noUnselect = false;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean s) {
        this.selected = s;
    }
    
    public Character getLetra() {
        return letra;
    }
    
    public Pair getUbi() {
        return ubi;
    }
    
    public Palabra getPalabra() {
        return palabra;
    }
    
    
    public void setLetra(Character letra) {
        this.letra = letra;
    }
    public void setUbi(Pair p) {
        ubi = p;
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
        final Letra o = (Letra) obj;
        if (!Objects.equals(this.letra, o.letra)) {
            return false;
        }
        if (ubi.X == o.getUbi().X && ubi.Y == o.getUbi().X) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return letra.toString();
    }
    
    public boolean validarSelectLetra(Letra n) {
        Pair n1 = n.getUbi();
        if ((n1.X == ubi.X + 1 && n1.Y == ubi.Y    ) ||
            (n1.X == ubi.X     && n1.Y == ubi.Y + 1) ||
            (n1.X == ubi.X - 1 && n1.Y == ubi.Y    ) ||
            (n1.X == ubi.X     && n1.Y == ubi.Y - 1) ||
            (n1.X == ubi.X + 1 && n1.Y == ubi.Y + 1) ||
            (n1.X == ubi.X - 1 && n1.Y == ubi.Y - 1) ||
            (n1.X == ubi.X + 1 && n1.Y == ubi.Y - 1) ||
            (n1.X == ubi.X - 1 && n1.Y == ubi.Y + 1)) {
            return true;
        }
        return false;
    }
    
}
