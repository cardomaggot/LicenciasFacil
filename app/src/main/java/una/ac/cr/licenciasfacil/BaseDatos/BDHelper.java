package una.ac.cr.licenciasfacil.BaseDatos;


/**
 * Created by root on 07/10/17.
 */

public class BDHelper {

    //TODO--------------------------------------- CREATES ----------------------------------------------

    public static final String SQL_CREATE_COMENTARIOS = "CREATE TABLE " +
            BDContract.Comentario.TABLE_NAME + " ( " +
            BDContract.Comentario.ID+ " TEXT PRIMARY KEY , " +
            BDContract.Comentario.COMENTARIO + " TEXT, "+
            BDContract.Comentario.USUARIO + " TEXT, "+ //Verificar el ID que coincida con la PK de la tabla USUARIOS
            BDContract.Comentario.LICENCIA + " INTEGER )+" + //Verificar el ID que coincida con la PK de la tabla LICENCIAS
            BDContract.Comentario.FECHA + " DATETIME " +
            "FOREIGN KEY(licencia) REFERENCES licencias(id)"+
            "FOREIGN KEY(usuario) REFERENCES usuarios(id)"+
            ")";

    public static final String SQL_CREATE_LICENCIAS = "CREATE TABLE " +
            BDContract.Licencia.TABLE_NAME + " ( " +
            BDContract.Licencia.ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +//Verificar el ID
            BDContract.Licencia.NOMBRE + " TEXT, "+
            BDContract.Licencia.DESCRIPCION + " TEXT, "+
            BDContract.Licencia.TIPO + " TEXT )";

    public static final String SQL_CREATE_LICENCIASAPROBAR = "CREATE TABLE " +
            BDContract.LicenciaAprobacion.TABLE_NAME + " ( " +
            BDContract.LicenciaAprobacion.ID+ " TEXT PRIMARY KEY, " +//Verificar el ID
            BDContract.LicenciaAprobacion.USUARIO+ " TEXT, "+ //Verificar el ID que coincida con la PK de la tabla USUARIOS
            BDContract.LicenciaAprobacion.NOMBRE + " TEXT, "+
            BDContract.LicenciaAprobacion.DESCRIPCION + " TEXT, "+
            BDContract.LicenciaAprobacion.TIPO + " TEXT +" +
            "FOREIGN KEY(usuario) REFERENCES usuarios(id)"+
            ")";

    public static final String SQL_CREATE_LIKES = "CREATE TABLE " +
            BDContract.Likes.TABLE_NAME + " ( " +
            BDContract.Likes.ID+ " TEXT PRIMARY KEY, " +
            BDContract.Likes.LICENCIA+ " INTEGER )";//Verificar el ID que coincida con la PK de la tabla LICENCIAS

    public static final String SQL_CREATE_USUARIOS = "CREATE TABLE " +
            BDContract.Usuario.TABLE_NAME + " ( " +
            BDContract.Usuario.ID+ " TEXT PRIMARY KEY, " +
            BDContract.Usuario.CONTRASEÑA+ " TEXT )";




}
