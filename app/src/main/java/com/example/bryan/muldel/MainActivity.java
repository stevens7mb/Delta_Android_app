package com.example.bryan.muldel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity implements Response.Listener <JSONObject>, ErrorListener, CompoundButton.OnCheckedChangeListener {
    RequestQueue rq;//Json
    JsonRequest jrq;//Json
    JSONArray ja;
    String Var="steve";
    String Pass="123";
    Boolean rbactivado;
    public static final String STRING_PREFERENCES="com.example.bryan.muldel";// String para Shared Preferences
    public static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";//Preferencia guardar estado botón radio
    //Declaración de objetos, instanciar
    Button button_admin;
    EditText textusuario;
    TextView textView;
    EditText editText2,edituser;
    RadioButton rbsesion;
    Switch sbadmin;
    int banderaswitch=0;//Bandera para conocer el estado del switch button


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Comparación inicio estado botón
        if(obtenerestadorbsesion()){
            //Redirigir a activity correspondiente
            //Intent intent = new Intent(MainActivity.this, Administrador.class);
            Intent intent = new Intent(MainActivity.this, activity_admin.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociacion de objetos en java con XML
        button_admin = (Button)findViewById(R.id.btn_admin); //Asociamos objeto con btn_admin del XML
        textusuario = (EditText)findViewById(R.id.editText1);//Asociamos objeto editText1 del XML
        editText2 = (EditText) findViewById(R.id.editText2);//Asociamos objeto editText1 del XML
        rbsesion= (RadioButton) findViewById(R.id.rbsesion);
        rbactivado = rbsesion.isChecked();//seleccionamos valor inicial de  botón de sesión.
        sbadmin = (Switch) findViewById(R.id.swbutton);


         rq = Volley.newRequestQueue(this);
        //Abrir activity administrador

        //radio button de sesion cambiar de estado
        rbsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbactivado){
                    rbsesion.setChecked(false); // si recibe el valor de rbactivado lo pondremos en falso
                }
                rbactivado = rbsesion.isChecked();
            }
        });

        //

        sbadmin.setOnCheckedChangeListener(this);//llamamos a método de on/off


        button_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            //evento click en boton
            public void onClick(View v) {

                if(banderaswitch==1){
                    ConsultaPass("http://192.168.43.115/ejemplologin/consultarusuario.php?user="+textusuario.getText().toString());
                }else {
                    iniciarsesion();
                }
                //Guardar Shared preferences de nombre de usuario
                SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
                preferences.edit().putString("usuario", textusuario.getText().toString()).apply();//Se guarda estado activado como true de Radio buton
            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorrecto"+error, Toast.LENGTH_SHORT).show();
    }

    @Override




    public void onResponse(JSONObject response) {
        User usuario = new User();
        Toast.makeText(getApplicationContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        guardarestadorbsesion();//Guardar estado al logearse
        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("usuario"));
            usuario.setPwd(jsonObject.optString("pass"));
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"El usuario no existe en la base de datos",Toast.LENGTH_LONG).show();

        }
        //Obtener datos de nombre para pasarlo a activity administrador
        //Intent redireccion a nav_administrador
        Intent intent = new Intent(MainActivity.this, activity_tecnico.class);//redirección a activity técnico drawer
        //intent.putExtra(activity_admin.user, usuario.getUser());
        startActivity(intent);
        finish();


    }
    //Método Iniciar sesion
    private void iniciarsesion(){
        //hosting http://muldel.000webhostapp.com/login.php?user
       // http://192.168.1.8/webservices
        String url ="http://muldel.000webhostapp.com/login.php?user/login.php?user/login.php?user="+textusuario.getText().toString()+"&pwd="+editText2.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.POST, url, null, this, this);
        rq.add(jrq);
    }

    //Metodo guardar estado de radio button
    public void guardarestadorbsesion() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,rbsesion.isChecked()).apply();//Se guarda estado activado como true de Radio buton
    }

    //Metodo obtener estado de radio button
    public boolean obtenerestadorbsesion() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION, false);
    }

    //metodo a activity 2 para cerrar cesión
    public static void estadocambiar(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences (STRING_PREFERENCES, MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,b).apply();
    }

    private void ConsultaPass(String URL) {

        Log.i("url",""+URL);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    ja = new JSONArray(response);
                    String contra = ja.getString(0);
                    if(contra.equals(editText2.getText().toString())){

                        Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, activity_admin.class);//redirección a administrador drawer
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecto",Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(),"El usuario no existe en la base de datos",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);



    }

        //Método para saber si Switch button esta activado o desactivado.
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(sbadmin.isChecked()){
            banderaswitch=1;
        }else{
            banderaswitch=0;
        }
    }
}
