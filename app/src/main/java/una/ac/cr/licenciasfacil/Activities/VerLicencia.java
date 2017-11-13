package una.ac.cr.licenciasfacil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Comentario;
import una.ac.cr.licenciasfacil.Clases.ComentarioAdapter;
import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.Clases.LicenciaAdapter;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.R;

public class VerLicencia extends AppCompatActivity {

    ImageView imagen;
    TextView titulo;
    TextView tipo;
    TextView descripcion;
    TextView software;
    TextView usuario;
    Button btnAprobar;
    Button btnRechazar;

    BDOperations bd = new BDOperations(this);
    Licencia licencia;


    ArrayList<Comentario> lista = new ArrayList<>();
    ComentarioAdapter adapter;

    ListView listaComentarios;
    TextView txtComentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_licencia);

        imagen = (ImageView) findViewById(R.id.imageView3);
        titulo = (TextView) findViewById(R.id.txtTitulo);
        tipo = (TextView) findViewById(R.id.txtTipoLic);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        software = (TextView) findViewById(R.id.txtSoftware);
        usuario = (TextView) findViewById(R.id.txtUsuario);;
        btnAprobar = (Button) findViewById(R.id.btnAprobar);
        btnRechazar = (Button) findViewById(R.id.btnRechazar);

        listaComentarios =(ListView) findViewById(R.id.listaComentarios);
        txtComentario = (TextView) findViewById(R.id.txtComentario);


        licencia = (Licencia) getIntent().getSerializableExtra("lic");
        titulo.setText(licencia.getNombre()+" "+licencia.getVersion() );
        descripcion.setText(licencia.getDescripcion());
        tipo.setText(licencia.getTipo());
        software.setText(licencia.getSoftware());
        //imagen.setBackground(licencia.getImagen());

        if(!VariablesGlobales.isListaAprobacion){
            usuario.setVisibility(View.INVISIBLE);
            findViewById(R.id.textView7).setVisibility(View.INVISIBLE);
            btnAprobar.setVisibility(View.INVISIBLE);
            btnRechazar.setVisibility(View.INVISIBLE);
        }else{
            String nombreUsuario = bd.extraerNombreUsuario(licencia.getUsuario());
            usuario.setText(nombreUsuario);
        }

        if(VariablesGlobales.isListaAprobacion || VariablesGlobales.Usuario.getTipo()==2){
            //MOSTRAR PARA COMENTARIOS  Y LIKES
            listaComentarios.setVisibility(View.INVISIBLE);
            txtComentario.setVisibility(View.INVISIBLE);

        }else{
            CargarLista();
        }


        txtComentario.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = df.format(c.getTime());

                    Comentario cm = new Comentario();
                    cm.setComentario(txtComentario.getText().toString());
                    cm.setUsuario(VariablesGlobales.Usuario.getId());
                    cm.setLicencia(licencia.getId());
                    cm.setFecha(formattedDate);

                    bd.saveComentario(cm);
                    CargarLista();
                    txtComentario.setText("");

                }
                return false;
            }
        });
    }

    public void Rechazar(View view){

        bd.deleteLicenciaApro(licencia.getId());
        PopUpMensaje("Se ha rechazado la Licencia");
        setResult(RESULT_OK);
        finish();

    }

    public void Aprobar(View view){

        bd.saveLicencia(licencia);
        bd.deleteLicenciaApro(licencia.getId());
        PopUpMensaje("Se ha Aprobado la Licencia");
        setResult(RESULT_OK);
        finish();

    }

    public void CargarLista(){

        lista = bd.cargarComentarios(licencia.getId());

        adapter = new ComentarioAdapter(this,lista);
        listaComentarios.setAdapter(adapter);
    }

    public void PopUpMensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }

}
