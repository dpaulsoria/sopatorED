/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.util;

import ec.edu.espol.model.person.Comprador;
import ec.edu.espol.model.person.Vendedor;
import ec.edu.espol.model.vehiculo.Vehículo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danny
 */
public class Oferta {
    private int IDOferta;
    private int IDVenta;
    private String EmailVendedor;
    private String EmailComprador;
    private Comprador Comprador;
    private double PrecioOfertado;
    private boolean Estado = false;
    private Venta Venta;
    private String Placa;
    private Vehículo V;
    private Vendedor Vendedor;
    
    public Oferta(int idof, Comprador comprador, double pof, Vendedor vendedor, Vehículo veh){
        this.IDOferta = idof;
        this.EmailVendedor = vendedor.getEmail();
        this.EmailComprador = comprador.getEmail();
        this.Comprador = comprador;
        this.PrecioOfertado = pof;
        this.Estado = false;
        this.Venta = null;
        this.Placa = veh.getPlaca();
        this.V = veh;
        this.Vendedor = vendedor;
    }
//    public boolean sendMailOferta(String EmailComprador, String NombreVendedor, String ApellidoVendedor) {
//        JavaMailUtil jmu = new JavaMailUtil(
//                EmailComprador,
//                "Oferta Aceptada",
//                "Me complace comunicarle que la oferta por su vehículo ha"
//                + " sido aceptada por: " + NombreVendedor + ", "  + ApellidoVendedor);
//        return jmu.sendMail();
//    }

    public int getIDOferta() {
        return IDOferta;
    }

    public void setIDOferta(int IDOferta) {
        this.IDOferta = IDOferta;
    }

    public int getIDVenta() {
        return IDVenta;
    }

    public void setIDVenta(int IDVenta) {
        this.IDVenta = IDVenta;
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

    public double getPrecioOfertado() {
        return PrecioOfertado;
    }

    public void setPrecioOfertado(double PrecioOfertado) {
        this.PrecioOfertado = PrecioOfertado;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Venta getVenta() {
        return Venta;
    }

    public void setVenta(Venta Venta) {
        this.Venta = Venta;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public Vehículo getVehículo() {
        return V;
    }

    public void setV(Vehículo V) {
        this.V = V;
    }

    public Vendedor getVendedor() {
        return Vendedor;
    }

    public void setVendedor(Vendedor Vendedor) {
        this.Vendedor = Vendedor;
    }
    
    
    
    @Override
    public String toString() {
        return IDOferta + "," + EmailComprador + "," + "{" + Comprador + "}" + "," + PrecioOfertado + "," + Estado + "," +
                EmailVendedor + "," + "{" + Vendedor + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.IDOferta;
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
        final Oferta other = (Oferta) obj;
        if (this.IDOferta != other.IDOferta) {
            return false;
        }
        return true;
    }

    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Oferta") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Oferta"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Oferta");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Oferta of = (Oferta)entry.getValue();
                if (n<of.getIDOferta()) {
                    n = of.getIDOferta(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
    
}