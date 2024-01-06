package com.example.coles;

import androidx.annotation.Nullable;

public class FueraDeHorarioException extends Exception {
    private Parada ultimaParada;
    private String message;
    public FueraDeHorarioException(Parada ultimaParada) {
        this.ultimaParada = ultimaParada;
        message = "";
    }

    public FueraDeHorarioException(){
        message = "Fuera de horario";
    }

    public Parada getUltimaParada() {
        return ultimaParada;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
