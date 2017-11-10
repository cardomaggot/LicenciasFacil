package una.ac.cr.licenciasfacil.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.Clases.Usuario;

/**
 * Created by root on 07/10/17.
 */

public class BDOperations {


    Context context;
    BDHelper BDHelper;

    SQLiteDatabase db;

    public BDOperations(Context context) {
        this.context = context;
        BDHelper=new BDHelper(context);
    }

    //TODO---------------------------------------------------------- METODOS DE LA LICENCIA --------------------------------------------------------

    public boolean saveLicencia(Licencia lc) {

        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Licencia.ID, UUID.randomUUID().toString());
            values.put(BDContract.Licencia.NOMBRE, lc.getNombre());
            values.put(BDContract.Licencia.VERSION, lc.getVersion());
            values.put(BDContract.Licencia.TIPO, lc.getTipo());
            values.put(BDContract.Licencia.DESCRIPCION, lc.getDescripcion());
            values.put(BDContract.Licencia.SOFTWARE, lc.getSoftware());

            hecho = db.insert(BDContract.Licencia.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public boolean updateLicencia(Licencia lc) { //recibe una canción para guardarla o actualizarla
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Licencia.NOMBRE, lc.getNombre());
            values.put(BDContract.Licencia.VERSION, lc.getVersion());
            values.put(BDContract.Licencia.DESCRIPCION, lc.getDescripcion());
            values.put(BDContract.Licencia.TIPO, lc.getTipo());
            values.put(BDContract.Licencia.SOFTWARE, lc.getSoftware());

             hecho = db.update(BDContract.Licencia.TABLE_NAME,values,BDContract.Licencia.ID+ " = ?",new String[]{lc.getId()})>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public ArrayList<Licencia> cargarLicencias(){

        ArrayList<Licencia> lista = new ArrayList<Licencia>();
        db=BDHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Licencia.TABLE_NAME,null);
            if (cursor.moveToFirst()) {//pone el cursor al inicio
                while (!cursor.isAfterLast()) {//Hasta el Final

                    Licencia l = new Licencia();
                    l.setId(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.ID)));
                    l.setNombre(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.NOMBRE)));
                    l.setVersion(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.VERSION)));
                    l.setDescripcion(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.DESCRIPCION)));
                    l.setTipo(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.TIPO)));
                    l.setSoftware(cursor.getString(cursor.getColumnIndex(BDContract.Licencia.SOFTWARE)));

                    //Añade la instancia al array de registros
                    lista.add(l);
                    cursor.moveToNext();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean deleteLicencia(String id){
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            hecho = db.delete(BDContract.Licencia.TABLE_NAME, BDContract.Licencia.ID + "=" +"'"+ id+"'", null)>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return hecho;
    }

    //TODO---------------------------------------------------------- METODOS DE USUARIO --------------------------------------------------------

    public boolean saveUsuario(Usuario u) {

        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Usuario.ID, UUID.randomUUID().toString());
            values.put(BDContract.Usuario.EMAIL, u.getEmail());
            values.put(BDContract.Usuario.CONTRASENA, u.getContrasena());
            values.put(BDContract.Usuario.TIPO, u.getTipo());

            hecho = db.insert(BDContract.Usuario.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public boolean updateUsuario(Usuario u) { //recibe una canción para guardarla o actualizarla
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Usuario.EMAIL, u.getEmail());
            values.put(BDContract.Usuario.CONTRASENA, u.getContrasena());
            values.put(BDContract.Usuario.TIPO, u.getTipo());

            hecho = db.update(BDContract.Licencia.TABLE_NAME,values,BDContract.Usuario.ID+ " = ?",new String[]{u.getId()})>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public ArrayList<Usuario> cargarUsuarios(){

        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        db=BDHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Usuario.TABLE_NAME,null);
            if (cursor.moveToFirst()) {//pone el cursor al inicio
                while (!cursor.isAfterLast()) {//Hasta el Final

                    Usuario u = new Usuario();
                    u.setId(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.ID)));
                    u.setEmail(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.EMAIL)));
                    u.setContrasena(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.CONTRASENA)));
                    u.setTipo(cursor.getInt(cursor.getColumnIndex(BDContract.Usuario.TIPO)));

                    //Añade la instancia al array de registros
                    lista.add(u);
                    cursor.moveToNext();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public boolean findUsuario(String correo, String pass){
        db=BDHelper.getReadableDatabase();

        Boolean find =false;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Usuario.TABLE_NAME+" WHERE "+BDContract.Usuario.EMAIL+" = "+correo,null);
            if(cursor!=null) //Si se trajo algo
            {
                cursor.moveToFirst();
                find=true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return find;

    }

    public boolean deleteUsuario(String id){
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            hecho = db.delete(BDContract.Usuario.TABLE_NAME, BDContract.Usuario.ID + "=" +"'"+ id+"'", null)>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return hecho;
    }

}
