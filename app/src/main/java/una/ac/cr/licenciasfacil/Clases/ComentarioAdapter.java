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

import una.ac.cr.licenciasfacil.R;

/**
 * Created by cardo on 13/11/17.
 */

public class ComentarioAdapter extends ArrayAdapter<Comentario> {

    Context context;

    public ComentarioAdapter(Context context, ArrayList<Comentario> comentarios) {
        super(context, 0, comentarios);
        this.context = context;
    }

    public ComentarioAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//position llega el # de elemento de la lista
        final Comentario comentario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comentario, parent, false);
        }

        TextView txtUsuario = (TextView) convertView.findViewById(R.id.txtUsuarioAD);
        TextView txtComent = (TextView) convertView.findViewById(R.id.txtComentarioAD);

        txtUsuario.setText(comentario.getUsuario());
        txtComent.setText(comentario.getComentario()+comentario.getFecha());

        return convertView;
    }

}
