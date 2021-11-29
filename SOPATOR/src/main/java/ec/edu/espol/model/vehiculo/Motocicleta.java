/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.vehiculo;

import ec.edu.espol.model.person.Vendedor;
import ec.edu.espol.model.util.Extras;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author danny
 */
public class Motocicleta extends Vehículo{
    private int IDM;
    private String Tipo = "MOTOCICLETA";
    private String TipoCombustible;
    private int Vidrios;
    private String Transmisión;
    public Motocicleta(String[] datos, Vendedor ve, String rutaImagenMotocicleta, String[] datosMotocicleta) {
        super(datos, ve, rutaImagenMotocicleta);
        this.TipoCombustible = datosMotocicleta[0];
        this.Vidrios = 0;
        this.Transmisión = datosMotocicleta[1];
        this.IDM = Integer.valueOf(datosMotocicleta[2]);
    }

    public int getID() {
        return IDM;
    }

    public void setID(int IDM) {
        this.IDM = IDM;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipoCombustible() {
        return TipoCombustible;
    }

    public void setTipoCombustible(String TipoCombustible) {
        this.TipoCombustible = TipoCombustible;
    }

    public int getVidrios() {
        return Vidrios;
    }

    public void setVidrios(int Vidrios) {
        this.Vidrios = Vidrios;
    }

    public String getTransmisión() {
        return Transmisión;
    }

    public void setTransmisión(String Transmisión) {
        this.Transmisión = Transmisión;
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
        final Motocicleta other = (Motocicleta) obj;
        if (this.IDM != other.IDM) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() +", Tipo=" + Tipo + ", TipoCombustible=" + TipoCombustible + ", Vidrios=" + Vidrios + ", Transmisión="+ Transmisión
                +", IDM=" + IDM;
    }
    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Motocicleta") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Motocicleta"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Camión");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Motocicleta m = (Motocicleta)entry.getValue();
                if (n<m.getID()) {
                    n = m.getID(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
