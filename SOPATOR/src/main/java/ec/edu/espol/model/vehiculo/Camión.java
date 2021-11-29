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
public class Camión extends Vehículo{
    private int IDCA;
    private String Tipo = "CAMIÓN";
    private String TipoCombustible;
    private int Vidrios;
    private String Transmisión;
    public Camión(String[] datos, Vendedor ve, String rutaImagenCamión, String[] datosCamión) {
        super(datos, ve, rutaImagenCamión);
        this.TipoCombustible = datosCamión[0];
        this.Vidrios = Integer.valueOf(datosCamión[1]);
        this.Transmisión = datosCamión[2];
        this.IDCA = Integer.valueOf(datosCamión[3]);
    }

    public int getID() {
        return IDCA;
    }

    public void setID(int IDCA) {
        this.IDCA = IDCA;
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
        int hash = 5;
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
        final Camión other = (Camión) obj;
        if (this.IDCA != other.IDCA) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return super.toString() + ", Tipo" + Tipo + ", TipoCombustible=" + TipoCombustible + ", Vidrios=" + Vidrios + ", Transmisión=" + Transmisión + ", IDCA="
                + IDCA;
    }
    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Camión") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Camión"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Camión");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Camión c = (Camión)entry.getValue();
                if (n<c.getID()) {
                    n = c.getID(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
