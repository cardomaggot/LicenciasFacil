package una.ac.cr.licenciasfacil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import una.ac.cr.licenciasfacil.Clases.Otros;
import una.ac.cr.licenciasfacil.R;

public class VerOtro extends AppCompatActivity {

    TextView titulo;
    TextView descripcion;
    Otros otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_otro);

        titulo = (TextView) findViewById(R.id.txtTitulo);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);

        otros = (Otros) getIntent().getSerializableExtra("lic");
        titulo.setText(otros.getTitulo());
        descripcion.setText(otros.getDescripcion());
    }
}
