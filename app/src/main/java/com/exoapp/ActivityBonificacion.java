package com.exoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.exoapp.capturapantalla.ScreenshotType;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;

public class ActivityBonificacion extends AppCompatActivity {

    TextView etBonificacion;
    TextView tvTitulo2;
    TextView tvTituloNota2;
    TextView tvNota2;
    TextView tvTituloNota3;
    TextView tvNota3;
    TextView tvTituloNota4;
    TextView tvNota4;
    TextView tvTituloNota5;
    TextView tvNota5;
    Button btnCalcular;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    CardView cv5;
    private AdView AdView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonificacion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bonificación total");

        etBonificacion = (TextView) findViewById(R.id.etBonificacion);
        tvTitulo2 = (TextView) findViewById(R.id.tvTitulo2);
        tvTituloNota2 = (TextView) findViewById(R.id.tvTituloNota2);
        tvNota2 = (TextView) findViewById(R.id.tvNota2);
        tvTituloNota3 = (TextView) findViewById(R.id.tvTituloNota3);
        tvNota3 = (TextView) findViewById(R.id.tvNota3);
        tvTituloNota4 = (TextView) findViewById(R.id.tvTituloNota4);
        tvNota4 = (TextView) findViewById(R.id.tvNota4);
        tvTituloNota5 = (TextView) findViewById(R.id.tvTituloNota5);
        tvNota5 = (TextView) findViewById(R.id.tvNota5);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        cv2 = (CardView) findViewById(R.id.cardView2);
        cv3 = (CardView) findViewById(R.id.cardView3);
        cv4 = (CardView) findViewById(R.id.cardView4);
        cv5 = (CardView) findViewById(R.id.cardView5);

        VisibilidadObjetos("INVISIBLE");
        MetodoBanner();

        try {
            String BonificacionRecibido = getIntent().getExtras().getString("bonificacion");
            Log.d("Boni", "La bonificacion recibida es: " + BonificacionRecibido + "");
            etBonificacion.setText(BonificacionRecibido);
        } catch (Exception e) {
            Log.d("Error trycatch ", "No se recibio ninguna bonificacion " + e);
        }


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OcultarTeclado(v);
                Calcular();
            }
        });

    }


    //Crear menu en action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_principal, menu);
        return true;
    }

    //Al usar el action overflow
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.escala) {
            Log.d("Opciones", "Abriendo escala");
            Intent i = new Intent(this, ActivityEscala.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.politicas) {
            Log.d("Opciones", "Abriendo politicas de privacidad");
            Intent i = new Intent(this, ActivityPoliticasPrivacidad.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.acercade) {
            Log.d("Opciones", "Abriendo acerca de");
            Intent i = new Intent(this, ActivityAcercade.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void OcultarTeclado(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void VisibilidadObjetos(String valor) {
        int valoor = 0;
        if (valor == "VISIBLE") {
            valoor = View.VISIBLE;
        }
        else if (valor == "INVISIBLE") {
            valoor = View.INVISIBLE;
        }
        tvTitulo2.setVisibility(valoor);
        tvTituloNota2.setVisibility(valoor);
        tvNota2.setVisibility(valoor);
        tvTituloNota3.setVisibility(valoor);
        tvNota3.setVisibility(valoor);
        tvTituloNota4.setVisibility(valoor);
        tvNota4.setVisibility(valoor);
        tvTituloNota5.setVisibility(valoor);
        tvNota5.setVisibility(valoor);
        cv2.setVisibility(valoor);
        cv3.setVisibility(valoor);
        cv4.setVisibility(valoor);
        cv5.setVisibility(valoor);
    }


    public void Calcular() {
        if (etBonificacion.getText().toString().equals("")) {//Si es vacio
            etBonificacion.setText("0");
        }
            DecimalFormat df = new DecimalFormat("#.###");
            DecimalFormat soloentero = new DecimalFormat("#");
            double bonmax = 40;
            double TotalFinal = 60;
            double bonificacion = Double.parseDouble(etBonificacion.getText().toString());

            if (bonificacion > bonmax || bonificacion < 0) { //Si bonificacion es mayor a bonificacion maxima
                Toast.makeText(this, "La bonificación no puede ser mayor a " + soloentero.format(bonmax) + " Puntos", Toast.LENGTH_LONG).show();
            } else {
                VisibilidadObjetos("VISIBLE");

                double Minpara2 = 60;
                double Minpara3 = 70;
                double Minpara4 = 81;
                double Minpara5 = 94;
                double Bonif = Double.parseDouble(etBonificacion.getText().toString());


                double Nota2 = Minpara2 - Bonif;
                if (Nota2 > TotalFinal) {
                    tvTituloNota2.setTextColor(Color.RED);
                    tvNota2.setTextColor(Color.RED);
                }else{
                    tvTituloNota2.setTextColor(Color.WHITE);
                    tvNota2.setTextColor(Color.WHITE);
                }
                double Nota2Porc = (Nota2 * 100) / TotalFinal;
                tvNota2.setText(df.format(Nota2) + " puntos de " + soloentero.format(TotalFinal) + " (" + df.format(Nota2Porc) + "% de 100%)");

                double Nota3 = Minpara3 - Bonif;
                if (Nota3 > TotalFinal) {
                    tvTituloNota3.setTextColor(Color.RED);
                    tvNota3.setTextColor(Color.RED);
                }else{
                    tvTituloNota3.setTextColor(Color.WHITE);
                    tvNota3.setTextColor(Color.WHITE);
                }
                double Nota3Porc = (Nota3 * 100) / TotalFinal;
                tvNota3.setText(df.format(Nota3) + " puntos de " + soloentero.format(TotalFinal) + " (" + df.format(Nota3Porc) + "% de 100%)");

                double Nota4 = Minpara4 - Bonif;
                if (Nota4 > TotalFinal) {
                    tvTituloNota4.setTextColor(Color.RED);
                    tvNota4.setTextColor(Color.RED);
                }else{
                    tvTituloNota4.setTextColor(Color.WHITE);
                    tvNota4.setTextColor(Color.WHITE);
                }
                double Nota4Porc = (Nota4 * 100) / TotalFinal;
                tvNota4.setText(df.format(Nota4) + " puntos de " + soloentero.format(TotalFinal) + " (" + df.format(Nota4Porc) + "% de 100%)");

                double Nota5 = Minpara5 - Bonif;
                if (Nota5 > TotalFinal) {
                    tvTituloNota5.setTextColor(Color.RED);
                    tvNota5.setTextColor(Color.RED);
                }else{
                    tvTituloNota5.setTextColor(Color.WHITE);
                    tvNota5.setTextColor(Color.WHITE);
                }
                double Nota5Porc = (Nota5 * 100) / TotalFinal;
                tvNota5.setText(df.format(Nota5) + " puntos de " + soloentero.format(TotalFinal) + " (" + df.format(Nota5Porc) + "% de 100%)");
            }
    }



    private void MetodoBanner() {
        AdView1 = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView1.loadAd(adRequest);
        AdView1.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Código a ejecutar cuando un anuncio termina de cargarse.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Código a ejecutar cuando falla una solicitud de anuncio.
            }

            @Override
            public void onAdOpened() {
                // Código que se ejecutará cuando un anuncio abra una
                // superposición que cubre la pantalla.
            }

            @Override
            public void onAdClicked() {
                // Código que se ejecutará cuando el usuario
                // haga clic en un anuncio.
            }

            @Override
            public void onAdLeftApplication() {
                // Código a ejecutar cuando el usuario
                // ha abandonado la aplicación.
            }

            @Override
            public void onAdClosed() {
                // Código a ejecutar cuando el usuario está a punto de regresar
                // a la aplicación después de pulsar en un anuncio.
            }
        });
    }
}