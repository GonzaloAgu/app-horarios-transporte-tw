package com.example.coles;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Parada implements Comparable<Parada>{
    final int idParada;
    private Hora horario;

    public Parada(int idParada, int hora, int minuto) {
        this.idParada = idParada;
        horario = new Hora(hora, minuto);
    }

    @Override
    public int compareTo(Parada parada) {
        if(getHora() > parada.getHora())
            return 1;
        else if(getMinuto() > parada.getMinuto())
            return 1;
        else if(parada.getHora() == getHora() && parada.getMinuto() == getMinuto())
            return 0;
        else
            return -1;

    }

    public int getIdParada() {
        return idParada;
    }

    public int getHora() {
        return horario.getHora();
    }

    public int getMinuto() {
        return horario.getMinuto();
    }

    public Hora getHorario() {
        return horario;
    }

    @NonNull
    @Override
    public String toString() {
        return horario.toString();
    }

    public boolean esAnteriorA(Parada p){
        int estaHora = getHora();
        if(p.getHora() == 0)
            estaHora = 24;

        if(estaHora < p.getHora())
            return true;
        else return estaHora == p.getHora() && getMinuto() < p.getMinuto();
    }


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parada){
            Parada p = (Parada)obj;
            return p.getHora() == getHora() && p.getMinuto() == getMinuto();
        } else {
            return false;
        }
    }
}
