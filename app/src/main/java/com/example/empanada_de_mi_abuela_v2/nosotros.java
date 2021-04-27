package com.example.empanada_de_mi_abuela_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class nosotros extends AppCompatActivity {

    private Button ButtonHacerPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);

        ButtonHacerPedido = findViewById(R.id.btnPedido);

        //icono en el action Bar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //Funcion del Button

        ButtonHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(nosotros.this,Pedido.class));

            }
        });
    }
}
