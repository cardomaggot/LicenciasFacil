package una.ac.cr.licenciasfacil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import una.ac.cr.licenciasfacil.Clases.Licencia;
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

    Licencia licencia;

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

        licencia = (Licencia) getIntent().getSerializableExtra("lic");
        titulo.setText(licencia.getNombre()+" "+licencia.getVersion() );
        descripcion.setText(licencia.getDescripcion());
        tipo.setText(licencia.getTipo());
        software.setText(licencia.getSoftware());
        //imagen.setBackground(licencia.getImagen());

        if(!VariablesGlobales.isListaAprobacion){
            usuario.setText(licencia.getUsuario());
            usuario.setVisibility(View.INVISIBLE);
            btnAprobar.setVisibility(View.INVISIBLE);
            btnRechazar.setVisibility(View.INVISIBLE);
        }

    }

    public void Rechazar(View view){

    }

    public void Aprobar(View view){

    }

}
