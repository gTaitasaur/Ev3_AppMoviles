package com.example.ev2_app;

import java.io.Serializable;

public class UserModel implements Serializable{

    private String rdFecha, rdHora, rdLab, rdNombre, rdRut, rdDescription;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return rdFecha;
    }
    public String getHora() {
        return rdHora;
    }
    public String getLaboratorio() {
        return rdLab;
    }
    public String getNombre() {
        return rdNombre;
    }
    public String getRut() {
        return rdRut;
    }
    public String getDescription() {
        return rdDescription;
    }

    public void setFecha(String rdFecha) {
        this.rdFecha = rdFecha;
    }

    public void setHora(String rdHora) {
        this.rdHora = rdHora;
    }

    public void setLaboratorio(String rdLaboratorio) {
        this.rdLab = rdLab;
    }

    public void setNombre(String rdNombre) {
        this.rdNombre = rdNombre;
    }
    public void setRut(String rdRut) {
        this.rdRut = rdRut;
    }

    public void setDescription(String rdDescription) {
        this.rdDescription = rdDescription;
    }

}
