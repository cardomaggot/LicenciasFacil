package una.ac.cr.licenciasfacil.Clases;

import java.io.Serializable;

/**
 * Created by cardo on 08/11/17.
 */

public class Usuario implements Serializable{

    String id;//uuid
    String email="";
    String contrasena="";
    int tipo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
