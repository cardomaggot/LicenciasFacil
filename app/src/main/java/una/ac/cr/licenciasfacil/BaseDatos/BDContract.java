package una.ac.cr.licenciasfacil.BaseDatos;

import android.provider.BaseColumns;

/**
 * Created by root on 07/10/17.
 */

public class BDContract {
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            RegistroEntry.TABLE_NAME + " ( " +
            RegistroEntry.COLUMN_NAME_JUGADOR + " PRIMARY KEY, " +
            RegistroEntry.COLUMN_NAME_TIEMPO + " TEXT, "+
            RegistroEntry.COLUMN_NAME_JUEGO + " TEXT )";
    //El resultado final va a ser CREATE TABLE buscaminas (jugador TEXT PRIMARY KEY, jugador TEXT, juego DATETIME)
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + RegistroEntry.TABLE_NAME;

    public static class RegistroEntry implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "licencias";
        public static final String COLUMN_NAME_JUGADOR = "jugador";
        public static final String COLUMN_NAME_TIEMPO = "tiempo";
        public static final String COLUMN_NAME_JUEGO= "juego";
    }

    //http://www.hermosaprogramacion.com/2016/01/base-de-datos-sqlite-en-android-con-multiples-tablas/
}
