package una.ac.cr.licenciasfacil.Fragmentos.Software;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import una.ac.cr.licenciasfacil.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CopyleftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CopyleftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CopyleftFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;


    private OnFragmentInteractionListener mListener;

    public CopyleftFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CopyleftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CopyleftFragment newInstance(String param1, String param2) {
        CopyleftFragment fragment = new CopyleftFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_copyleft, container, false);
        txt = (TextView) v.findViewById(R.id.lblCopyleftDesc);
        txt.setText(" El copyleft es un método general para liberar un programa u otro tipo de trabajo (en el sentido de libertad, no de gratuidad), que requiere que todas las versiones modificadas y extendidas sean también libres.\n" +
                "\n" +
                "La manera más simple de hacer que un programa sea software libre consiste en ponerlo en el dominio público, sin copyright. Esto permite compartir el programa y sus mejoras a quienes así lo deseen. Sin embargo, también posibilita que otra gente sin interés cooperativo convierta el programa en software privativo. Pueden hacer cambios, muchos o pocos, y distribuir el resultado como un producto privativo. Quienes reciban el programa modificado en esas condiciones no podrán disfrutar de la libertad que el autor original les dio. El intermediario se la ha arrebatado.\n" +
                "\n" +
                "El objetivo del proyecto GNU es dar a todos los usuarios la libertad de redistribuir y cambiar el software GNU. Si los intermediarios pudiesen eliminar esta libertad, nuestro código podría «tener muchos usuarios», pero no les daría libertad. Por eso, en vez de poner el software GNU en el dominio público, lo protegemos con copyleft. Copyleft significa que cualquiera que redistribuya el software, con o sin cambios, debe transmitir la libertad de poder seguir haciendo más copias y cambios. El copyleft garantiza que cualquier usuario tenga libertad.");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
