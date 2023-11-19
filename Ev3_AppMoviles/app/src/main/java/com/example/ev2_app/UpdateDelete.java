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
        setContentView(R.layout.activity_update_delete); // Asegúrate de que este es el layout correcto

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new Sqlito(this);

        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        etNombre = findViewById(R.id.etNombre);
        etRut = findViewById(R.id.etRut);
        etDescripcion = findViewById(R.id.etDescripcion);
        etLab = findViewById(R.id.etLab);

        actualizar = findViewById(R.id.btnActualizar);
        eliminar = findViewById(R.id.btnEliminar);

        // Establecer los valores actuales en los EditTexts
        if (userModel != null) {
            etFecha.setText(userModel.getFecha());
            etNombre.setText(userModel.getNombre());
            etRut.setText(userModel.getRut());
            etDescripcion.setText(userModel.getDescription());
            etLab.setText(userModel.getLaboratorio());
        }

        actualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseHelper.updateUser(userModel.getId(),
                        etFecha.getText().toString(),
                        etHora.getText().toString(),
                        etNombre.getText().toString(),
                        etRut.getText().toString(),
                        etLab.getText().toString(),
                        etDescripcion.getText().toString());
                Toast.makeText(UpdateDelete.this, "Actualizado!", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDelete.this, "Eliminado!", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });
    }
}
