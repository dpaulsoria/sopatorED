/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.person;
import ec.edu.espol.model.util.Extras;
import ec.edu.espol.model.util.Oferta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danny
 */
 //Codigo editato por Luis Cavero
public class Comprador extends Persona {
    protected int iDComprador;
    protected transient ArrayList<Oferta> listaOfertas;
    public Comprador(String[] datos, int idC){        
        super(datos);
        this.iDComprador = idC;
        this.listaOfertas = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Comprador other = (Comprador) obj;
        if (this.iDComprador != other.iDComprador) {
            return false;
        }
        return true;
    }

    public int getID() {
        return iDComprador;
    }

    public void setID(int iDComprador) {
        this.iDComprador = iDComprador;
    }

    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(ArrayList<Oferta> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
    @Override
    public String toString() {
        return super.toString() + "," +this.getID();
    }
    
    public static int nextCode() {
        int n=-1;
        if (Extras.deserializar("Comprador") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Comprador");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Comprador c = (Comprador)entry.getValue();
                if (n<c.getID()) {
                    n = c.getID();
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
