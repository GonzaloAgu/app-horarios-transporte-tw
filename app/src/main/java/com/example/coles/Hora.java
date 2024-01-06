package com.example.coles;

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

    public String getHoraCompleta(){
        return String.format("%d:%02d", hora, minuto);
    }

    @Override
    public int compareTo(Hora hora) {
        if(this.hora < hora.getHora())
            return -1;
        else if(this.hora == hora.getHora()) {
            if (this.minuto < hora.getMinuto())
                return -1;
            if (this.minuto == hora.getMinuto())
                return 0;
            else
                return 1;
        } else
            return 1;
    };
}
