package org.example;

public class Pronostico {
    private Equipo equipo1;
    private int ganador;
    private Equipo equipo2;

    public Pronostico(Equipo equipo1,int ganador,Equipo equipo2) {
        this.setEquipo1(equipo1);
        this.setGanador(ganador);
        this.setEquipo2(equipo2);
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }
}
