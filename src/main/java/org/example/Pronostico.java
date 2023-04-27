package org.example;

public class Pronostico {

    private int ganador;
    private Partido partido;
    private boolean acierto;

    public Pronostico(int ganador,Partido partido) {
        this.setGanador(ganador);
        this.setPartido(partido);
        this.setAcierto(ganador == partido.getGanador());
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public void setAcierto(boolean acierto) {
        this.acierto = acierto;
    }
}
