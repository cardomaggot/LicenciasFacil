package una.ac.cr.licenciasfacil.Clases;

/**
 * Created by root on 07/10/17.
 */

public class Licencia {

    //Textos
    String codigo="";
    String nombre="";
    String descripcion="";//La descripcion de la licencia
    String compatibles="";//para indicar con cual licencia de la FSF es compatible

    //Filtros
    boolean isCopyleft;
    boolean isLibre;
    boolean isSemiLibre;
    boolean isAbierta;
    boolean isCompatible;//para saber si es compatible con las de la FSF
}
