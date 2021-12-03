/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author danny
 */
public class Desplazamiento {
    Pair p;
    int filacol;
    public Desplazamiento(Pair pair, int f) {
        this.p = pair;
        this.filacol = f;
    }
    public int getFilaCol() {
        return filacol;
    }
    @Override
    public String toString() {
        if (p.X == 0 && p.Y == 1) {
            return "Arriba";
        } else if (p.X == 1 && p.Y == 0) {
            return "Derecha";
        } else if (p.X == -1 && p.Y == 0) {
            return "Izquierda";
        } else if (p.X == 0 && p.Y == -1) {
            return "Abajo";
        } else {
            return "";
        }
    }
}
