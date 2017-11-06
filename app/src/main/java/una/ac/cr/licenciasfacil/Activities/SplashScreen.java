package una.ac.cr.licenciasfacil.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import una.ac.cr.licenciasfacil.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inte = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        },3000);
    }
}
