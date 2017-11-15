package una.ac.cr.licenciasfacil.Fragmentos.Usuarios;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SugerirLicenciaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SugerirLicenciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SugerirLicenciaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imagen;

    TextView txtNombre;
    TextView txtVersion;
    TextView txtTipo;
    TextView txtDescripcion;
    TextView txtSoftware;
    Button btnSugerir;
    Button btnImagen;
    BDOperations bd;

    private OnFragmentInteractionListener mListener;

    public SugerirLicenciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SugerirLicenciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SugerirLicenciaFragment newInstance(String param1, String param2) {
        SugerirLicenciaFragment fragment = new SugerirLicenciaFragment();
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
        View v = inflater.inflate(R.layout.fragment_sugerir_licencia, container, false);
        imagen = (ImageView) v.findViewById(R.id.imageViews);
        txtNombre = (TextView) v.findViewById(R.id.txtNombreLicencia);
        txtVersion = (TextView) v.findViewById(R.id.txtVersionLicencia);
        txtTipo = (TextView) v.findViewById(R.id.txtTipoLicencia);
        txtDescripcion = (TextView) v.findViewById(R.id.txtDescripcionLicencia);
        txtSoftware = (TextView) v.findViewById(R.id.txtSoftwareLicencia);
        btnSugerir = (Button) v.findViewById(R.id.btnSugerir);
        btnImagen = (Button) v.findViewById(R.id.btnCargarImgen);

        btnSugerir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sugerir();
            }
        });

        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        bd = new BDOperations(v.getContext());

        return v;
    }

    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path=data.getData();
            imagen.setImageURI(path);
        }
    }

    public void Sugerir(){
        if(txtNombre.getText().toString().equals("")){
            PopUpMensaje("Nombre vacio");
            return;
        }
        if(txtVersion.getText().toString().equals("")){
            PopUpMensaje("Version vacio");
            return;
        }
        if(txtTipo.getText().toString().equals("")){
            PopUpMensaje("Tipo vacio");
            return;
        }
        if(txtDescripcion.getText().toString().equals("")){
            PopUpMensaje("Descripcion vacio");
            return;
        }
        if(txtSoftware.getText().toString().equals("")){
            PopUpMensaje("Software vacio");
            return;
        }

        Licencia lc = new Licencia();
        lc.setNombre(txtNombre.getText().toString());
        lc.setVersion(txtVersion.getText().toString());
        lc.setDescripcion(txtDescripcion.getText().toString());
        lc.setTipo(txtTipo.getText().toString());
        lc.setSoftware(txtSoftware.getText().toString());
        lc.setUsuario(VariablesGlobales.Usuario.getId());

        if(bd.saveLicenciaApro(lc)){
            PopUpMensaje("Se ha insertado la licencia para ser revisada por el administrador");
            //LimpiarCampos
            txtNombre.setText("");
            txtVersion.setText("");
            txtDescripcion.setText("");
            txtTipo.setText("");
            txtSoftware.setText("");
        }else{
            PopUpMensaje("No se ha podido insertar la Licencia");
        }
    }


    public void PopUpMensaje(String msj){
        Toast.makeText(getActivity(),msj,Toast.LENGTH_SHORT).show();
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
