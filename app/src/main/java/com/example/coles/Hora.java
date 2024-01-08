package com.example.coles;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Hora implements Comparable<Hora>{
    private int hora;
    private int minuto;

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    @NonNull
    @Override
    public String toString(){
        return String.format(Locale.getDefault(), "%d:%02d", hora, minuto);
    }

    @Override
    public int compareTo(Hora hora) {
        if(this.hora < hora.getHora())
            return -1;
        else if(this.hora == hora.getHora()) {
            return Integer.compare(this.minuto, hora.getMinuto());
        } else
            return 1;
    }
}
