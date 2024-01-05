package com.example.coles;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;

public class ProximasLlegadasLista {
    private static ProximasLlegadasLista instance = new ProximasLlegadasLista();
    ArrayList<Hora> llegadas = new ArrayList<>();
    private int posActual = 0;

    private final int iconoBus = R.drawable.ic_baseline_directions_bus_24;

    private ProximasLlegadasLista(){};

    public static ProximasLlegadasLista getInstance() {
        return instance;
    }

    public ArrayList<Hora> getLlegadas() {
        return llegadas;
    }

    public void agregarLlegada(Hora l){
        llegadas.add(l);
    }

    public void ordenarLista(){
        llegadas.sort(new Comparator<Hora>() {
            @Override
            public int compare(Hora h1, Hora h2) {
                if(h1.getHora() < h2.getHora())
                    return -1;
                else if(h1.getHora() == h2.getHora()) {
                    if (h1.getMinuto() < h2.getMinuto())
                        return -1;
                    else if ((h1.getMinuto() == h2.getMinuto()))
                        return 0;
                    else
                        return 1;
                } else
                    return 1;
            }
        });
    }

    public void limpiarLista(){
        llegadas.clear();
        posActual = 0;
    }

    public int getIconoBus() {
        return iconoBus;
    }

    public int getPosActual() {
        return posActual;
    }

    public void sumarAPosActual(int n) {
        this.posActual += n;
    }
}
