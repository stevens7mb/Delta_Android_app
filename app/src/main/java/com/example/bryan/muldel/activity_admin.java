package com.example.bryan.muldel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class activity_admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String STRING_PREFERENCES="com.example.bryan.muldel";// String para Shared Preferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        obtenerusuario (); //Método obtener usuario
        //SECCIÓN CODIGO ASIGNAR USUARIO EN LAYOUT DE HEADER
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //con esto generamos el usuario en el header del menu-------------------------------
        View hView = navigationView.getHeaderView(0);
        TextView usuario = (TextView) hView.findViewById(R.id.txtusuario);
        usuario.setText(obtenerusuario());
        navigationView.setNavigationItemSelectedListener(this);
        //FIN SECCION ASIGNAR USUARIO

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_admin, menu);
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
            return true;
        }
        if (id == R.id.action_cerrar_sesion) {
            eliminarnombreuser_cerrar ();//Método que al cerrar sesión borre nombre de usuario.
            //Al cerrar sesión regresar a login
            MainActivity.estadocambiar (activity_admin.this,false);
            Intent intent = new Intent(activity_admin.this, MainActivity.class);
            startActivity(intent);
            finish ();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            Toast.makeText(this, "option 1 navigation selected", Toast.LENGTH_SHORT).show();
            setFragment(0);
        } else if (id == R.id.nav_rol) {
            Toast.makeText(this, "option 2 navigation selected", Toast.LENGTH_SHORT).show();
            setFragment(1);

        } else if (id == R.id.nav_eliminar) {
            Toast.makeText(this, "option 3 navigation selected", Toast.LENGTH_SHORT).show();
            setFragment(2);

        } else if (id == R.id.nav_eliminartra) {
            Toast.makeText(this, "option 5 navigation selected", Toast.LENGTH_SHORT).show();
            setFragment(4);
        } else if (id == R.id.nav_reporte) {
            Toast.makeText(this, "option 6 navigation selected", Toast.LENGTH_SHORT).show();
            setFragment(5);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String obtenerusuario(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        String Usuario = preferences.getString ("usuario","aun no sirve");
        return Usuario;
    }
    public void eliminarnombreuser_cerrar(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().clear().commit();
    }


    //método para establecer el estado de los fragmentos
    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0://Case  fragmento de agregar usuario
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_agregarusuario inboxFragment1 = new fragment_agregarusuario();
                fragmentTransaction.replace(R.id.content_admin, inboxFragment1);
                fragmentTransaction.commit();
                break;
              case 1://Case para Asignar rol
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_rol inboxFragment2 = new fragment_rol();
                fragmentTransaction.replace(R.id.content_admin, inboxFragment2);
                fragmentTransaction.commit();
                break;
            case 2://Case para eliminar usuario
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_eliminar inboxFragment3 = new fragment_eliminar();
                fragmentTransaction.replace(R.id.content_admin, inboxFragment3);
                fragmentTransaction.commit();
                break;

            case 4://Case para modificar mantenimiento de obra civil para telecomunicaciones
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_eliminartrabajo inboxFragment5 = new fragment_eliminartrabajo();
                fragmentTransaction.replace(R.id.content_admin, inboxFragment5);
                fragmentTransaction.commit();
                break;
            case 5://Case para modificar mantenimiento de obra civil para telecomunicaciones
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment_reporte inboxFragment6 = new fragment_reporte();
                fragmentTransaction.replace(R.id.content_admin, inboxFragment6);
                fragmentTransaction.commit();
                break;




        }
    }
}
