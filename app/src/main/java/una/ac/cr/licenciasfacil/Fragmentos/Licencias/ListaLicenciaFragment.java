package una.ac.cr.licenciasfacil.Fragmentos.Licencias;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import una.ac.cr.licenciasfacil.Activities.ActualizarLicencia;
import una.ac.cr.licenciasfacil.Activities.InsertarLicencia;
import una.ac.cr.licenciasfacil.Activities.VerLicencia;
import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.Clases.LicenciaAdapter;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaLicenciaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaLicenciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaLicenciaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnAñadir;
    TextView txtBuscar;
    ListView listaLicencias;
    ArrayList<Licencia> lista = new ArrayList<>();
    LicenciaAdapter adapter;
    BDOperations bd;

    private static final int CHILD_REQUEST = 0;

    private OnFragmentInteractionListener mListener;

    public ListaLicenciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaLicenciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaLicenciaFragment newInstance(String param1, String param2) {
        ListaLicenciaFragment fragment = new ListaLicenciaFragment();
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
        View v = inflater.inflate(R.layout.fragment_lista_licencia, container, false);

        btnAñadir = (Button) v.findViewById(R.id.btnInsertarLicencia);
        listaLicencias = (ListView) v.findViewById(R.id.listLicencias);
        txtBuscar = (TextView) v.findViewById(R.id.txtBuscar);

        if(VariablesGlobales.Usuario.getTipo() == 1 || VariablesGlobales.Usuario.getTipo() == 2 || VariablesGlobales.isListaAprobacion){
            btnAñadir.setVisibility(View.INVISIBLE);
        }

        bd = new BDOperations(v.getContext());

        CargarLista();

        listaLicencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                //cambiar de activity y mandarle el id de la licencia que fue seleccionada
                //ADMIN
                if(VariablesGlobales.Usuario.getTipo() == 0) {
                    Intent intent;
                    if(VariablesGlobales.isListaAprobacion) {
                        intent = new Intent(view.getContext(), VerLicencia.class);
                        intent.putExtra("lic", lista.get(position));//se manda el id para en la otra vista poder cargar los datos

                    }else{
                        intent = new Intent(view.getContext(), ActualizarLicencia.class);
                        intent.putExtra("lic", lista.get(position));//se manda el id para en la otra vista poder cargar los datos
                    }

                    startActivityForResult(intent,CHILD_REQUEST);

                }else{
                    Intent intent = new Intent(view.getContext(), VerLicencia.class);
                    intent.putExtra("lic", lista.get(position));//se manda el id para en la otra vista poder cargar los datos
                    startActivity(intent);
                }
            }
        });

        listaLicencias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                if(VariablesGlobales.Usuario.getTipo() == 0 && !VariablesGlobales.isListaAprobacion) {
                    mensaje_Si_No(lista.get(position).getId());
                }
                return true;
            }
        });

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), InsertarLicencia.class);
                startActivityForResult(inte,CHILD_REQUEST);
            }
        });

        return v;
    }


    public void CargarLista(){
        if(VariablesGlobales.isListaAprobacion) {
            lista = bd.cargarLicenciasApro();
        }else
            lista = bd.cargarLicencias();

        adapter = new LicenciaAdapter(getActivity(), lista);
        listaLicencias.setAdapter(adapter);
    }

    public void mensaje_Si_No(final String idLic){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
        dialogo1.setTitle("Atención");
        dialogo1.setMessage("¿Desea Eliminar la Licencia ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if(bd.deleteLicencia(idLic))
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHILD_REQUEST){
            switch (resultCode) {
                case RESULT_OK: {
                    CargarLista();
                    break;
                }

                case RESULT_CANCELED: {
                    // Cancelación o cualquier situación de error
                    break;
                }
            }
        }
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
