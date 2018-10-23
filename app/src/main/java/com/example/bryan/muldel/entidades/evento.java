package com.example.bryan.muldel.entidades;

public class evento {
    private String ticket;
    private String fecha_inicio;

    public evento(String ticket, String fecha_inicio, String hora_inicio, String ruta_imagen1, String ruta_imagen2, Integer id_sitio, String nombre_sitio, Integer item, String descripcion_trabajo, Double precio) {
        this.ticket = ticket;
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.ruta_imagen1 = ruta_imagen1;
        this.ruta_imagen2 = ruta_imagen2;
        this.id_sitio = id_sitio;
        this.nombre_sitio = nombre_sitio;
        this.item = item;
        this.descripcion_trabajo = descripcion_trabajo;
        this.precio = precio;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getRuta_imagen1() {
        return ruta_imagen1;
    }

    public void setRuta_imagen1(String ruta_imagen1) {
        this.ruta_imagen1 = ruta_imagen1;
    }

    public String getRuta_imagen2() {
        return ruta_imagen2;
    }

    public void setRuta_imagen2(String ruta_imagen2) {
        this.ruta_imagen2 = ruta_imagen2;
    }

    public Integer getId_sitio() {
        return id_sitio;
    }

    public void setId_sitio(Integer id_sitio) {
        this.id_sitio = id_sitio;
    }

    public String getNombre_sitio() {
        return nombre_sitio;
    }

    public void setNombre_sitio(String nombre_sitio) {
        this.nombre_sitio = nombre_sitio;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getDescripcion_trabajo() {
        return descripcion_trabajo;
    }

    public void setDescripcion_trabajo(String descripcion_trabajo) {
        this.descripcion_trabajo = descripcion_trabajo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    private String hora_inicio;
    private String ruta_imagen1;
    private String ruta_imagen2;
    private Integer id_sitio;
    private String nombre_sitio;
    private Integer item;
    private String  descripcion_trabajo;
    private Double precio;



}

