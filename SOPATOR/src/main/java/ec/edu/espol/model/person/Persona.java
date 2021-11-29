/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model.person;
 
import ec.edu.espol.model.util.Extras;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
 

/**
 *
 * @author danny
 */
public class Persona implements Serializable {
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Organización;
    private String Usuario;
    private String Clave;
    public Persona(String[] datos){
        this.Nombre = datos[0];
        this.Apellido = datos[1];
        this.Email = datos[2];
        this.Organización = datos[3];
        this.Usuario = datos[4];
        this.Clave = Extras.claveHex(datos[5]);
    }

    public Persona(String u, String c){
        this.Usuario = u;
        this.Clave = c;
        this.Nombre = new String();
        this.Apellido = new String();
        this.Email = new String();
        this.Organización = new String();
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getOrganización() {
        return Organización;
    }

    public void setOrganización(String Organización) {
        this.Organización = Organización;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Extras.claveHex(Clave);
    }
    
    @Override
    public String toString() {
        return Nombre + "," + Apellido + "," + Email + "," + Organización + "," + Usuario + "," + Clave;
    }
    public static boolean validarExistenciaEmail(String tipo, String email) {
        Vendedor v; Comprador c;
            //ArrayList<Persona> lista = new ArrayList<>();
        if (Extras.deserializar(tipo) != null) {
            HashMap<String, Object> mapa = Extras.deserializar(tipo);
            switch (tipo.toUpperCase()) {
                case "VENDEDOR": 
                    for(Map.Entry<String, Object> entry:mapa.entrySet()) {
                        v = (Vendedor)entry.getValue();
                        if (email.equals(v.getEmail())) {
                            return true;
                        }
                    }
                    break;
                case "COMPRADOR":
                    for(Map.Entry<String, Object> entry:mapa.entrySet()) {
                        c = (Comprador)entry.getValue();
                        if (email.equals(c.getEmail())) {
                            return true;
                        }
                    }
                    break;
            }
            return false;
        } else {
            return false;
        }
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        return true;
    }
    public static boolean login(String correo, String clave, String tipo) {
        clave = Extras.claveHex(clave);
        boolean b = false; Persona p;
        if (Extras.deserializar(tipo) != null) {
            HashMap<String, Object> mapa = Extras.deserializar(tipo);
            for(Map.Entry<String, Object> entry:mapa.entrySet()) {
                p = (Persona)entry.getValue();
                if (correo.equals(p.getEmail()) && clave.equals(p.getClave())) {
                    b = true;
                    return b;
                }
            }
            return b;
        } else {
            return b;
        }
    }
}
