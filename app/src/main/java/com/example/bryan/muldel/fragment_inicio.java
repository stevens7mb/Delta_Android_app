package com.example.bryan.muldel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
        txtFecha = (EditText) view.findViewById(R.id.txtFechaini);
        txtFecha.setText(fecha);
        txthora = (EditText) view.findViewById(R.id.txthora);
        txthora.setText(hora);
        return view;
    }


}
