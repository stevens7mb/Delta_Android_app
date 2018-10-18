package com.example.bryan.muldel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
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


public class mapsFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    Button boton_geo;
    TextView txtentrada;
    TextView txtsalida;
    TextView txtsalidaregion;
    Button button_buscar;
    RequestQueue request;
    JsonObjectRequest jsonObjetRequest;
    String usuario;
    String usuario2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        txtentrada = (TextView) view.findViewById(R.id.entrada);
        txtsalida = (TextView) view.findViewById(R.id.salida);
        txtsalidaregion = (TextView) view.findViewById(R.id.salida_region);
        request = Volley.newRequestQueue(getContext());//para webservice
        button_buscar= (Button)view.findViewById(R.id.btn_buscar);
        button_buscar.setOnClickListener(new View.OnClickListener() {//Botón consultar
            @Override
            public void onClick(View v) {
                cargarWebservice(); //metodo cargar webservice
            }


        });

        boton_geo = (Button) view.findViewById(R.id.boton_mapa);
        boton_geo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // Intent intent = new Intent(getActivity(), mapsActivity.class);
              //  getActivity().startActivity(intent);

                //Fragmento de código abrir activity de Google Maps con la ruta
                Toast.makeText(getContext(),"Generando Ruta a BTS",Toast.LENGTH_LONG).show();
                double latitud=-90.222; // para la latitud valor decimal
                double longitud=15.0000;// para la longitud valor decimal
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitud+","+longitud+"&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        return view;


    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"mensaje"+error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"mensaje"+response,Toast.LENGTH_LONG).show();
        JSONArray json=response.optJSONArray("usuario");
        JSONObject jsonObject=null;
        try {
            jsonObject=json.getJSONObject(0);
            usuario= jsonObject.optString("nombre_sitio");
            usuario2= jsonObject.optString("nombre_sede");

        }catch (JSONException e){
            e.printStackTrace();
        }
        txtsalida.setText(usuario);
        txtsalidaregion.setText(usuario2);
    }

    private void cargarWebservice() {//método cargar webservice
        String url = "http://192.168.1.8/webservices/consulta.php?documento="+ txtentrada.getText().toString();
        jsonObjetRequest = new JsonObjectRequest(Request.Method.POST,url,null, this,this);
        request.add(jsonObjetRequest);

    }


}
