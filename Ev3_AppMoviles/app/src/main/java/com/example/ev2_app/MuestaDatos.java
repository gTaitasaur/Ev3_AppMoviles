package com.example.ev2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MuestaDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muesta_datos);

        //listView = (ListView) findViewById(R.id.muestra_lista);

        //      CAMBIA PANTALLA A MOSTRAR DATOS
        Button buttonVolver = findViewById(R.id.button_volver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MuestaDatos.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}