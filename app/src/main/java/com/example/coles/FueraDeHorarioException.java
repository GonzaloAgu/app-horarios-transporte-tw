package com.example.coles;


public class FueraDeHorarioException extends Exception {
    private final String message;
    public FueraDeHorarioException(String message) {
        this.message = message;
    }
    public FueraDeHorarioException(){
        message = "Fuera de horario";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
