package org.example;

public class Partido {
    public int ronda;
    private Equipo equipo1;
    private int goles1;
    private int goles2;
    private Equipo equipo2;
    private int ganador = 0;

    public int getGanador() {
        if (getGoles1() > getGoles2()) {
            this.ganador = 1;
        } else if (getGoles2() > getGoles1()) {
            this.ganador = 2;
        }
        return this.ganador;
    }

    public Partido(int ronda,Equipo equipo1,int goles1,int goles2,Equipo equipo2) {
        this.ronda = ronda;
        this.setEquipo1(equipo1);
        this.setGoles1(goles1);
        this.setGoles2(goles2);
        this.setEquipo2(equipo2);

    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public int getGoles1() {
        return goles1;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public int getGoles2() {
        return goles2;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }
}
