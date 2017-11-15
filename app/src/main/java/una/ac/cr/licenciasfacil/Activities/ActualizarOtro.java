package una.ac.cr.licenciasfacil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Otros;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.R;

public class ActualizarOtro extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtDescripcion;
    Button btnInsertar;
    BDOperations bd = new BDOperations(this);
    String idOtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_otro);


        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
        if(VariablesGlobales.isFaq){
            lblTitulo.setText("Actualizar Pregunta");
        }else{
            lblTitulo.setText("Actualizar Definición");
        }

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcionTitulo);
        btnInsertar = (Button) findViewById(R.id.btnInsertarOtros);


        Otros ot = (Otros) getIntent().getSerializableExtra("lic");
        idOtro=ot.getId();
        txtTitulo.setText(ot.getTitulo());
        txtDescripcion.setText(ot.getDescripcion());

    }


    public void Actualizar(View v){
        if(txtTitulo.getText().toString().equals("")){
            PopUpMensaje("Nombre vacio");
            return;
        }

        if(txtDescripcion.getText().toString().equals("")){
            PopUpMensaje("Descripcion vacio");
            return;
        }

        Otros ot = new Otros();
        ot.setId(idOtro);
        ot.setTitulo(txtTitulo.getText().toString());
        ot.setDescripcion(txtDescripcion.getText().toString());
        if(VariablesGlobales.isFaq)
            ot.setTipo(1);//1 es pregunta
        else
            ot.setTipo(2);//2 es definicion;

        if(bd.updateOtros(ot)){
            PopUpMensaje("Se ha Actualizado correctamente");
            setResult(RESULT_OK);
            finish();
        }else{
            PopUpMensaje("No se ha podido insertar");
        }
    }

    public void PopUpMensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }
}
