package com.example.ev2_app;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateDelete extends AppCompatActivity {

    private UserModel userModel;
    private EditText etFecha, etHora, etNombre, etRut, etDescripcion, etLab;
    private Button actualizar, eliminar;
    private Sqlito databaseHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new Sqlito(this);

        etFecha = (EditText) findViewById(R.id.editTextFecha);
        etHora = (EditText) findViewById(R.id.editTextHora);
        etNombre = (EditText) findViewById(R.id.text_nombre);
        etRut = (EditText) findViewById(R.id.text_rut);
        etDescripcion = (EditText) findViewById(R.id.text_descripcion);
        etLab = (EditText) findViewById(R.id.text_nombre);

        etFecha.setText(userModel.getFecha());
        etNombre.setText(userModel.getNombre());
        etRut.setText(userModel.getRut());
        etDescripcion.setText(userModel.getDescription());
        etLab.setText(userModel.getLaboratorio());

        actualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseHelper.updateUser(userModel.getId(),etFecha.getText().toString(),etHora.getText().toString(),etNombre.getText().toString(), etRut.getText().toString(),etLab.getText().toString(), etDescripcion.getText().toString());
                Toast.makeText(UpdateDelete.this, "Actualizado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDelete.this, MuestaDatos.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDelete.this, "ELIMINADO!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDelete.this, MuestaDatos.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



    }

}
