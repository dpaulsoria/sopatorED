/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.vehiculo;

import ec.edu.espol.model.util.Venta;
import ec.edu.espol.model.util.Extras;
import ec.edu.espol.model.util.Oferta;
import ec.edu.espol.model.person.Vendedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 *
 * @author danny
 */
public class Vehículo implements Serializable {
    private String Placa;
    private String Marca;
    private String Modelo;
    private int Año;
    private int IDOferta;
    private String EmailVendedor;
    private double Recorrido;
    private String Color;
    private double Precio;
    private Venta venta;
    private ArrayList<Oferta> ListaOfertas;
    private Vendedor Vendedor;
    private String img;
    private String motor;
    public Vehículo(String[] datos, Vendedor v, String rutaImg) {
        this.Placa = datos[0];
        this.Marca = datos[1];
        this.Modelo = datos[2];
        this.Año = Integer.valueOf(datos[3]);
        this.Recorrido = Double.valueOf(datos[4]);
        this.Color = datos[5];
        this.Precio = Double.valueOf(datos[6]);
        this.motor = datos[7];
        this.EmailVendedor = v.getEmail();
        this.venta = null;
        this.ListaOfertas = new ArrayList<>();
        this.Vendedor = v;
        this.img = rutaImg;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public int getIDOferta() {
        return IDOferta;
    }

    public void setIDOferta(int IDOferta) {
        this.IDOferta = IDOferta;
    }

    public String getEmailVendedor() {
        return EmailVendedor;
    }

    public void setEmailVendedor(String EmailVendedor) {
        this.EmailVendedor = EmailVendedor;
    }

    public ArrayList<Oferta> getListaOfertas() {
        return ListaOfertas;
    }

    public void setListaOfertas(ArrayList<Oferta> ListaOfertas) {
        this.ListaOfertas = ListaOfertas;
    }

    public String getRutaImg() {
        return img;
    }

    public void setRutaImg(String img) {
        this.img = img;
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
        final Vehículo other = (Vehículo) obj;
        if (!Objects.equals(this.Placa, other.Placa)) {
            return false;
        }
        return true;
    }
/*
    public Image getImg() {
        return img;
    }

    public void setImg(Image bi) {
        this.img = bi;
    }
    */
    
    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public double getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(double Recorrido) {
        this.Recorrido = Recorrido;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Vendedor getVendedor() {
        return Vendedor;
    }

    public void setVendedor(Vendedor Vendedor) {
        this.Vendedor = Vendedor;
    }
    private static ArrayList<Vehículo> recorrerYBuscar(HashMap<String, Object> mapaVehículo, String[] parametros) {
        ArrayList<Vehículo> lista = new ArrayList<>();
        for (Map.Entry<String, Object> entry:mapaVehículo.entrySet()) {
            Vehículo v = (Vehículo)entry.getValue();
            if (!"".equals(parametros[0]) && !"".equals(parametros[1])) {
                if (Double.valueOf(parametros[0]) <= v.getRecorrido() && v.getRecorrido() <= Double.valueOf(parametros[1])) lista.add(v);
            }
            if (!"".equals(parametros[2]) && !"".equals(parametros[3])) {
                if (Integer.valueOf(parametros[2]) <= v.getAño() && v.getAño() <= Integer.valueOf(parametros[3])) lista.add(v);
            }
            if (!"".equals(parametros[4]) && !"".equals(parametros[5])) {
                if (Double.valueOf(parametros[4]) <= v.getPrecio()&& v.getPrecio()<= Double.valueOf(parametros[5])) lista.add(v);
            }
        }
        return lista;
    }
    public static ArrayList<Vehículo> buscarVehículo(String[] parametros, String tipoVeh) {
        if (Extras.deserializar(tipoVeh) != null) {
            HashMap<String, Object> mapaVehículo = Extras.deserializar(tipoVeh);
            return recorrerYBuscar(mapaVehículo, parametros);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Placa=" + Placa + ", Marca=" + Marca + ", Modelo=" + Modelo + ", Año=" + Año + ", IDOferta=" + IDOferta + ", EmailVendedor=" + EmailVendedor + ", Recorrido=" + Recorrido + ", Color=" + Color + ", Precio=" + Precio + ", venta=" + venta + ", ListaOfertas=" + ListaOfertas + ", Vendedor=" + Vendedor + ", img=" + img + ", motor=" + motor + " ";
    }
    
}