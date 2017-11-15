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
 * {@link CodigoAbiertoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CodigoAbiertoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodigoAbiertoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;

    private OnFragmentInteractionListener mListener;

    public CodigoAbiertoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodigoAbiertoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CodigoAbiertoFragment newInstance(String param1, String param2) {
        CodigoAbiertoFragment fragment = new CodigoAbiertoFragment();
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
        View v=  inflater.inflate(R.layout.fragment_codigo_abierto, container, false);

        txt = (TextView) v.findViewById(R.id.lblOS);
        txt.setText("Es la comunidad de software libre, en la cual se rigen a partir de 10 principios, de acuerdo a otras comunidades: " +
                "\n" +
                "- Libre redistribución: La licencia del software no debe impedir que este sea regalado o vendido libremente como parte de una distribución mayor que contenga programas de diferentes fuentes. Tampoco debe exigir un pago por hacerlo.\n" +
                "- Código fuente: A la hora de publicar un programa tiene que incluir su código fuente íntegro o permitir acceder libremente a él.\n" +
                "- Trabajos derivados: Las licencias deben permitir modificaciones y trabajos derivados, y debe permitir que estos se distribuyan bajo los mismos términos que el software original.\n" +
                "- Integridad del código fuente del autor: Se puede impedir la distribución de modificaciones únicamente si se permite la distribución de tales como parches. También se puede requerir que trabajos derivados cambien de nombre o número de versión.\n" +
                "- Sin discriminación de personas o grupos: No se puede discriminar a ninguna persona o grupo a la hora de acceder a un programa o su código.\n" +
                "- Sin discriminación de áreas de iniciativa: Tampoco le se puede restringir su acceso a ninguna iniciativa. Las empresas o grupos de investigación tienen tanto derecho como el resto a utilizar el software.\n" +
                "- Distribución de la licencia: Los derechos asociados en las licencias de los programas deben aplicarse a todos a los que lo redistribuyan sin necesidad de pedir una licencia adicional.\n" +
                "- La licencia no debe ser específica de un producto: Un programa no puede licenciarse únicamente como parte de un software mayor. Podrá ser extraído y utilizado libremente y con todos los derechos en otras soluciones.\n" +
                "- La licencia no debe restringir otro software: El hecho de que un proyecto sea de código abierto no puede obligar a que los programas en los que se incluye sean también de código abierto.\n" +
                "- La licencia debe ser tecnológicamente neutral: Ninguna disposición de la licencia puede basarse en la tecnología o un estilo de interfaz, con lo que, por ejemplo, no se debe requerir su aceptación mediante gestos explícitos como clicks de ratón.\n");

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
