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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton ButtonProducto;
    private ImageButton ButtonNosotros;
    private ImageButton ButtonContacto;
    private ImageButton ButtonHazTupedido;
    private ProgressDialog mDialog;
    private  static final String STRING_PREFERENCES = "elegadodemiabuela";
    private String facebookId   ="fb://page/112064530564274";
    private String facebookUrl  ="https://www.facebook.com/El-sabor-del-legado-112064530564274";

    final private int REQUEST_CODE_ASK_PERMISSION = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //icono en el action Bar

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        ButtonProducto = findViewById(R.id.ButtonProducto);
        ButtonNosotros = findViewById(R.id.ButtonNosotros);
        ButtonContacto = findViewById(R.id.ButtonContacto);
        ButtonHazTupedido = findViewById(R.id.ButtonHacerpedido);
        mDialog = new ProgressDialog(this,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth);

        //solicitar permisos
        solicitarpermisos();

        //Funcion de las ImagenButton

        ButtonProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,producto.class));

            }
        });

        ButtonNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,nosotros.class));

            }
        });

        ButtonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,contacto.class));

            }
        });

        ButtonHazTupedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,Pedido.class));

            }
        });

    }
    // metodo para mostrar y ocultar menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //metodo para asignar funciones en menu

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id ==  R.id.miFacebook){

            CargarRedesSociales(facebookId, facebookUrl);

        }else if( id == R.id.miWhatsapp){
            String url = "https://api.whatsapp.com/send?phone=573125297585";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if( id == R.id.miInstagram){
            String url = "https://www.instagram.com/empanadas_ellegadodemiabuela/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        }else if( id == R.id.mitel) {

            Intent llamar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3125297585"));

            if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Te estamos poniendo en contacto. Espere por favor..!!", Toast.LENGTH_LONG).show();

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
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                        Manifest.permission.CALL_PHONE },1);

                Toast.makeText(MainActivity.this, "Debes Aceptar los permisos para realizar la llamada", Toast.LENGTH_LONG).show();

            }

        }return super.onOptionsItemSelected(item);
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
        int permisointernet = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.INTERNET);
        int permisocel = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE);

        int permisolocation= ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION);

        if(permisointernet!=PackageManager.PERMISSION_GRANTED || permisocel!= PackageManager.PERMISSION_GRANTED || permisolocation!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSION);

            }
        }
    }

    private void CargarRedesSociales(String urlId, String urlUrl) {

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlId)));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlUrl)));
        }
    }



}
