package una.ac.cr.licenciasfacil.Activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import layout.OtrosFragment;
import una.ac.cr.licenciasfacil.Clases.VariablesGlobales;
import una.ac.cr.licenciasfacil.Fragmentos.Licencias.ListaLicenciaFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Software.CodigoAbiertoFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Software.CopyleftFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Software.CreativeCommonsFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Software.SoftwareLibreFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Software.SoftwarePrivativoFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Usuarios.ListaUsuariosFragment;
import una.ac.cr.licenciasfacil.Fragmentos.Usuarios.SugerirLicenciaFragment;
import una.ac.cr.licenciasfacil.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ListaUsuariosFragment.OnFragmentInteractionListener,
        SoftwareLibreFragment.OnFragmentInteractionListener,ListaLicenciaFragment.OnFragmentInteractionListener,
        SoftwarePrivativoFragment.OnFragmentInteractionListener, CreativeCommonsFragment.OnFragmentInteractionListener,
        CodigoAbiertoFragment.OnFragmentInteractionListener,SugerirLicenciaFragment.OnFragmentInteractionListener,
        OtrosFragment.OnFragmentInteractionListener,CopyleftFragment.OnFragmentInteractionListener
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Licencias Facil V 1.0 CC", 2000)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Si es admin
        if(VariablesGlobales.Usuario.getTipo() == 0){
            navigationView.getMenu().findItem(R.id.nav_AgregarLicencia).setVisible(false);
        }

        //Si es usuario o invitado
        if(VariablesGlobales.Usuario.getTipo() == 1 || VariablesGlobales.Usuario.getTipo() == 2 ){

            navigationView.getMenu().findItem(R.id.nav_ListaUsuarios).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_LicenciasAprobar).setVisible(false);

            if(VariablesGlobales.Usuario.getTipo() == 2){
                navigationView.getMenu().findItem(R.id.nav_AgregarLicencia).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_CerrarSesion).setTitle("Salir");
            }
        }

        navigationView.getMenu().findItem(R.id.nav_ListaUsuarios).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_LicenciasAprobar).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_CerrarSesion).setTitle("Salir");
        getSupportActionBar().setTitle("Licencias Fácil");

    }

    private long tiempoPrimerClick;
    @Override
    public void onBackPressed() {
        if (tiempoPrimerClick + 2000> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fg = null;
        boolean cambioFragment=false;

        //REGISTRO USUARIO
        if (id == R.id.nav_ListaUsuarios) {
            fg=new ListaUsuariosFragment();
            cambioFragment=true;
        }
        //REGISTRO LICENCIAS
        else if (id == R.id.nav_ListaLicencias) {
            fg=new ListaLicenciaFragment();
            cambioFragment=true;
            VariablesGlobales.isListaAprobacion=false;

        }else if (id == R.id.nav_LicenciasAprobar) {
            fg=new ListaLicenciaFragment();
            cambioFragment=true;
            VariablesGlobales.isListaAprobacion=true;

        }else if (id == R.id.nav_AgregarLicencia) {
            fg=new SugerirLicenciaFragment();
            cambioFragment=true;
            VariablesGlobales.isListaAprobacion=true;

        }
        //SOFTWARE
        else if (id == R.id.nav_SoftwareLibre) {
            fg=new SoftwareLibreFragment();
            cambioFragment=true;

        }else if (id == R.id.nav_SoftwareAbierto) {
            fg = new CodigoAbiertoFragment();
            cambioFragment = true;

        }else if (id == R.id.nav_CreativeCommons) {
            fg = new CreativeCommonsFragment();
            cambioFragment = true;

        }else if (id == R.id.nav_Copyleft) {
            fg = new CopyleftFragment();
            cambioFragment = true;

        }else if (id == R.id.nav_SoftwarePrivativo) {
            fg = new SoftwarePrivativoFragment();
            cambioFragment = true;
        }
        else if (id == R.id.nav_CerrarSesion) {
            Intent inte = new Intent(MainActivity.this, Login.class);
            startActivity(inte);
            finish();
        }
        //OTROS
        else if (id == R.id.nav_FAQ) {
            fg = new OtrosFragment();
            cambioFragment = true;
            VariablesGlobales.isFaq=true;
        } else if (id == R.id.nav_otros) {
            fg = new OtrosFragment();
            cambioFragment = true;
            VariablesGlobales.isFaq=false;
        }

        if(cambioFragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fg).commit();
            getSupportActionBar().setTitle(item.getTitle());
            cambioFragment=false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
