package com.example.ev2_app;

import java.io.Serializable;

public class UserModel implements Serializable{

    private String fecha, hora, laboratorio, nombre, description;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }
    public String getLaboratorio() {
        return laboratorio;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescription() {
        return description;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
