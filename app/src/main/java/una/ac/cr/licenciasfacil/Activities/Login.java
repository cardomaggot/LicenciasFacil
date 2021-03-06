package una.ac.cr.licenciasfacil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import una.ac.cr.licenciasfacil.BaseDatos.BDOperations;
import una.ac.cr.licenciasfacil.Clases.Usuario;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.R;

public class Login extends AppCompatActivity {

    BDOperations db;
    TextView usuario;
    TextView pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (TextView) findViewById(R.id.txtUsuario);
        pass = (TextView) findViewById(R.id.txtPass);

        findViewById(R.id.txtRegistrarse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Login.this, RegistrarUsuario.class);
                startActivity(inte);
                finish();
            }
        });

        findViewById(R.id.txtInvitado).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setEmail("root");
                u.setTipo(0);
                VariablesGlobales.Usuario = u;
                Intent inte = new Intent(Login.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        });
        VariablesGlobales.Usuario = null;
        db = new BDOperations(this);
    }


    public void Iniciar(View v){

        /*if(usuario.getText().toString().trim().equals("")){
            PopUpMensaje("Debe digitar el Usuario");
            return;
        }
        if(pass.getText().toString().trim().equals("")){
            PopUpMensaje("Debe digitar la contraseña");
            return;
        }*/

        /*Usuario u= db.findUsuario(usuario.getText().toString(),pass.getText().toString());
        //Hacer la consulta a la base de datos para sacar el tipo de usuario
        if(u != null) {
            VariablesGlobales.Usuario = u;

            Intent inte = new Intent(Login.this, MainActivity.class);
            startActivity(inte);
            finish();

        }else
            PopUpMensaje("Usuario Incorrecto");*/
        Usuario u = new Usuario();
        u.setTipo(2);
        VariablesGlobales.Usuario = u;
        Intent inte = new Intent(Login.this,MainActivity.class);
        startActivity(inte);
        finish();
    }

    public void PopUpMensaje(String msj){
        Toast.makeText(this,msj,Toast.LENGTH_SHORT).show();
    }
}
