package com.example.ev2_app;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.widget.EditText;
import android.text.TextUtils;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ProgressBar progressBar;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isVertical = false;

    private EditText editRUT, editNombre, editDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //      CAMBIA PANTALLA A MOSTRAR DATOS
        Button buttonMostrarDatos = findViewById(R.id.button_mostrarDatos);
        buttonMostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MuestaDatos.class);
                startActivity(intent);
            }
        });

        progressBar = findViewById(R.id.progressBar);
        Button myButton = findViewById(R.id.button_guardar);

        editRUT = findViewById(R.id.text_rut);
        editNombre = findViewById(R.id.text_nombre);
        editDescripcion = findViewById(R.id.text_descripcion);

        //          FUNCIONES CALENDARIO
        //          FUNICONES HORA
        EditText editTextHora = findViewById(R.id.editTextHora);

        Calendar calendario = Calendar.getInstance();

        long ahora = System.currentTimeMillis();
        calendario.setTimeInMillis(ahora);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        String horaStr = String.format("%02d:%02d", hora, minuto);
        editTextHora.setText(horaStr);


//          FUNCIONES FECHA
        EditText editTextFecha = findViewById(R.id.editTextFecha);

        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int anio = calendario.get(Calendar.YEAR);
        String fechaStr = String.format("%02d/%02d/%02d", dia, mes, anio);
        editTextFecha.setText(fechaStr);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFieldsAndSave();
            }
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float xRot = event.values[2];

        int progress = (int) ((10 - xRot) * 10);
        progressBar.setProgress(progress);

        if (xRot <= 0) {
            if (!isVertical) {
                isVertical = true;
                checkFieldsAndSave();
            }
        } else {
            isVertical = false;
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private boolean isRUTValid(String rut) {
        boolean valid = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "").replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                valid = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return valid;
    }

    private void checkFieldsAndSave() {
        String rut = editRUT.getText().toString().trim();
        String nombre = editNombre.getText().toString().trim();
        String descripcion = editDescripcion.getText().toString().trim();

        if (TextUtils.isEmpty(rut) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(descripcion)) {
            showAlert2("Debes llenar todos los campos");
            return;
        }

        if (!isRUTValid(rut)) {
            showAlert2("RUT no valido");
            return;
        }

        showAlert2("Deseas grabar los cambios?");
    }
    
    private void showAlert2(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Advertencia")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

}
