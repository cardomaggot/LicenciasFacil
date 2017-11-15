package una.ac.cr.licenciasfacil.BaseDatos;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 07/10/17.
 */

public class BDHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=7;
    public static final String DATABASE_NAME="Licencias.db"; //Le pone el nombre de la base de datos, es un archivo físico
    //Si ve que no existe va al onCreate y la crea
    private final Context myContext;
    public SQLiteDatabase db;
    private static String DB_PATH = "/data/data/una.ac.cr.licenciasfacil/databases/";


    public BDHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //Le pasa el nombre de la base datos y la versión
        this.myContext = context;
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
            db.execSQL(SQL_CREATE_LIKES);
            db.execSQL(SQL_CREATE_COMENTARIOS);
            db.execSQL(SQL_CREATE_LICENCIASAPROBAR);
            db.execSQL(SQL_CREATE_OTROS);
        }catch (Exception e){
            e.printStackTrace();
        }
        /*try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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




    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        /*if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
*/
        try {

            copyDataBase();

        } catch (IOException e) {

            throw new Error("Error copying database");

        }
        //      }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }




    //TODO--------------------------------------- CREATES ----------------------------------------------

    public static final String SQL_CREATE_COMENTARIOS = "CREATE TABLE " +
            BDContract.Comentario.TABLE_NAME + " ( " +
            BDContract.Comentario.ID+ " TEXT PRIMARY KEY , " +
            BDContract.Comentario.COMENTARIO + " TEXT, "+
            BDContract.Comentario.USUARIO + " TEXT, "+ //Verificar el ID que coincida con la PK de la tabla USUARIOS
            BDContract.Comentario.LICENCIA + " TEXT, " + //Verificar el ID que coincida con la PK de la tabla LICENCIAS
            BDContract.Comentario.FECHA + " TEXT, " +
            "FOREIGN KEY (licencia) REFERENCES licencias (id), "+
            "FOREIGN KEY (usuario) REFERENCES usuarios (id)"+
            ")";

    public static final String SQL_CREATE_LICENCIAS = "CREATE TABLE " +
            BDContract.Licencia.TABLE_NAME + " ( " +
            BDContract.Licencia.ID+ " TEXT PRIMARY KEY, " +//Verificar el ID
            BDContract.Licencia.NOMBRE + " TEXT, "+
            BDContract.Licencia.VERSION + " TEXT, "+
            BDContract.Licencia.TIPO + " TEXT,"+
            BDContract.Licencia.DESCRIPCION + " TEXT," +
            BDContract.Licencia.IMAGEN + " BLOB, "+
            BDContract.Licencia.SOFTWARE + " TEXT "+
            ")";

    public static final String SQL_CREATE_LICENCIASAPROBAR = "CREATE TABLE " +
            BDContract.LicenciaAprobacion.TABLE_NAME + " ( " +
            BDContract.LicenciaAprobacion.ID+ " TEXT PRIMARY KEY, " +//Verificar el ID
            BDContract.LicenciaAprobacion.NOMBRE + " TEXT, "+
            BDContract.LicenciaAprobacion.VERSION + " TEXT, "+
            BDContract.LicenciaAprobacion.TIPO + " TEXT, " +
            BDContract.LicenciaAprobacion.DESCRIPCION + " TEXT, "+
            BDContract.LicenciaAprobacion.SOFTWARE + " TEXT, "+
            //BDContract.LicenciaAprobacion.IMAGEN + " TEXT, "+
            BDContract.LicenciaAprobacion.USUARIO+ " TEXT, "+//Verificar el ID que coincida con la PK de la tabla USUARIOS
            "FOREIGN KEY (usuario) REFERENCES usuarios (id)"+
            ")";

    public static final String SQL_CREATE_LIKES = "CREATE TABLE " +
            BDContract.Likes.TABLE_NAME + " ( " +
            BDContract.Likes.ID+ " TEXT PRIMARY KEY, " +
            BDContract.Likes.LICENCIA+ " TEXT, "+//Verificar el ID que coincida con la PK de la tabla LICENCIAS
            BDContract.Likes.USUARIO+ " TEXT, "+
            "FOREIGN KEY (licencia) REFERENCES licencias (id), "+
            "FOREIGN KEY (usuario) REFERENCES usuarios (id)"+
            ")";

    public static final String SQL_CREATE_USUARIOS = "CREATE TABLE " +
            BDContract.Usuario.TABLE_NAME + " ( " +
            BDContract.Usuario.ID + " TEXT PRIMARY KEY, " +
            BDContract.Usuario.EMAIL + " TEXT, " +
            BDContract.Usuario.CONTRASENA + " TEXT, "+
            BDContract.Usuario.TIPO + " INTEGER "+
            ")";

    public static final String SQL_CREATE_OTROS = "CREATE TABLE " +
            BDContract.Otros.TABLE_NAME + " ( " +
            BDContract.Otros.ID + " TEXT PRIMARY KEY, " +
            BDContract.Otros.TITULO + " TEXT, " +
            BDContract.Otros.DESCRIPCION + " TEXT, "+
            BDContract.Usuario.TIPO + " INTEGER "+
            ")";
}
