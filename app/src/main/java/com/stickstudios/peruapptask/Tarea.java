package com.stickstudios.peruapptask;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tabla_tarea")
public class Tarea {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;

    private String descripcion;

    private int estado;

    public Tarea(String titulo, String descripcion, int estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEstado() {
        return estado;
    }
}
