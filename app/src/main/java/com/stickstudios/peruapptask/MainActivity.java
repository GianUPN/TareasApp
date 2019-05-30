package com.stickstudios.peruapptask;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TareaViewModel tareaViewModel;
    BottomAppBar  bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottomAppBar);
        RecyclerView recyclerTareas = findViewById(R.id.recycler_tareas);
        FloatingActionButton fab_agregar = findViewById(R.id.fab_agregar);
        recyclerTareas.setLayoutManager(new LinearLayoutManager(this));
        recyclerTareas.setHasFixedSize(true);
        final TareasAdapter adapter = new TareasAdapter();
        recyclerTareas.setAdapter(adapter);


        /** MVVM OBSERVABLE */
        tareaViewModel = ViewModelProviders.of(this)
                .get(TareaViewModel.class);
        tareaViewModel.listarTareas().observe(this, new Observer<List<Tarea>>() {
            @Override
            public void onChanged(@Nullable List<Tarea> tareas) {
                //actualizamos el recycler
                adapter.setList(tareas);
            }
        });

        fab_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevaTareaActivity.class);
                startActivityForResult(i, 3);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                tareaViewModel.eliminar(adapter.getTareaAt(viewHolder.getAdapterPosition()));
            }
        });
        helper.attachToRecyclerView(recyclerTareas);

        adapter.SetOnClickListener(new TareasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea) {
                Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
                intent.putExtra("id", tarea.getId());
                intent.putExtra("titulo", tarea.getTitulo());
                intent.putExtra("descripcion", tarea.getDescripcion());
                intent.putExtra("estado", tarea.getEstado());
                //intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intent, 4);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_appbar, menu);
        return true;
    }



    @Override
    protected void onActivityResult(int request, int result, Intent data){

        if(request == 3 && result == 10){

            String tit = data.getStringExtra("titulo");
            String desc = data.getStringExtra("descripcion");
            int estado = data.getIntExtra("estado",0);
            Tarea tarea = new Tarea(tit,desc,estado);
            tareaViewModel.insertar(tarea);
        }
        if(request == 4 && result == 10){
            int id = data.getIntExtra("id",-1);
            String tit = data.getStringExtra("titulo");
            String desc = data.getStringExtra("descripcion");
            int estado = data.getIntExtra("estado",0);
            Tarea tarea = new Tarea(tit,desc,estado);
            tarea.setId(id);
            if(id!=-1) {
                tareaViewModel.actualizar(tarea);
            }
        }
    }

}
