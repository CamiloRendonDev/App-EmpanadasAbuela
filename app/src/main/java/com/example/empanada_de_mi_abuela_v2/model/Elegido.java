package com.example.empanada_de_mi_abuela_v2.model;

public class Elegido {

    private String uid;
    private String a_Nombre;
    private String b_Celular;
    private String c_Direccion;
    private String d_Fecha;
    private String e_Hora;

    public Elegido() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getA_Nombre() {
        return a_Nombre;
    }

    public void setA_Nombre(String a_nombre) { a_Nombre = a_nombre; }


    public String getB_Celular() {
        return b_Celular;
    }

    public void setB_Celular(String b_celular) { b_Celular = b_celular; }


    public String getC_Direccion() {
        return c_Direccion;
    }

    public void setC_Direccion(String c_direccion) {c_Direccion = c_direccion;}


    public String getD_Fecha() {
        return d_Fecha;
    }

    public void setD_Fecha(String d_fecha) {
        d_Fecha = d_fecha;
    }


    public String getE_Hora() {
        return e_Hora;
    }

    public void setE_Hora(String e_hora) {
        e_Hora = e_hora;
    }

    @Override
    public String toString() {
        return a_Nombre;
    }
}
