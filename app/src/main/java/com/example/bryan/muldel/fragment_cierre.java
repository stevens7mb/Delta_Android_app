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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;


public class fragment_cierre extends Fragment {
    EditText txtid;
    EditText txtfecha;
    EditText txthora;
    Spinner sptecnico;
    Spinner spsupervisor;
    ImageView img1;
    ImageView img2;
    Button btnimg1;
    Button btnimg2;
    Button btnguardar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_cierre, container, false);
        txtid = (EditText) view.findViewById(R.id.etid);
        txtfecha = (EditText) view.findViewById(R.id.etfecha_fin);
        txthora = (EditText) view.findViewById(R.id.ethora_fin);
        sptecnico = (Spinner) view.findViewById(R.id.stecnico);
        spsupervisor = (Spinner) view.findViewById(R.id.ssupervisor);
        img1 = (ImageView) view.findViewById(R.id.imgplaca);
        img2 = (ImageView) view.findViewById(R.id.imgeneral);
        btnimg1 = (Button) view.findViewById(R.id.btn_foto1);
        btnimg2 = (Button) view.findViewById(R.id.btnfoto2);
        btnguardar = (Button) view.findViewById(R.id.btn_guardarinfo);
        return view;

    }


}



