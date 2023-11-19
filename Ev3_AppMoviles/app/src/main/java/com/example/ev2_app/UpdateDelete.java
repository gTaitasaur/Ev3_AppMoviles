package com.example.ev2_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateDelete extends AppCompatActivity {

    private UserModel userModel;
    private EditText etFecha, etHora, etNombre, etRut, etDescripcion, etLab;
    private Button actualizar, eliminar;
    private Sqlito databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new Sqlito(this);

        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        etLab = findViewById(R.id.etLab);
        etNombre = findViewById(R.id.etNombre);
        etRut = findViewById(R.id.etRut);
        etDescripcion = findViewById(R.id.etDescripcion);


        actualizar = findViewById(R.id.btnActualizar);
        eliminar = findViewById(R.id.btnEliminar);

        if (userModel != null) {
            etFecha.setText(userModel.getFecha());
            etHora.setText(userModel.getHora());
            etLab.setText(userModel.getLaboratorio());
            etNombre.setText(userModel.getNombre());
            etRut.setText(userModel.getRut());
            etDescripcion.setText(userModel.getDescription());

        }

        actualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseHelper.updateUser(userModel.getId(),
                        etFecha.getText().toString(),
                        etHora.getText().toString(),
                        etLab.getText().toString(),
                        etNombre.getText().toString(),
                        etRut.getText().toString(),
                        etDescripcion.getText().toString());
                Toast.makeText(UpdateDelete.this, "Actualizado! \n Debes volver al inicio.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDelete.this, "Eliminado! \n Debes volver al inicio.", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });
    }
}
