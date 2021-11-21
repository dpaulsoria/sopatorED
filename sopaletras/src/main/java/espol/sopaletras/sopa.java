/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.sopaletras;

import java.util.HashMap;

/**
 *
 * @author danny
 */
public class sopa {
    final int filas;
    final int columnas;
    HashMap<Integer, CircularLinkedList> sopa = new HashMap<>();;
    
    public sopa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }
    
    public void setFilas(int fila, CircularLinkedList list) {
        if (fila > -1 && list != null) {
            sopa.put(fila, list);
        }
    }
    
    
}
