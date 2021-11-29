/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.person;
import ec.edu.espol.model.util.Extras;
import ec.edu.espol.model.vehiculo.Vehículo;
import ec.edu.espol.model.util.Venta;
import ec.edu.espol.model.util.Oferta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author danny
 */
public class Vendedor extends Persona {
    protected int ID_Vendedor, ID_Oferta;
    protected ArrayList<Venta> Lista_Venta;
    protected ArrayList<Vehículo> Lista_Vehículos;
    protected Oferta of;
    public Vendedor(String[] datos, int id_v){
        super(datos);
        this.ID_Vendedor = id_v;
        this.Lista_Venta = new ArrayList<>();
        this.Lista_Vehículos = new ArrayList<>();
        this.of = null;
    }
    public Vendedor(String[] datos, int id_v, int id_of){
        super(datos);
        this.ID_Vendedor = id_v; this.ID_Oferta = id_of;
        this.Lista_Venta = new ArrayList<>();
        this.Lista_Vehículos = new ArrayList<>();
        this.of = null;
    }
    public int getID() {
        return ID_Vendedor;
    }

    public void setID(int ID_Vendedor) {
        this.ID_Vendedor = ID_Vendedor;
    }

    public int getID_Oferta() {
        return ID_Oferta;
    }

    public void setID_Oferta(int ID_Oferta) {
        this.ID_Oferta = ID_Oferta;
    }

    public ArrayList<Venta> getLista_Venta() {
        return Lista_Venta;
    }

    public void setLista_Venta(ArrayList<Venta> Lista_Venta) {
        this.Lista_Venta = Lista_Venta;
    }

    public ArrayList<Vehículo> getLista_Vehículos() {
        return Lista_Vehículos;
    }

    public void setLista_Vehículos(ArrayList<Vehículo> Lista_Vehículos) {
        this.Lista_Vehículos = Lista_Vehículos;
    }

    public Oferta getOf() {
        return of;
    }

    public void setOf(Oferta of) {
        this.of = of;
    }

    @Override
    public String toString() {
        return super.toString() + "," + this.getID();
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
        final Vendedor other = (Vendedor) obj;
        if (this.ID_Vendedor != other.ID_Vendedor) {
            return false;
        }
        return true;
    }
    public static int nextCode() {
        int n=-1;
        if (Extras.deserializar("Vendedor") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Vendedor");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Vendedor v = (Vendedor)entry.getValue();
                if (n<v.getID()) {
                    n = v.getID();
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
