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
public class Auto extends Vehículo{
    private int IDA;
    private String Tipo = "AUTO";
    private String TipoCombustible;
    private int Vidrios;
    private String Transmisión;
    public Auto(String[] datos, Vendedor ve, String rutaImagenAuto, String[] datosAuto) {
        
        super(datos, ve, rutaImagenAuto);
        this.TipoCombustible = datosAuto[0];
        this.Vidrios = Integer.valueOf(datosAuto[1]);
        this.Transmisión = datosAuto[2];
        this.IDA = Integer.valueOf(datosAuto[3]);
    }

    public int getID() {
        return IDA;
    }

    public void setID(int ID) {
        this.IDA = ID;
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
        final Auto other = (Auto) obj;
        if (this.IDA != other.IDA) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Tipo=" + Tipo + ", TipoCombustible =" + TipoCombustible + ", Vidrios=" + Vidrios + ", Transmisión=" + Transmisión + ", IDA=" +
                IDA;
    }
    public static int nextCode() {
        int n=-1; System.out.println("NextCode");
        if (Extras.deserializar("Auto") != null) {
            HashMap<String, Object> mapa = Extras.deserializar("Auto"); System.out.println("Extrayendo Mapa");
            System.out.println("For EntrySet Auto");
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {            
                Auto a = (Auto)entry.getValue();
                if (n<a.getID()) {
                    n = a.getID(); System.out.println("Contador = " + n);
                }
            }
            return n+1;
        } else {
            return 1;
        }
    }
}
