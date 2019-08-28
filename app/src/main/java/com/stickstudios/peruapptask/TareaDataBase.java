package com.stickstudios.peruapptask;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/** BASE DE DATOS SQLITE CON ROOM */
@Database(entities = Tarea.class,version = 1)
public abstract class TareaDataBase extends RoomDatabase {

    private static TareaDataBase instance;

    public abstract TareaDao tareaDao();

    public static synchronized  TareaDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TareaDataBase.class, "tarea_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new LlenarDBAsyncTask(instance).execute();
        }
    };

    //PRUEBA PARA LLENAR RECYCLER :3
    private static class LlenarDBAsyncTask extends AsyncTask<Void,Void,Void>{

        private TareaDao tareaDao;

        private LlenarDBAsyncTask(TareaDataBase db){
            tareaDao = db.tareaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            tareaDao.insertar(new Tarea("Bienvenido! :)","Para eliminar solo desliza esta tarjeta." +
                    "Para modificar solo presiona.",1));
            //tareaDao.insertar(new Tarea("Comprar huevos2","ya se acabaron",1));
            //tareaDao.insertar(new Tarea("Comprar huevos3","ya se acabaron",1));
            //tareaDao.insertar(new Tarea("Comprar huevos4","ya se acabaron",1));

            return null;
        }
    }
}
