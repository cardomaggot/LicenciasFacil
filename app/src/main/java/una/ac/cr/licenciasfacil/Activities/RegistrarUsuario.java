package una.ac.cr.licenciasfacil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Usuario;
import una.ac.cr.licenciasfacil.R;

public class RegistrarUsuario extends AppCompatActivity {

    TextView txtUser;
    TextView txtPass;
    Button btnRegistrar;
    CheckBox check;
    BDOperations bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        txtUser = (TextView) findViewById(R.id.txtEmailUsuario);
        txtPass = (TextView) findViewById(R.id.txtContrasenaUsuario);
        check = (CheckBox) findViewById(R.id.checkBox);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrar);
        bd = new BDOperations(this);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });

    }

    public void Registrar(){
        if(txtUser.getText().toString().equals("")){
            PopUpMensaje("Usuario vacio");
            return;
        }
        if(txtPass.getText().toString().equals("")){
            PopUpMensaje("Contraseña vacia");
            return;
        }



        Usuario u = new Usuario();
        u.setEmail(txtUser.getText().toString());
        u.setContrasena(txtPass.getText().toString());
        if(check.isChecked())
            u.setTipo(0);//admin
        else
            u.setTipo(1);//normal

        if(bd.saveUsuario(u)){
            PopUpMensaje("Se ha registrado el usuario");
            finish();
        }else{
            PopUpMensaje("No se ha podido insertar el usuario");
        }
    }

    @Override
    public void onBackPressed() {
        Intent inte = new Intent(RegistrarUsuario.this, Login.class);
        startActivity(inte);
        finish();
    }

    public void PopUpMensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }
}
