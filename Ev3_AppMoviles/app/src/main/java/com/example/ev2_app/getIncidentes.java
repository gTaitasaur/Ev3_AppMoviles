package com.example.ev2_app;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class getIncidentes extends AppCompatActivity {

    private Button grabar, mostrar;
    private EditText etFecha, etHora, etNombre, etRut, etLab, etDesc;

    Spinner spinnerList = findViewById(R.id.lista_Labs);

    private DatabaseHelper Sqlito;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        grabar = (Button) findViewById(R.id.button_guardar);
        mostrar = (Button) findViewById(R.id.button_mostrarDatos);
        etFecha = (EditText) findViewById(R.id.editTextFecha);
        etHora = (EditText) findViewById(R.id.editTextHora);
        etNombre = (EditText) findViewById(R.id.text_nombre);
        etRut = (EditText) findViewById(R.id.text_rut);
        etDesc = (EditText) findViewById(R.id.text_descripcion);
        String selectedOption = (String) spinnerList.getSelectedItem();

        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addUserDetail
            }
        });


    }

}