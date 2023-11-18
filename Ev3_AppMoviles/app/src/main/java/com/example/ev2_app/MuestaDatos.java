package com.example.ev2_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MuestaDatos extends AppCompatActivity {

    private ListView listView;
    private ArrayList<UserModel> userModelArrayList;
    private RecogeDatos customAdapter;
    private Sqlito databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muesta_datos);

        //      CAMBIA PANTALLA A MOSTRAR DATOS
        Button buttonVolver = findViewById(R.id.button_volver);

        listView = (ListView) findViewById(R.id.muestra_lista);
        databaseHelper = new Sqlito(this);
        userModelArrayList = databaseHelper.getAllUsers();
        customAdapter = new RecogeDatos(this, userModelArrayList);
        listView.setAdapter(customAdapter);



// HARDCODEO: SE REEMPLAZA get(position) por get(0),  TAMBIEN MainActivity.class por Sqlito.class
        //      CAMBIA PANTALLA A MOSTRAR DATOS
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MuestaDatos.this, Sqlito.class);
                intent.putExtra("user", userModelArrayList.get(0));
                startActivity(intent);
            }
        });
    }
}