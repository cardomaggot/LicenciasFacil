package una.ac.cr.licenciasfacil.Fragmentos.Usuarios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.LicenciaAdapter;
import una.ac.cr.licenciasfacil.Clases.Usuario;
import una.ac.cr.licenciasfacil.Clases.UsuarioAdapter;
import una.ac.cr.licenciasfacil.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaUsuariosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaUsuariosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnAñadir;
    ListView listaUsuario;
    ArrayList<Usuario> lista = new ArrayList<>();
    BDOperations bd;

    private OnFragmentInteractionListener mListener;

    public ListaUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsuarioUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaUsuariosFragment newInstance(String param1, String param2) {
        ListaUsuariosFragment fragment = new ListaUsuariosFragment();
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

        View v = inflater.inflate(R.layout.fragment_lista_usuarios, container, false);

        btnAñadir = (Button) v.findViewById(R.id.btnInsertarUsuario);
        listaUsuario = (ListView) v.findViewById(R.id.listUsuarios);

        bd = new BDOperations(v.getContext());

        CargarLista();

        listaUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                //cambiar de activity y mandarle el id de la licencia que fue seleccionada
                //Intent intent = new Intent(view.getContext(), ActualizarLicencia.class);
                //intent.putExtra("lic",usuario.get(position));//se manda el id para en la otra vista poder cargar los datos
                //startActivity(intent);
            }
        });

        listaUsuario.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                //mensaje_Si_No(usuario.get(position).getId());
                return true;
            }
        });

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent inte = new Intent(v.getContext(), InsertarLicencia.class);
                //startActivity(inte);
            }
        });

        return v;
    }

    public void CargarLista(){
        lista = bd.cargarUsuarios();
        UsuarioAdapter adapter = new UsuarioAdapter(getActivity(), lista);
        listaUsuario.setAdapter(adapter);
    }

    public void mensaje_Si_No(final String idUser){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
        dialogo1.setTitle("Atención");
        dialogo1.setMessage("¿Desea Eliminar la Licencia ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if(bd.deleteUsuario(idUser))
                    Toast.makeText(getActivity(),"Se ha eliminado la licencia",Toast.LENGTH_SHORT);
                else
                    Toast.makeText(getActivity(),"No se ha podido eliminar la licencia",Toast.LENGTH_SHORT);

                CargarLista();
            }
        });
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
            }
        });
        dialogo1.show();
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
