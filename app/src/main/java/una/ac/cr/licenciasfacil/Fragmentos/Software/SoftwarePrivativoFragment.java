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
 * {@link SoftwarePrivativoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SoftwarePrivativoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoftwarePrivativoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;

    private OnFragmentInteractionListener mListener;

    public SoftwarePrivativoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SoftwarePrivativoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SoftwarePrivativoFragment newInstance(String param1, String param2) {
        SoftwarePrivativoFragment fragment = new SoftwarePrivativoFragment();
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
        View v= inflater.inflate(R.layout.fragment_software_privativo, container, false);
        txt = (TextView) v.findViewById(R.id.lblSoftwareLibre);
        txt.setText("Se denomina con el falso amigo software propietario, o, dicho correctamente, privativo, al software del cual no existe una forma libre de acceso a su código fuente, el cual solo se encuentra a disposición de su desarrollador y no se permite su libre modificación, adaptación o incluso lectura por parte de terceros. El término ha sido creado para designar al antónimo del concepto de software libre, por lo cual en diversos sectores se le han asignado implicaciones políticas relativas al mismo. Para la Fundación para el Software Libre (FSF), este concepto se aplica a cualquier programa informático que no es libre o que solo lo es parcialmente (semilibre), sea porque su uso, redistribución o modificación está prohibida, o sea porque requiere permiso expreso del titular del software.\n" +
                "\n" +
                "La persona física o jurídica (compañía, corporación, fundación, etc.), al poseer los derechos de autor sobre un software, tiene la posibilidad de controlar y restringir los derechos del usuario sobre su programa, lo que en el software no libre implica por lo general que el usuario solo tendrá derecho a ejecutar el software bajo ciertas condiciones, comúnmente fijadas por el proveedor, que signifique la restricción de una o varias de las cuatro libertades.");
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
