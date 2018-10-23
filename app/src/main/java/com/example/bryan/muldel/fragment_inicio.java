package com.example.bryan.muldel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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


import com.example.bryan.muldel.entidades.ConexionSQLiteHelper;
import com.example.bryan.muldel.utilidades.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;


public class fragment_inicio extends Fragment {
    EditText txtFecha;
    EditText txthora;

    EditText txtTicket;
    EditText txt_idsitio;
    EditText txt_nombsitio;
    EditText txtitem;
    EditText txt_desctrabajo;
    Button btnguardar;
    String fecha;
    String hora;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        fecha = dateFormat.format(date);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date2 = new Date();
        hora = dateFormat2.format(date2);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_inicio, container, false);
        txtTicket = (EditText) view.findViewById(R.id.txtTicket);
        txt_idsitio = (EditText) view.findViewById(R.id.txtid_sitio);
        txt_nombsitio = (EditText) view.findViewById(R.id.txtnombre_sitio);
        txtitem = (EditText) view.findViewById(R.id.txtitem);
        txt_desctrabajo = (EditText) view.findViewById(R.id.txtdesc_trabajo);
        btnguardar = (Button) view.findViewById(R.id.btn_guardaraper);

        txtFecha = (EditText) view.findViewById(R.id.txtFechaini);
        txtFecha.setText(fecha);
        txthora = (EditText) view.findViewById(R.id.txthora);
        txthora.setText(hora);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarevento();
            }
        });


        return view;
    }

    private void registrarevento() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(getContext(),"bd_evento",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(utilidades.TICKET,txtTicket.getText().toString());
        values.put(utilidades.FECHA_INICIO,txtFecha.getText().toString());
        values.put(utilidades.HORA_INICIO,txthora.getText().toString());
        values.put(utilidades.FECHA_INICIO,txtFecha.getText().toString());
        values.put(utilidades.ID_SITIO,txt_idsitio.getText().toString());
        values.put(utilidades.NOMBRE_SITIO,txt_nombsitio.getText().toString());
        values.put(utilidades.ITEM,txtitem.getText().toString());
        values.put(utilidades.DESCRIPCION_TRABAJO,txt_desctrabajo.getText().toString());


        Long idResultante=db.insert(utilidades.TABLA_EVENTO,utilidades.TICKET,values);

        Toast.makeText(getContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }


}
