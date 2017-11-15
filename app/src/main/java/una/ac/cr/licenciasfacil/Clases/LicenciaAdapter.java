package una.ac.cr.licenciasfacil.Clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.SyncStateContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import una.ac.cr.licenciasfacil.R;

/**
 * Created by cardo on 07/11/17.
 */

public class LicenciaAdapter extends ArrayAdapter<Licencia> implements Filterable {

    Context context;
    private List<Licencia>originalData = null;
    private List<Licencia>filteredData = null;
    private ItemFilter mFilter = new ItemFilter();

    public LicenciaAdapter(Context context, ArrayList<Licencia> licencias) {
        super(context, 0, licencias);
        this.filteredData = licencias ;
        this.originalData = licencias ;
        this.context = context;
    }

    public LicenciaAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }


    public int getCount() {
        return filteredData.size();
    }

    public Licencia getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//position llega el # de elemento de la lista
        final Licencia licencia = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.licencia, parent, false);
        }

        ImageView imagen = (ImageView) convertView.findViewById(R.id.imagenLic);
        TextView txtLicencia = (TextView) convertView.findViewById(R.id.txtNombreLicenciaAD);
        TextView txtTipo = (TextView) convertView.findViewById(R.id.txtTipoLicenciaAD);


        Bitmap bmp = BitmapFactory.decodeByteArray(licencia.getImagen(),0,licencia.getImagen().length);
        imagen.setImageBitmap(bmp);
        imagen.setBackgroundResource(0);

        txtLicencia.setText(licencia.getNombre() + " " + licencia.getVersion());
        txtTipo.setText(licencia.getTipo());

        return convertView;
    }


    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Licencia> list = originalData;

            int count = list.size();
            final ArrayList<Licencia> nlist = new ArrayList<Licencia>(count);

            Licencia filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getNombre().toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Licencia>) results.values;
            notifyDataSetChanged();
        }

    }

}
