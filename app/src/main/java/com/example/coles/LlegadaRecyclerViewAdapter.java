package com.example.coles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LlegadaRecyclerViewAdapter extends RecyclerView.Adapter<LlegadaRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Hora> horas;
    int iconoBus = ProximasLlegadasLista.getInstance().getIconoBus();

    public LlegadaRecyclerViewAdapter(Context context, ArrayList<Hora> horas){
        this.context = context;
        this.horas = horas;
    }

    @NonNull
    @Override
    public LlegadaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fila_parada_rv, parent, false);
        return new LlegadaRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LlegadaRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.horaView.setText(horas.get(position).toString());
        holder.imageView.setImageResource(iconoBus);
    }

    @Override
    public int getItemCount() {
        return horas.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView horaView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.busIcono);
            horaView = itemView.findViewById(R.id.hora);
        }
    }
}
