package una.ac.cr.licenciasfacil.Clases;

import java.io.Serializable;

/**
 * Created by root on 07/10/17.
 */

public class Licencia implements Serializable {

    String id;//uuid
    String nombre="";
    String version="";
    String descripcion="";
    String tipo="";
    String software="";//el sotfware que la utiliza


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }
}
