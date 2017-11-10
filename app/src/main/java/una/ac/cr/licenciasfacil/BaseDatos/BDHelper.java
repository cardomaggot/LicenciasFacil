package una.ac.cr.licenciasfacil.BaseDatos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by root on 07/10/17.
 */

public class BDHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME="Licencias.db"; //Le pone el nombre de la base de datos, es un archivo físico
    //Si ve que no existe va al onCreate y la crea

    public BDHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //Le pasa el nombre de la base datos y la versión
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        /* Para hacer efectiva las referencias de integridad de las llaves foráneas en SQLite
           es necesario usar el método setForeignKeyConstraintsEnabled() pasando el valor true dentro de onOpen().
           Este método va desde Android Jelly Bean en adelante.
        */
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                //Para el soporte, se puede habilitar manualmente a través de la sentencia PRAGMA foreign_keys=ON
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_LICENCIAS);
            db.execSQL(SQL_CREATE_USUARIOS);
            //db.execSQL(SQL_CREATE_LIKES);
            //db.execSQL(SQL_CREATE_COMENTARIOS);
            //db.execSQL(SQL_CREATE_LICENCIASAPROBAR);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override //Cuando modifico el esquema de base de datos, estos tienen un número de versión, por ejemplo si agrego un tercer
    //campo en la base de datos, donde se borra la anterior y se crea la nueva tabla
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Licencia.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Usuario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Likes.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Comentario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.LicenciaAprobacion.TABLE_NAME);

        onCreate(db); //Se vuelve a crear llamando al onCreate que llama al
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Licencia.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Usuario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Likes.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.Comentario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BDContract.LicenciaAprobacion.TABLE_NAME);

        onCreate(db);
    }


    //TODO--------------------------------------- CREATES ----------------------------------------------

    public static final String SQL_CREATE_COMENTARIOS = "CREATE TABLE " +
            BDContract.Comentario.TABLE_NAME + " ( " +
            BDContract.Comentario.ID+ " TEXT PRIMARY KEY , " +
            BDContract.Comentario.COMENTARIO + " TEXT, "+
            BDContract.Comentario.USUARIO + " TEXT, "+ //Verificar el ID que coincida con la PK de la tabla USUARIOS
            BDContract.Comentario.LICENCIA + " TEXT, " + //Verificar el ID que coincida con la PK de la tabla LICENCIAS
            BDContract.Comentario.FECHA + " DATETIME, " +
            "FOREIGN KEY(licencia) REFERENCES licencias(id), "+
            "FOREIGN KEY(usuario) REFERENCES usuarios(id)"+
            ")";

    public static final String SQL_CREATE_LICENCIAS = "CREATE TABLE " +
            BDContract.Licencia.TABLE_NAME + " ( " +
            BDContract.Licencia.ID+ " TEXT PRIMARY KEY, " +//Verificar el ID
            BDContract.Licencia.NOMBRE + " TEXT, "+
            BDContract.Licencia.VERSION + " TEXT, "+
            BDContract.Licencia.TIPO + " TEXT,"+
            BDContract.Licencia.DESCRIPCION + " TEXT," +
            BDContract.Licencia.SOFTWARE + " TEXT "+
            ")";

    public static final String SQL_CREATE_LICENCIASAPROBAR = "CREATE TABLE " +
            BDContract.LicenciaAprobacion.TABLE_NAME + " ( " +
            BDContract.LicenciaAprobacion.ID+ " TEXT PRIMARY KEY, " +//Verificar el ID
            BDContract.LicenciaAprobacion.USUARIO+ " TEXT, "+ //Verificar el ID que coincida con la PK de la tabla USUARIOS
            BDContract.LicenciaAprobacion.NOMBRE + " TEXT, "+
            BDContract.LicenciaAprobacion.DESCRIPCION + " TEXT, "+
            BDContract.LicenciaAprobacion.TIPO + " TEXT, " +
            "FOREIGN KEY(usuario) REFERENCES usuarios(id)"+
            ")";

    public static final String SQL_CREATE_LIKES = "CREATE TABLE " +
            BDContract.Likes.TABLE_NAME + " ( " +
            BDContract.Likes.ID+ " TEXT PRIMARY KEY, " +
            BDContract.Likes.LICENCIA+ " TEXT )";//Verificar el ID que coincida con la PK de la tabla LICENCIAS

    public static final String SQL_CREATE_USUARIOS = "CREATE TABLE " +
            BDContract.Usuario.TABLE_NAME + " ( " +
            BDContract.Usuario.ID + " TEXT PRIMARY KEY, " +
            BDContract.Usuario.EMAIL + " TEXT, " +
            BDContract.Usuario.CONTRASENA + " TEXT, "+
            BDContract.Usuario.TIPO + " INTEGER "+
            ")";




}
