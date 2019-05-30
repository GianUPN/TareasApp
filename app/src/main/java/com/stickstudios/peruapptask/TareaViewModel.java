package com.stickstudios.peruapptask;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TareaViewModel extends AndroidViewModel {

    private TareaRepository tareaRepository;
    private LiveData<List<Tarea>> tareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);
        tareaRepository = new TareaRepository(application);
        tareas = tareaRepository.listarTareas();
    }

    public LiveData<List<Tarea>> listarTareas(){
        return tareas;
    }

    public void insertar(Tarea tarea){
        tareaRepository.insertar(tarea);
    }

    public void actualizar(Tarea tarea){
        tareaRepository.actualizar(tarea);
    }

    public void eliminar(Tarea tarea){
        tareaRepository.eliminar(tarea);
    }

    public void vaciartareas(){
        tareaRepository.vaciarTareas();
    }

}
