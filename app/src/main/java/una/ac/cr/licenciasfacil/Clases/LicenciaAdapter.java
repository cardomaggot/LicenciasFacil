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
 * Created by cardo on 07/11/17.
 */

public class LicenciaAdapter extends ArrayAdapter<Licencia> {

    Context context;

    public LicenciaAdapter (Context context, ArrayList<Licencia> licencias){
        super(context,0,licencias);
        this.context=context;
    }

    public LicenciaAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){//position llega el # de elemento de la lista
        final Licencia licencia = getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.licencia,parent,false);
        }

        ImageView imagen = (ImageView)convertView.findViewById(R.id.imagenLic);
        TextView txtLicencia = (TextView)convertView.findViewById(R.id.txtNombreLicenciaAD);
        TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipoLicenciaAD);

        //imagen.setBackground(R.drawable."licencia.imagen");algo asi es
        txtLicencia.setText(licencia.getNombre()+" "+licencia.getVersion());
        txtTipo.setText(licencia.getTipo());

        return convertView;
    }
}
