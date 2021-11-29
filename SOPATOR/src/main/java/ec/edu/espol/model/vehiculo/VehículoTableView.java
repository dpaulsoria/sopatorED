/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.vehiculo;

import java.util.Objects;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author danny
 */
public class VehículoTableView {
    private SimpleStringProperty Placa = new SimpleStringProperty();
    private SimpleStringProperty Marca = new SimpleStringProperty();
    private SimpleStringProperty Modelo = new SimpleStringProperty();
    private SimpleStringProperty EmailVendedor = new SimpleStringProperty();
    private SimpleDoubleProperty Precio = new SimpleDoubleProperty();
    private SimpleStringProperty motor = new SimpleStringProperty();
    private SimpleIntegerProperty año = new SimpleIntegerProperty();

    public String getPlaca() {
        return Placa.get();
    }

    public String getEmailVendedor() {
        return EmailVendedor.get();
    }

    public void setEmailVendedor(SimpleStringProperty EmailVendedor) {
        this.EmailVendedor = EmailVendedor;
    }

    public void setPlaca(SimpleStringProperty Placa) {
        this.Placa = Placa;
    }

    public String getMarca() {
        return Marca.get();
    }

    public void setMarca(SimpleStringProperty Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo.get();
    }
    public void setModelo(SimpleStringProperty Modelo) {
        this.Modelo = Modelo;
    }
    public double getPrecio() {
        return Precio.get();
    }

    public void setPrecio(SimpleDoubleProperty Precio) {
        this.Precio = Precio;
    }

    public String getMotor() {
        return motor.get();
    }

    public void setMotor(SimpleStringProperty motor) {
        this.motor = motor;
    }

    public int getAño() {
        return año.get();
    }

    public void setAño(SimpleIntegerProperty año) {
        this.año = año;
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
        final VehículoTableView other = (VehículoTableView) obj;
        if (!Objects.equals(this.Placa, other.Placa)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Placa=" + Placa + ", Marca=" + Marca + ", Modelo=" + Modelo + ", IDOferta=" + ", Precio=" + Precio + ", motor=" + motor + " ";
    }
    
}