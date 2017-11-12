package una.ac.cr.licenciasfacil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Usuario;
import una.ac.cr.licenciasfacil.R;

public class RegistrarUsuario extends AppCompatActivity {

    TextView txtUser;
    TextView txtPass;
    Button btnRegistrar;
    BDOperations bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        txtUser = (TextView) findViewById(R.id.txtEmailUsuario);
        txtPass = (TextView) findViewById(R.id.txtContrasenaUsuario);
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
        u.setTipo(1);

        if(bd.saveUsuario(u)){
            PopUpMensaje("Se ha insertado el usuario");
            //finish();
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
