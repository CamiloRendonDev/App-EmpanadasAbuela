package com.example.empanada_de_mi_abuela_v2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.empanada_de_mi_abuela_v2.model.Elegido;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.UUID;

public class datospersonales extends AppCompatActivity implements View.OnClickListener {

    private EditText ET_nombre,  ET_celular,ET_Ubicacion;
    private Button btnSolicitar;
    private TextView ET_Hora, ET_Fecha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private int dia,mes,ano,hora,minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datospersonales);

        //icono en el action Bar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        ET_nombre = findViewById(R.id.nombre);
        ET_celular = findViewById(R.id.celular);
        ET_Ubicacion=findViewById(R.id.direccion);

        btnSolicitar = findViewById(R.id.btnSolicitarP);

        ET_Hora = findViewById(R.id.Hora);
        ET_Fecha= findViewById(R.id.Fecha);

        ET_Hora.setOnClickListener(this);
        ET_Fecha.setOnClickListener(this);

        //icon bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Firebase Init
        inicializarFirebase();

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Solicitar();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v==ET_Fecha){

            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog    datePickerDialog  = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ET_Fecha.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                }
            },ano,mes,dia);
            datePickerDialog.show();
        }
        if(v==ET_Hora){

            final Calendar c = Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Dialog,new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    String AM_PM;
                    if(hourOfDay < 12) {
                        AM_PM = "am";
                    } else {
                        AM_PM = "pm";
                    }

                    ET_Hora.setText(hourOfDay+":"+minute+AM_PM);

                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }

    private void Solicitar() {

        String contenidoNombre = ET_nombre.getText().toString().trim();
        String contenidoCelular = ET_celular.getText().toString().trim();
        String contenidoDireccion = ET_Ubicacion.getText().toString().trim();
        String contenidoFecha = ET_Fecha.getText().toString().trim();
        String contenidoHora = ET_Hora.getText().toString().trim();

        if (contenidoNombre.isEmpty()){
            ET_nombre.setError("Ingresa Tu nombre");
            ET_nombre.requestFocus();
        }
        else if(contenidoCelular.isEmpty()){
            ET_celular.setError("Ingresa tu numero celular");
            ET_celular.requestFocus();
        }
        else if (ET_celular.length()<10 || ET_celular.length()>=11){
            ET_celular.setError("Numero de cel Invalido");
            ET_celular.requestFocus();
        }
        else if (contenidoDireccion.isEmpty()){
            ET_Ubicacion.setError("Direccion de residencia");
            ET_Ubicacion.requestFocus();
        }
        else if (contenidoFecha.isEmpty()){
            Toast.makeText(this, "Fecha del domicilio", Toast.LENGTH_SHORT).show();
            ET_Fecha.requestFocus();
        }
        else if (contenidoHora.isEmpty()){
            Toast.makeText(this, "Hora del domicilio", Toast.LENGTH_SHORT).show();
            ET_Hora.requestFocus();
        }

        else {
            Elegido p = new Elegido();
            p.setUid(UUID.randomUUID().toString());
            p.setA_Nombre(contenidoNombre);
            p.setB_Celular(contenidoCelular);
            p.setC_Direccion(contenidoDireccion);
            p.setD_Fecha(contenidoFecha);
            p.setE_Hora(contenidoHora);

            databaseReference.child("Pedidos").child(p.getD_Fecha()).child(p.getE_Hora()).setValue(p);

            Devolver_conductorelegido();
        }
    }

    private void Devolver_conductorelegido() {

        btnSolicitar.setEnabled(true);
        limpiarcajas();
        Toast.makeText(this, "En breve te llamaremos para organizar tu domicilio ", Toast.LENGTH_LONG).show();
        startActivity(new Intent(datospersonales.this,MainActivity.class));

        finish();
    }

    private void limpiarcajas() {
        ET_nombre.setText("");
        ET_celular.setText("");
        ET_Ubicacion.setText("");
        ET_Fecha.setText("");
        ET_Hora.setText("");

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }



}