package com.example.coles;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Parada implements Comparable<Parada>{
    final int idParada;
    private int hora;
    private int minuto;

    public Parada(int idParada, int hora, int minuto) {
        this.idParada = idParada;
        this.hora = hora;
        this.minuto = minuto;
    }


    @NonNull
    @Override
    public String toString(){
        return String.format(Locale.getDefault(), "%d:%d", hora, minuto);
    }

    @Override
    public int compareTo(Parada parada) {
        if(this.hora > parada.getHora())
            return 1;
        else if(this.minuto > parada.getMinuto())
            return 1;
        else if(parada.getHora() == this.hora && parada.getMinuto() == this.minuto)
            return 0;
        else
            return -1;

    }

    public int getIdParada() {
        return idParada;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public boolean esAnteriorA(Parada p){
        if(this.hora < p.getHora())
            return true;
        else return this.hora == p.getHora() && this.minuto < p.getMinuto();
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

        if(this.hora == 25)
            this.hora = 1;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parada){
            Parada p = (Parada)obj;
            return p.getHora() == this.hora && p.getMinuto() == this.minuto;
        } else {
            return false;
        }
    }
}
