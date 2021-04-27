package com.example.empanada_de_mi_abuela_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pedido extends AppCompatActivity {

    private EditText ETArracacha;
    private EditText ETArroz;
    private EditText ETPapa;
    private TextView TV_Total;
    private TextView TV_Valor;
    private  TextView TV_Domicilio;
    private Button  btn_calcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ETArracacha = findViewById(R.id.ET_Arracacha);
        ETArroz = findViewById(R.id.ET_Arroz);
        ETPapa = findViewById(R.id.ET_Papa);
        TV_Total = findViewById(R.id.TV_total);
        TV_Valor = findViewById(R.id.TV_Precio);
        TV_Domicilio = findViewById(R.id.TVdomicilio);
        btn_calcular = findViewById(R.id.btnCalcular);

        //icono en el action Bar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sumar();

            }
        });
    }



    @SuppressLint("SetTextI18n")
    private void sumar() {

        String Arracacha = ETArracacha.getText().toString().trim();
        String Arroz = ETArroz.getText().toString().trim();
        String Papa = ETPapa.getText().toString().trim();

        int Suma_Arracacha = Integer.parseInt(Arracacha);
        int Suma_Arroz = Integer.parseInt(Arroz);
        int Suma_Papa = Integer.parseInt(Papa);

        int suma = Suma_Arracacha + Suma_Arroz + Suma_Papa;
        TV_Total.setText(""+suma);
        TV_Domicilio.setText("Recuerda que el valor del domicilio será según tu ubicación");

        if (suma < 12){
            int total=Suma_Arracacha*2700 + Suma_Arroz*2400 + Suma_Papa*2400;
            TV_Valor.setText(total+" $");
        }else{
            int total=Suma_Arracacha*2600 + Suma_Arroz*2300 + Suma_Papa*2300;
            TV_Valor.setText(total+" $");
        }
    }

    public void Hacerpedido(View view){

        String alerta = "ALERTA!... 8 Empanadas minimo para el envio del domicilio";

        if(TV_Total.getText().toString().isEmpty() || TV_Total.getText().toString().equals("0")){

            Toast.makeText(this, "Cuantas Empanas vas a llevar?", Toast.LENGTH_SHORT).show();

        }else if (TV_Total.getText().toString().equals("7")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("6")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("5")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("4")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("3")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("2")) {
            TV_Domicilio.setText(alerta);
        }
        else if (TV_Total.getText().toString().equals("1")) {
            TV_Domicilio.setText(alerta);
        }
        else{
            startActivity(new Intent(Pedido.this,datospersonales.class));
        }



    }








}
