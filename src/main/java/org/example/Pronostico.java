package org.example;

public class Pronostico {

    public int ganador;
    public Partido partido;
    public boolean acierto;
    public Pronostico(int ganador,Partido partido) {
        this.ganador = ganador;
        this.partido = partido;
        if (ganador == partido.getGanador()) acierto=true;
        else acierto= false;
    }
}
