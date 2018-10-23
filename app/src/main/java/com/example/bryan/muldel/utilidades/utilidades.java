package com.example.bryan.muldel.utilidades;

public class utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_EVENTO="evento";
    public static final String TICKET="ticket";
    public static final String FECHA_INICIO="fecha_inicio";
    public static final String HORA_INICIO="hora_inicio";
    public static final String RUTA_IMAGEN1="ruta_imagen1";
    public static final String RUTA_IMAGEN2="ruta_imagen2";
    public static final String ID_SITIO="id_sitio";
    public static final String NOMBRE_SITIO="nombre_sitio";
    public static final String ITEM="item";
    public static final String DESCRIPCION_TRABAJO="descripcion_trabajo";
    public static final String PRECIO="precio";

    public static final String CREAR_TABLA_EVENTO="CREATE TABLE " +
            ""+TABLA_EVENTO+" ("+TICKET+" " +
            "TEXT, "+FECHA_INICIO+" TEXT,"+HORA_INICIO+" TEXT, "+RUTA_IMAGEN1+"TEXT,"+RUTA_IMAGEN2+"TEXT" +
            ","+ID_SITIO+"TEXT,"+NOMBRE_SITIO+"TEXT,"+ITEM+"TEXT,"+DESCRIPCION_TRABAJO+"TEXT,"+PRECIO+"TEXT)";
}
