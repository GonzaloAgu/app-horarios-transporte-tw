package com.example.coles;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Hora implements Comparable<Hora>{
    private int hora;
    private int minuto;

    public Hora(int hora, int minuto) {
        if(hora == 0)
            this.hora = 24;
        else
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

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    @NonNull
    @Override
    public String toString(){
        if(hora == 24)
            return String.format(Locale.getDefault(), "%d:%02d", 0, minuto);
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

    public void sumarMinutos(int minutos){
        if(minutos > 59){
            this.hora += minutos / 60;
            this.minuto += minutos % 60;
        } else {
            this.minuto += minutos;
        }
        if(this.minuto > 59){
            this.hora++;
            this.minuto -= 60;
        }

        if(this.hora > 24)
            this.hora -= 24;
    }
}
