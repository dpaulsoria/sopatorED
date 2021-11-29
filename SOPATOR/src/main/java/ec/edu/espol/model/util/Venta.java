/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.util;
import ec.edu.espol.model.person.Comprador;
import ec.edu.espol.model.person.Vendedor;
import ec.edu.espol.model.vehiculo.Vehículo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danny
 */
public class Venta {
    private String EmailVendedor;
    private String EmailComprador;
    private Comprador Comprador;
    private int IDVenta;
    private Vendedor Vendedor;
    private ArrayList<Vehículo> ListaVehículos;
    public Venta(int idventa, Comprador comprador, Vendedor vendedor){
        this.EmailComprador = comprador.getEmail();
        this.EmailVendedor = vendedor.getEmail();
        this.Comprador = comprador;
        this.IDVenta = idventa;
        this.Vendedor = vendedor;
        this.ListaVehículos = new ArrayList<>();        
    }

    @Override
    public String toString() {
        return IDVenta + "," + EmailComprador + "," + "{" + Comprador.toString() + "}" + "," + EmailVendedor + ","  + "{" + Vendedor.toString() + "}" + "," + ListaVehículos;
    }

    public String getEmailVendedor() {
        return EmailVendedor;
    }

    public void setEmailVendedor(String EmailVendedor) {
        this.EmailVendedor = EmailVendedor;
    }

    public String getEmailComprador() {
        return EmailComprador;
    }

    public void setEmailComprador(String EmailComprador) {
        this.EmailComprador = EmailComprador;
    }

    public Comprador getComprador() {
        return Comprador;
    }

    public void setComprador(Comprador Comprador) {
        this.Comprador = Comprador;
    }

    public int getIDVenta() {
        return IDVenta;
    }

    public void setIDVenta(int IDVenta) {
        this.IDVenta = IDVenta;
    }

    public Vendedor getVendedor() {
        return Vendedor;
    }

    public void setVendedor(Vendedor Vendedor) {
        this.Vendedor = Vendedor;
    }

    public ArrayList<Vehículo> getListaVehículos() {
        return ListaVehículos;
    }

    public void setListaVehículos(ArrayList<Vehículo> ListaVehículos) {
        this.ListaVehículos = ListaVehículos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Venta other = (Venta) obj;
        if (this.IDVenta != other.IDVenta) {
            return false;
        }
        return true;
    }

    
    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Venta") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Venta"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Venta");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Venta venta = (Venta)entry.getValue();
                if (n<venta.getIDVenta()) {
                    n = venta.getIDVenta(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
    
}
