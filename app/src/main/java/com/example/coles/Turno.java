package com.example.coles;

public class Turno {
    private Parada paradaInicial;
    private Parada paradaFinal;


    public Turno(Parada paradaInicial, Parada paradaFinal) {
        this.paradaInicial = paradaInicial;
        this.paradaFinal = paradaFinal;
    }


    public Parada getParadaInicial() {
        return paradaInicial;
    }

    public Parada getParadaFinal() {
        return paradaFinal;
    }

    /**
     *
     * @param idParada
     * @param ahora Hora actual
     * @param ordenParadas arreglo que enumera IDs de las paradas que abarca la línea
     * @param offsets referido al arreglo anterior; arreglo que enumera los minutos que se demora en llegar a cada parada.
     * @return [ hora de llegada, minuto de llegada]
     */
    public int[] obtenerProximoArriboAParada(int idParada, Hora ahora, int[] ordenParadas, int[] offsets) {

        // se crean dos "paradas teóricas", una siendo la parada de partida de la unidad y otra la del usuario
        Parada aux = new Parada(-1, paradaInicial.getHora(), paradaInicial.getMinuto());
        Parada objetivo = new Parada(-1, ahora.getHora(), ahora.getMinuto());

        // hallar posición en ordenParadas de la parada inicial y la parada del usuario, guardarla en j
        int j = Carga.posicionEnArreglo(ordenParadas, paradaInicial.getIdParada());
        int j2 = Carga.posicionEnArreglo(ordenParadas, idParada);

        // se suman los minutos desde la hora de salida del bondi hasta la parada del usuario
        while(j != j2){
            if(j == offsets.length-1)
                j = 0;
            else
                j++;
            aux.sumarMinutos(offsets[j]);
        }

        // se obtiene el tiempo de ciclo de la unidad (vuelta completa en el recorrido)
        int minutosEntrePasadas = 0;
        for(int k = 0; k<offsets.length; k++){
            minutosEntrePasadas += offsets[k];
        }

        ProximasLlegadasLista listaLlegadas = ProximasLlegadasLista.getInstance();

        // se suman vueltas hasta llegar a un horario posterior a la hora actual y está dentro de horario
        while(aux.esAnteriorA(objetivo) && aux.esAnteriorA(paradaFinal)){

            // caso excepcional línea 3
            if(aux.getHora() == 24)
                break;

            listaLlegadas.agregarLlegada(new Hora(aux.getHora(), aux.getMinuto()));
            listaLlegadas.incrementarPosActual();
            aux.sumarMinutos(minutosEntrePasadas);
        }

        // guardo respuesta a retornar
        int[] proximaLlegada = {aux.getHora(), aux.getMinuto()};

        // si ninguna parada cumplió el requisito
        if(paradaFinal.esAnteriorA(aux) && !paradaFinal.equals(aux))
            return null;

        // lleno el recyclerview con los horarios completos
        while(aux.esAnteriorA(paradaFinal) && aux.getHora() >= 5){
            listaLlegadas.agregarLlegada(new Hora(aux.getHora(), aux.getMinuto()));
            aux.sumarMinutos(minutosEntrePasadas);
        }

        return proximaLlegada;

    }

}
