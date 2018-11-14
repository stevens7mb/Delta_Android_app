package com.example.bryan.muldel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class fragment_agregarusuario extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText txtdpi;
    EditText txtnombre;
    EditText txtapellido;
    EditText txttelefono;
    EditText txtcorreo;
    Button usuario;
    RequestQueue request;
    JsonObjectRequest jsonObjetRequest;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_agregarusuario, container, false);
        txtdpi = (EditText) view.findViewById(R.id.etdpi);
        txtnombre = (EditText) view.findViewById(R.id.etnombre);
        txtapellido = (EditText) view.findViewById(R.id.etapellido);
        txttelefono = (EditText) view.findViewById(R.id.ettelefono);
        txtcorreo = (EditText) view.findViewById(R.id.etcorreo);
        usuario = (Button) view.findViewById(R.id.btnagregaru);
        request = Volley.newRequestQueue(getContext());//para webservice
        usuario.setOnClickListener(new View.OnClickListener() {//Botón consultar
            @Override
            public void onClick(View v) {
                cargarWebservice(); //metodo cargar webservice
            }


        });
        return view;
    }

    private void cargarWebservice() {//método cargar webservice
        //String conectar a webservice
        //http://muldel.000webhostapp.com/consulta.php?documento
        //string local http://192.168.1.8/webservices/WSlocalizacion.php?idsitio=
        String url = "http://192.168.2.211/webservices/WSagregarusuario.php?dpi="+txtdpi.getText().toString()+"&nombre="+txtnombre.getText().toString()+"&apellido="+txtapellido.getText().toString()+"&telefono="+txttelefono.getText().toString()+"&correo="+txtcorreo.getText().toString();
        url=url.replace(" ","%20");

        jsonObjetRequest = new JsonObjectRequest(Request.Method.POST,url,null, this,this);
        request.add(jsonObjetRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"mensaje"+error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Registrado Correctamente",Toast.LENGTH_LONG).show();

    }
}
