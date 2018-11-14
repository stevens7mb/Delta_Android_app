package com.example.bryan.muldel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class fragment_rol extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    Spinner sprol;
    TextView txtusuario;
    EditText txtdpi;
    Button btn_buscar;
    RequestQueue request;
    JsonObjectRequest jsonObjetRequest;
    String usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_rol, container, false);
        sprol = (Spinner) view.findViewById(R.id.sprol);
        String[] datos = new String[] {"Técnico en Mantenimiento", "Administrador"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, datos);
        sprol.setAdapter(adapter);
        txtusuario = (TextView) view.findViewById(R.id.et_usuario);
        txtdpi = (EditText) view.findViewById(R.id.etdpi);
        btn_buscar = (Button) view.findViewById(R.id.btn_buscaru);


        return view;
    }



    //cargar webservice de consulta  a rol
    private void cargarWebservice() {//método cargar webservice
        //String conectar a webservice
        //http://muldel.000webhostapp.com/consulta.php?documento
        //string local http://192.168.1.8/webservices/WSlocalizacion.php?idsitio=
        String url = "http://192.168.1.8/webservices/WSconsultarol.php?dpi="+ txtdpi.getText().toString();
        jsonObjetRequest = new JsonObjectRequest(Request.Method.POST,url,null, this,this);
        request.add(jsonObjetRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        //Consultando a webservice
        Toast.makeText(getContext(),"mensaje"+response,Toast.LENGTH_LONG).show();
        JSONArray json=response.optJSONArray("object_localizacion");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            usuario= jsonObject.optString("nombre_usuario");


        }catch (JSONException e){
            e.printStackTrace();
        }
        txtusuario.setText(usuario);

    }
    }

