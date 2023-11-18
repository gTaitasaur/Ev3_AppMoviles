package com.example.ev2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MuestaDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muesta_datos);

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