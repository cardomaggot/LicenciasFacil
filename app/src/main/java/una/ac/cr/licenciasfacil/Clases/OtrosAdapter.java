package una.ac.cr.licenciasfacil.Clases;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import una.ac.cr.licenciasfacil.R;

/**
 * Created by cardo on 14/11/17.
 */

public class OtrosAdapter extends ArrayAdapter<Otros> {

    Context context;
    private List<Otros> originalData = null;
    private List<Otros>filteredData = null;
    private ItemFilter mFilter = new ItemFilter();

    public OtrosAdapter (Context context, ArrayList<Otros> otros){
        super(context,0,otros);
        this.filteredData = otros ;
        this.originalData = otros ;
        this.context=context;
    }

    public OtrosAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){//position llega el # de elemento de la lista
        final Otros otros = getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.otros,parent,false);
        }

        ImageView imagen = (ImageView)convertView.findViewById(R.id.imagenOtro);
        TextView txtOtros = (TextView)convertView.findViewById(R.id.txtOtrosAD);
        //TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipoOtrosAD);

        txtOtros.setText(otros.getTitulo());
        if(otros.getTipo()==2)
            imagen.setBackgroundResource(R.drawable.ic_info);

        return convertView;
    }

    public int getCount() {
        return filteredData.size();
    }

    public Otros getItem(int position) {
        return filteredData.get(position);
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Otros> list = originalData;

            int count = list.size();
            final ArrayList<Otros> nlist = new ArrayList<Otros>(count);

            Otros filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getTitulo().toLowerCase().contains(filterString)) {
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
            filteredData = (ArrayList<Otros>) results.values;
            notifyDataSetChanged();
        }

    }
}
