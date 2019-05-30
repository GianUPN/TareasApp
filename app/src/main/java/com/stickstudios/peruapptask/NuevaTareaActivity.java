package com.stickstudios.peruapptask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevaTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        final EditText titulo = findViewById(R.id.edt_titulo);
        final EditText descripcion = findViewById(R.id.edt_descripcion);
        Button guardar = findViewById(R.id.btn_guardar);

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            setTitle("Editar  Tarea");
            titulo.setText(intent.getStringExtra("titulo"));
            descripcion.setText(intent.getStringExtra("descripcion"));
        } else {
            setTitle("Agregar Tarea");
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit = titulo.getText().toString();
                String desc = descripcion.getText().toString();

                if(tit.trim().isEmpty() || desc.trim().isEmpty()){
                    Toast.makeText(NuevaTareaActivity.this,
                            "Agregue un titulo y descripción a su tarea",
                            Toast.LENGTH_SHORT);
                }else{
                    Intent datos = new Intent();
                    int id = getIntent().getIntExtra("id",-1);
                    if(id!=-1){
                        datos.putExtra("id", id);
                    }
                    datos.putExtra("titulo",tit);
                    datos.putExtra("descripcion",desc);
                    setResult(10, datos);
                    finish();
                }
            }
        });

    }
}
