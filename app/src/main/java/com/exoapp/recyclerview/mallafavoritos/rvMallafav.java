package com.exoapp.recyclerview.mallafavoritos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exoapp.R;

import java.util.ArrayList;

public class rvMallafav extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private ArrayList<itemMallafav> listItems; //Aca se cargaran los datos
    private View.OnClickListener listener;
    private ImageView ivImagen;



    public rvMallafav(Context context, ArrayList<itemMallafav> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.rv_mallafav, null, false);

        //OnClick
        contentView.setOnClickListener(this);

        System.out.println("Se crea la vista CREATE VIEW HOLDER: " + viewType);
        return new Holder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        itemMallafav item = listItems.get(position);
        Holder Holder = (Holder) holder;
        Holder.ivImagen.setImageResource(item.getImagen());
        Holder.tvTitulo.setText(item.getTitulo());
        Holder.tvDescripcion.setText(item.getDescripcion());

        System.out.println("Se muestra el item   BING VIEW HODLER: " + position);

        Holder.ivImagen.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    //Onclick
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivImagen:
                Log.d("Tag", "fdgsh");
                /*
                1414Boolean compararImagen = ivImagen.getDrawable().equals(R.drawable.star_off);
                if ( compararImagen == true){
                    ivImagen.setImageResource(R.drawable.star_on);
                }else{
                    ivImagen.setImageResource(R.drawable.star_off);
                }*/
                break;
        }

        /*if (listener != null) {
            listener.onClick(view);
        }*/
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvDescripcion;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
