/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.generator;

import espol.collection.CircularLinkedList;
import espol.collection.CircularLinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author danny
 */
public class soup {
    
    private final int filas;
    private final int columnas;
    private HashMap<Integer, CircularLinkedList> filas_palabras = new HashMap<>();;
    private Set<String> palabras_validas = new HashSet<>();
    
    public soup(int filas, int columnas, Set<String> palabras_validas) {
        this.palabras_validas = palabras_validas;
        this.filas = filas;
        this.columnas = columnas;
    }
    
}
