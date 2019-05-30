package com.stickstudios.peruapptask;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TareaRepository {

    private TareaDao tareaDao;
    private LiveData<List<Tarea>> tareas;

    public TareaRepository(Application application){
        TareaDataBase dataBase = TareaDataBase.getInstance(application);
        tareaDao = dataBase.tareaDao();
        tareas = tareaDao.listarTareas();
    }

    public void insertar (Tarea tarea){
        new InsertarTareaAsyncTask(tareaDao).execute(tarea);
    }

    public void actualizar (Tarea tarea){
        new ActualizarTareaAsyncTask(tareaDao).execute(tarea);
    }

    public void eliminar (Tarea tarea){
        new EliminarTareaAsyncTask(tareaDao).execute(tarea);
    }

    public void vaciarTareas (){
        new VaciarTareasAsyncTask(tareaDao).execute();
    }

    public LiveData<List<Tarea>> listarTareas (){
        return tareas;
    }

    private static class InsertarTareaAsyncTask extends AsyncTask<Tarea,Void,Void>{

        private TareaDao tareaDao;

        private InsertarTareaAsyncTask(TareaDao tareaDao){
            this.tareaDao = tareaDao;
        }

        @Override
        protected Void doInBackground(Tarea... tareas) {
            tareaDao.insertar(tareas[0]);
            return null;
        }
    }

    private static class ActualizarTareaAsyncTask extends AsyncTask<Tarea,Void,Void>{

        private TareaDao tareaDao;

        private ActualizarTareaAsyncTask(TareaDao tareaDao){
            this.tareaDao = tareaDao;
        }

        @Override
        protected Void doInBackground(Tarea... tareas) {
            tareaDao.actualizar(tareas[0]);
            return null;
        }
    }

    private static class EliminarTareaAsyncTask extends AsyncTask<Tarea,Void,Void>{

        private TareaDao tareaDao;

        private EliminarTareaAsyncTask(TareaDao tareaDao){
            this.tareaDao = tareaDao;
        }

        @Override
        protected Void doInBackground(Tarea... tareas) {
            tareaDao.eliminar(tareas[0]);
            return null;
        }
    }

    private static class VaciarTareasAsyncTask extends AsyncTask<Void,Void,Void>{

        private TareaDao tareaDao;

        private VaciarTareasAsyncTask(TareaDao tareaDao){
            this.tareaDao = tareaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tareaDao.vaciarTareas();
            return null;
        }
    }

}
