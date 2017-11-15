package una.ac.cr.licenciasfacil.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import una.ac.cr.licenciasfacil.Clases.Comentario;
import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.Clases.Otros;
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
            values.put(BDContract.Licencia.IMAGEN, lc.getImagen());


            hecho = db.insert(BDContract.Licencia.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public boolean updateLicencia(Licencia lc) {
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
            values.put(BDContract.Licencia.IMAGEN, lc.getImagen());

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
                    l.setImagen(cursor.getBlob(cursor.getColumnIndex(BDContract.LicenciaAprobacion.IMAGEN)));


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

    public Usuario findUsuario(String correo, String pass){
        db=BDHelper.getReadableDatabase();

        Usuario u =null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Usuario.TABLE_NAME+" WHERE "+BDContract.Usuario.EMAIL+" = '"+correo+"' AND "+BDContract.Usuario.CONTRASENA+" = '"+pass+"'",null);
            if(cursor.moveToFirst()) //Si se trajo algo
            {
                u = new Usuario();
                u.setId(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.ID)));
                u.setEmail(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.EMAIL)));
                u.setContrasena(cursor.getString(cursor.getColumnIndex(BDContract.Usuario.CONTRASENA)));
                u.setTipo(cursor.getInt(cursor.getColumnIndex(BDContract.Usuario.TIPO)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return u;

    }

    public String extraerNombreUsuario(String id){
        db=BDHelper.getReadableDatabase();

        String u ="";
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Usuario.TABLE_NAME+" WHERE "+BDContract.Usuario.ID+" = '"+id+"'",null);
            if(cursor.moveToFirst()) //Si se trajo algo
            {
                u = cursor.getString(cursor.getColumnIndex(BDContract.Usuario.EMAIL));

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
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


    //TODO---------------------------------------------------------- METODOS DE LA LICENCIA --------------------------------------------------------

    public boolean saveLicenciaApro(Licencia lc) {

        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.LicenciaAprobacion.ID, UUID.randomUUID().toString());
            values.put(BDContract.LicenciaAprobacion.NOMBRE, lc.getNombre());
            values.put(BDContract.LicenciaAprobacion.VERSION, lc.getVersion());
            values.put(BDContract.LicenciaAprobacion.TIPO, lc.getTipo());
            values.put(BDContract.LicenciaAprobacion.DESCRIPCION, lc.getDescripcion());
            values.put(BDContract.LicenciaAprobacion.SOFTWARE, lc.getSoftware());
            values.put(BDContract.LicenciaAprobacion.USUARIO, lc.getUsuario());
            //values.put(BDContract.LicenciaAprobacion.IMAGEN, lc.getImagen());


            hecho = db.insert(BDContract.LicenciaAprobacion.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public boolean updateLicenciaApro(Licencia lc) {
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.LicenciaAprobacion.NOMBRE, lc.getNombre());
            values.put(BDContract.LicenciaAprobacion.VERSION, lc.getVersion());
            values.put(BDContract.LicenciaAprobacion.TIPO, lc.getTipo());
            values.put(BDContract.LicenciaAprobacion.DESCRIPCION, lc.getDescripcion());
            values.put(BDContract.LicenciaAprobacion.SOFTWARE, lc.getSoftware());
            values.put(BDContract.LicenciaAprobacion.USUARIO, lc.getSoftware());
            //values.put(BDContract.LicenciaAprobacion.IMAGEN, lc.getImagen());

            hecho = db.update(BDContract.LicenciaAprobacion.TABLE_NAME,values,BDContract.LicenciaAprobacion.ID+ " = ?",new String[]{lc.getId()})>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public ArrayList<Licencia> cargarLicenciasApro(){

        ArrayList<Licencia> lista = new ArrayList<Licencia>();
        db=BDHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.LicenciaAprobacion.TABLE_NAME,null);
            if (cursor.moveToFirst()) {//pone el cursor al inicio
                while (!cursor.isAfterLast()) {//Hasta el Final

                    Licencia l = new Licencia();
                    l.setId(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.ID)));
                    l.setNombre(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.NOMBRE)));
                    l.setVersion(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.VERSION)));
                    l.setDescripcion(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.DESCRIPCION)));
                    l.setTipo(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.TIPO)));
                    l.setSoftware(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.SOFTWARE)));
                    l.setUsuario(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.USUARIO)));
                    //l.setImagen(cursor.getString(cursor.getColumnIndex(BDContract.LicenciaAprobacion.IMAGEN)));

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

    public boolean deleteLicenciaApro(String id){
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            hecho = db.delete(BDContract.LicenciaAprobacion.TABLE_NAME, BDContract.LicenciaAprobacion.ID + "=" +"'"+ id+"'", null)>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return hecho;
    }

    //TODO---------------------------------------------------------- METODOS DE LA LICENCIA --------------------------------------------------------

    public boolean saveComentario(Comentario u) {

        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Comentario.ID, UUID.randomUUID().toString());
            values.put(BDContract.Comentario.COMENTARIO, u.getComentario());
            values.put(BDContract.Comentario.LICENCIA, u.getLicencia());
            values.put(BDContract.Comentario.USUARIO, u.getUsuario());
            values.put(BDContract.Comentario.FECHA, u.getFecha());

            hecho = db.insert(BDContract.Comentario.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }



    public ArrayList<Comentario> cargarComentarios(String id){

        ArrayList<Comentario> lista = new ArrayList<Comentario>();
        db=BDHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT c.id, c.comentario, u.email as usuario, c.licencia, c.fecha FROM " + BDContract.Comentario.TABLE_NAME+
                    " c INNER JOIN "+BDContract.Usuario.TABLE_NAME+" u on c.usuario = u.id WHERE c."+BDContract.Comentario.LICENCIA+
                    " = "+"'"+id+"'",null);

            if (cursor.moveToFirst()) {//pone el cursor al inicio
                while (!cursor.isAfterLast()) {//Hasta el Final

                    Comentario u = new Comentario();
                    u.setId(cursor.getString(cursor.getColumnIndex(BDContract.Comentario.ID)));
                    u.setComentario(cursor.getString(cursor.getColumnIndex(BDContract.Comentario.COMENTARIO)));
                    u.setUsuario(cursor.getString(cursor.getColumnIndex(BDContract.Comentario.USUARIO)));
                    u.setLicencia(cursor.getString(cursor.getColumnIndex(BDContract.Comentario.LICENCIA)));
                    u.setFecha(cursor.getString(cursor.getColumnIndex(BDContract.Comentario.FECHA)));

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

    public boolean deleteComentario(String id){
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            hecho = db.delete(BDContract.Comentario.TABLE_NAME, BDContract.Comentario.ID + "=" +"'"+ id+"'", null)>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return hecho;
    }

    //TODO---------------------------------------------------------- METODOS DE OTROS --------------------------------------------------------

    public boolean saveOtros(Otros u) {

        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Otros.ID, UUID.randomUUID().toString());
            values.put(BDContract.Otros.TITULO, u.getTitulo());
            values.put(BDContract.Otros.DESCRIPCION, u.getDescripcion());
            values.put(BDContract.Otros.TIPO, u.getTipo());

            hecho = db.insert(BDContract.Otros.TABLE_NAME, null, values)>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public boolean updateOtros(Otros u) { //recibe una canción para guardarla o actualizarla
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            //El primer parámetro es como poner la tabla, el segundo el dato
            values.put(BDContract.Otros.TITULO, u.getTitulo());
            values.put(BDContract.Otros.DESCRIPCION, u.getDescripcion());
            values.put(BDContract.Otros.TIPO, u.getTipo());

            hecho = db.update(BDContract.Otros.TABLE_NAME,values,BDContract.Otros.ID+ " = ?",new String[]{u.getId()})>0;
        }catch (Exception e){
            e.printStackTrace();
            hecho=false;
        }
        return hecho;
    }

    public ArrayList<Otros> cargarOtros(int id){

        ArrayList<Otros> lista = new ArrayList<Otros>();
        db=BDHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + BDContract.Otros.TABLE_NAME+" WHERE "+BDContract.Otros.TIPO+" = '"+id+"'",null);
            if (cursor.moveToFirst()) {//pone el cursor al inicio
                while (!cursor.isAfterLast()) {//Hasta el Final

                    Otros u = new Otros();
                    u.setId(cursor.getString(cursor.getColumnIndex(BDContract.Otros.ID)));
                    u.setTitulo(cursor.getString(cursor.getColumnIndex(BDContract.Otros.TITULO)));
                    u.setDescripcion(cursor.getString(cursor.getColumnIndex(BDContract.Otros.DESCRIPCION)));
                    u.setTipo(cursor.getInt(cursor.getColumnIndex(BDContract.Otros.TIPO)));

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

    public boolean deleteOtros(String id){
        boolean hecho=false;
        try {
            db = BDHelper.getWritableDatabase();
            hecho = db.delete(BDContract.Otros.TABLE_NAME, BDContract.Otros.ID + "=" +"'"+ id+"'", null)>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return hecho;
    }


}
