package una.ac.cr.licenciasfacil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Licencia;
import una.ac.cr.licenciasfacil.R;

public class ActualizarLicencia extends AppCompatActivity {

    TextView txtNombre;
    TextView txtVersion;
    TextView txtTipo;
    TextView txtDescripcion;
    TextView txtSoftware;
    Button btnActualizar;
    BDOperations bd = new BDOperations(this);

    String idLicencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_licencia);

        txtNombre = (TextView) findViewById(R.id.txtNombreLicencia);
        txtVersion = (TextView) findViewById(R.id.txtVersionLicencia);
        txtTipo = (TextView) findViewById(R.id.txtTipoLicencia);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcionLicencia);
        txtSoftware = (TextView) findViewById(R.id.txtSoftwareLicencia);
        btnActualizar = (Button) findViewById(R.id.btnInsertarLicencia);

        Licencia li = (Licencia) getIntent().getSerializableExtra("lic");
        idLicencia=li.getId();
        txtNombre.setText(li.getNombre());
        txtVersion.setText(li.getVersion());
        txtDescripcion.setText(li.getDescripcion());
        txtTipo.setText(li.getTipo());
        txtSoftware.setText(li.getSoftware());
    }


    public void Actualizar(View v){
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
        lc.setId(idLicencia);
        lc.setNombre(txtNombre.getText().toString());
        lc.setVersion(txtVersion.getText().toString());
        lc.setDescripcion(txtDescripcion.getText().toString());
        lc.setTipo(txtTipo.getText().toString());
        lc.setSoftware(txtSoftware.getText().toString());

        if(bd.updateLicencia(lc)){
            PopUpMensaje("Se ha Actualizado la Licencia");
            finish();
        }else{
            PopUpMensaje("No se ha podido Actualizar la Licencia");
        }
    }


    public void PopUpMensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }

}
