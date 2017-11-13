package una.ac.cr.licenciasfacil.Clases;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by cardo on 13/11/17.
 */

public class ComentarioAdapter extends ArrayAdapter<Comentario> {

    Context context;

    public ComentarioAdapter(Context context, ArrayList<Comentario> comentarios) {
        super(context, 0, comentarios);
        this.context = context;
    }

}
