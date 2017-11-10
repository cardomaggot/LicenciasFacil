package una.ac.cr.licenciasfacil.BaseDatos;

import android.provider.BaseColumns;

import java.util.UUID;

/**
 * Created by root on 07/10/17.
 */

public class BDContract {

    //TODO----------------------------------------- MODELOS --------------------------------------------

    public static class Comentario implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "comentarios";
        public static final String ID = "id";
        public static final String COMENTARIO = "comentario";
        public static final String USUARIO = "usuario";
        public static final String LICENCIA= "licencia";
        public static final String FECHA= "fecha";
    }

    public static class Licencia implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "licencias";
        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String TIPO = "tipo";
        public static final String VERSION = "version";
        public static final String DESCRIPCION = "descripcion";
        public static final String SOFTWARE = "software";

    }

    public static class LicenciaAprobacion implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "licenciasAprobar";
        public static final String ID = "id";
        public static final String USUARIO = "usuario";
        public static final String NOMBRE = "nombre";
        public static final String DESCRIPCION= "descripcion";
        public static final String TIPO= "tipo";
    }

    public static class Likes implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "likes";
        public static final String ID = "id";
        public static final String LICENCIA = "licencia";
    }

    public static class Usuario implements BaseColumns { //Nombre de la tabla y de los datos
        public static final String TABLE_NAME = "usuarios";
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String CONTRASENA = "contrasena";
        public static final String TIPO = "tipo";
    }
}


