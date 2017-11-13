package una.ac.cr.licenciasfacil.Clases;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import una.ac.cr.licenciasfacil.Clases.Usuario;
import una.ac.cr.licenciasfacil.R;

/**
 * Created by cardo on 10/11/17.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    Context context;

    public UsuarioAdapter (Context context, ArrayList<Usuario> usuarios){
        super(context,0,usuarios);
        this.context=context;
    }

    public UsuarioAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){//position llega el # de elemento de la lista
        final Usuario usuario = getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.usuario,parent,false);
        }

        ImageView imagen = (ImageView)convertView.findViewById(R.id.imagenUser);
        TextView txtUsuario = (TextView)convertView.findViewById(R.id.txtUsuarioAD);
        TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipoUsuarioAD);

        //imagen.setBackground(R.drawable."usuario.imagen");algo asi es
        txtUsuario.setText(usuario.getEmail());
        if(usuario.getTipo()==0)
            txtTipo.setText("Admin");
        else
            txtTipo.setText("Simple");

        return convertView;
    }
}
