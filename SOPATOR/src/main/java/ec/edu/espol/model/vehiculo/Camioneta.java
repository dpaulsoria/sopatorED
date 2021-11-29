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
public class Camioneta extends Vehículo{
    private int IDCT;
    private String Tipo = "CAMIONETA";
    private String TipoCombustible;
    private int Vidrios;
    private String Transmisión;
    public Camioneta(String[] datos, Vendedor ve, String rutaImagenCamioneta, String[] datosCamioneta) {
        super(datos, ve, rutaImagenCamioneta);
        this.TipoCombustible = datosCamioneta[0];
        this.Vidrios = Integer.valueOf(datosCamioneta[1]);
        this.Transmisión = datosCamioneta[2];
        this.IDCT = Integer.valueOf(datosCamioneta[3]);
    }

    public int getID() {
        return IDCT;
    }

    public void setID(int IDCT) {
        this.IDCT = IDCT;
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
        final Camioneta other = (Camioneta) obj;
        if (this.IDCT != other.IDCT) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return super.toString() + ", TipodeVehículo=" + Tipo + ", TipoCombustible=" + TipoCombustible + ", Vidrios=" + Vidrios + ", Transmisión=" + Transmisión + ", IDCT=" +
                IDCT;
    }
    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Camioneta") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Camioneta"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Auto");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Camioneta ca = (Camioneta)entry.getValue();
                if (n<ca.getID()) {
                    n = ca.getID(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
