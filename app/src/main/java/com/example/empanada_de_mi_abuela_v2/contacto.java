package com.example.empanada_de_mi_abuela_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class contacto extends AppCompatActivity {

    private ImageButton ButtonFacebook;
    private ImageButton ButtonInstagram;
    private ImageButton ButtonWhatsApp;
    private ImageButton ButtonLlamar;
    private ProgressDialog mDialog;
    private  static final String STRING_PREFERENCES = "elegadodemiabuela";
    private String facebookId   ="fb://page/112064530564274";
    private String facebookUrl  ="https://www.facebook.com/El-sabor-del-legado-112064530564274";

    final private int REQUEST_CODE_ASK_PERMISSION = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        ButtonFacebook = findViewById(R.id.Buttonfacebook);
        ButtonInstagram = findViewById(R.id.Buttoninstagram);
        ButtonWhatsApp = findViewById(R.id.ButtonWhatsapp);
        ButtonLlamar = findViewById(R.id.Buttontelefono);

        mDialog = new ProgressDialog(this,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);

        //solicitar permisos
        solicitarpermisos();

        //icono en el action Bar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        ButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarRedesSociales(facebookId, facebookUrl);
            }
        });

        ButtonInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.instagram.com/empanadas_ellegadodemiabuela/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        ButtonWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://api.whatsapp.com/send?phone=573125297585";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        ButtonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3125297585"));

                if(ActivityCompat.checkSelfPermission(contacto.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(contacto.this, "Te estamos poniendo en contacto. Espere por favor..!!", Toast.LENGTH_LONG).show();

                    Mostrardialogo ();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDialog.dismiss();
                        }
                    }, 5000);

                    mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mDialog.dismiss();
                        }

                    });

                    startActivity(llamar);

                }else {
                    ActivityCompat.requestPermissions(contacto.this,new String[]{
                            Manifest.permission.CALL_PHONE },1);

                    Toast.makeText(contacto.this, "Debes Aceptar los permisos para realizar la llamada", Toast.LENGTH_LONG).show();

                }

            }

        });


    }

    private void CargarRedesSociales(String urlId, String urlUrl) {

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlUrl)));
        }
    }


    private void Mostrardialogo() {

        mDialog.setIcon(R.mipmap.ic_launcher);
        mDialog.setMessage("Espere un momento..");
        mDialog.setTitle("Cars Valet service");
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);

        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        });
        mDialog.show();


    }

    private void solicitarpermisos() {
        int permisointernet = ActivityCompat.checkSelfPermission(contacto.this,Manifest.permission.INTERNET);
        int permisocel = ActivityCompat.checkSelfPermission(contacto.this,Manifest.permission.CALL_PHONE);

        int permisolocation= ActivityCompat.checkSelfPermission(contacto.this,Manifest.permission.ACCESS_FINE_LOCATION);

        if(permisointernet!=PackageManager.PERMISSION_GRANTED || permisocel!= PackageManager.PERMISSION_GRANTED || permisolocation!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSION);

            }
        }
    }

}
