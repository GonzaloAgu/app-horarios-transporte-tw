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
        Hora h = parada.getHorario();
        if(horario.getHora() > h.getHora())
            return 1;
        else if(horario.getMinuto() > h.getMinuto())
            return 1;
        else if(h.getHora() == horario.getHora() && h.getMinuto() == horario.getMinuto())
            return 0;
        else
            return -1;

    }

    public int getIdParada() {
        return idParada;
    }

    public Hora getHorario() {
        return horario;
    }

    @NonNull
    @Override
    public String toString() {
        return horario.toString();
    }


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Parada){
            Parada p = (Parada)obj;
            return p.getHorario().getHora() == horario.getHora() && p.getHorario().getMinuto() == horario.getMinuto();
        } else {
            return false;
        }
    }
}
