package una.ac.cr.licenciasfacil.Clases;

/**
 * Created by cardo on 14/11/17.
 */

public class Otros {
    String id="";//uuid;
    String titulo="";
    String descripcion="";
    int tipo=0;//si es 1 es una pregunta si es 2 es otro concepto mas


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
