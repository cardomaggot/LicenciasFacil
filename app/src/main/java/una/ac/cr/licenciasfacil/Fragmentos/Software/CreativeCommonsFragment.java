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
 * {@link CreativeCommonsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreativeCommonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreativeCommonsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;

    private OnFragmentInteractionListener mListener;

    public CreativeCommonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreativeCommonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreativeCommonsFragment newInstance(String param1, String param2) {
        CreativeCommonsFragment fragment = new CreativeCommonsFragment();
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
        View v= inflater.inflate(R.layout.fragment_creative_commons, container, false);
        txt = (TextView) v.findViewById(R.id.lblCC);
        txt.setText("Creative Commons (CC) ―en español, \"Comunes Creativos\"― es una organización sin fines de lucro dedicada a promover el acceso y el intercambio de cultura. Desarrolla un conjunto de instrumentos jurídicos de carácter gratuito que facilitan usar y compartir tanto la creatividad como el conocimiento. Su sede central se encuentra en Mountain View, en el estado de California, Estados Unidos.\n" +
                "\n" +
                "Los instrumentos jurídicos desarrollados por la organización consisten en un conjunto de “modelos de contratos de licenciamiento” o licencias de derechos de autor (licencias Creative Commons o licencias CC) que ofrecen al autor de una obra una manera simple y estandarizada de otorgar permiso al público para compartir y usar su trabajo creativo bajo los términos y condiciones de su elección. En este sentido, las licencias Creative Commons permiten al autor cambiar fácilmente los términos y condiciones de derechos de autor de su obra de “todos los derechos reservados” a “algunos derechos reservados”.\n" +
                "\n" +
                "Las licencias Creative Commons no reemplazan a los derechos de autor, sino que se apoyan en estos para permitir elegir los términos y condiciones de la licencia de una obra de la manera que mejor satisfaga al titular de los derechos. Por tal motivo, estas licencias han sido entendidas por muchos como una manera en que los autores pueden tomar el control de cómo quieren compartir su propiedad intelectual.");
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
