package com.stickstudios.peruapptask;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/** consultas a la BD SQLITE */
@Dao
public interface TareaDao {

    @Insert
    void insertar(Tarea tarea);

    @Update
    void actualizar(Tarea tarea);

    @Delete
    void eliminar(Tarea tarea);

    @Query("DELETE FROM tabla_tarea")
    void vaciarTareas();

    @Query("SELECT * FROM tabla_tarea ORDER BY estado ASC")
    LiveData<List<Tarea>> listarTareas();

}
