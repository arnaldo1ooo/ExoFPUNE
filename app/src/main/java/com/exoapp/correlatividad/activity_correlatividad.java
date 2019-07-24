package com.exoapp.correlatividad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exoapp.R;
import com.exoapp.recyclerview.Item;
import com.exoapp.recyclerview.RecyclerViewAdaptador;

import java.util.ArrayList;

public class activity_correlatividad extends AppCompatActivity {

    private RecyclerView recyclerview;
    private RecyclerView.Adapter adaptador;
    private LinearLayoutManager layoutManager;
    private TextView txtTitulo;
    private String carreraseleccionada;
    private String mallaseleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcarrera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Seleccione la malla");


        //Ponemos el titulo
        carreraseleccionada = getIntent().getExtras().getString("carrera");
        mallaseleccionada = getIntent().getExtras().getString("malla");
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);

        txtTitulo.setText(mallaseleccionada + " - " + carreraseleccionada);

        //Se crea el recyclerview y adaptador
        recyclerview = (RecyclerView) findViewById(R.id.rvPrincipal);
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        //Adaptador
        adaptador = new RecyclerViewAdaptador(this, MetodoListItem());

        //OnClick
        ((RecyclerViewAdaptador) adaptador).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Guardo el titulo del item seleccionado
                String tituloitem = MetodoListItem().get(
                        recyclerview.getChildAdapterPosition(view)).getTitulo();
                String malla = "";
                Intent intent;

                switch (tituloitem) {
                    //POLITECNICA FPUNE
                    case "PLAN 2017":
                        malla = tituloitem;
                        break;

                    case "PLAN VIGENTE":
                        malla = tituloitem;
                        break;


                    default:
                        Toast.makeText(activity_correlatividad.this,"No se seleccionó ningún item", Toast.LENGTH_SHORT).show();
                        break;
                }

                //intent = new Intent(activity_corremalla.this, com.exoapp.correlatividad.activity_corremalla.class);
                //intent.putExtra("malla", malla);
                //startActivity(intent);

                //Imprime el titulo del item seleccionado
                /*Toast.makeText(getApplicationContext(), "Selección: " + MetodoListItem().get(
                        recyclerview.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();*/
            }
        });

        recyclerview.setAdapter(adaptador);

        //Linea divisor de RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerview.addItemDecoration(dividerItemDecoration);

    }

    private ArrayList<Item> MetodoListItem(){
        ArrayList<Item> listItems = new ArrayList<>();

        String carreraseleccionada = getIntent().getExtras().getString("carrera");
        switch (carreraseleccionada) {
            case "ANÁLISIS DE SISTEMAS":
                listItems.add(new Item(R.drawable.malla,"kyukuykPLAN 2017","Ciudad: Ciudad del Este"));
                listItems.add(new Item(R.drawable.malla,"PLAN VIGENTE","Ciudad: Ciudad del Este"));
                break;

            case "TURISMO":
                listItems.add(new Item(R.drawable.malla,"IDIOMAS I","Ciudad: Minga Guazú"));
                listItems.add(new Item(R.drawable.malla,"LENGUA GUARANI I","Ciudad: Minga Guazú"));
                break;

            case "INGENIERÍA ELÉCTRICA":
                listItems.add(new Item(R.drawable.malla,"PLAN 2017","Ciudad: Minga Guazú"));
                listItems.add(new Item(R.drawable.malla,"PLAN VIGENTE","Ciudad: Minga Guazú"));
                break;

            default:
                Toast.makeText(activity_correlatividad.this,"La universidad recibida no coincide con ninguno", Toast.LENGTH_SHORT).show();
                break;
        }

        return listItems;
    }
}
