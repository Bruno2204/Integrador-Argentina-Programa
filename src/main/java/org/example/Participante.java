package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Participante {

    public static HashMap<String, Participante> participantes = new HashMap<String, Participante>();
    private String nombre;
    public int puntaje;
    public int aciertos;
    public int rondaEntera;

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public List<Pronostico> pronosticos = new ArrayList<Pronostico>();

    public static Participante GetParticipante(String key) {
        if (!participantes.containsKey(key)) {
            participantes.put(key,new Participante(key));
        }
        return participantes.get(key);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void ObtenerPuntaje() {
        puntaje = 0;
        aciertos = 0;
        int faseEntera = 0;
        int rondaActual = 1;
        int aciertosRonda = 0;
        int rondaLen;
        int faseLen;
        int aciertosFase = 0;
        int faseActual = 1;
        for (int i = 0; i < this.pronosticos.size(); i++) {
            Pronostico pronostico = pronosticos.get(i);
            rondaLen = Main.rondas.get(Integer.toString(pronostico.partido.ronda));
            faseLen = Main.fases.get(Integer.toString(pronostico.partido.fase));
            if (pronostico.acierto) {
                aciertosRonda++;
                aciertos++;
                if (aciertosRonda == rondaLen && rondaActual == pronostico.partido.ronda){
                    rondaEntera++;
                    aciertosFase++;
                    if (aciertosFase == faseLen && faseActual == pronostico.partido.fase){
                        faseEntera++;
                        aciertosFase=0;
                    } else if (!(faseActual == pronostico.partido.fase)){
                        aciertosFase = 1;
                        faseActual = pronostico.partido.fase;
                        faseLen = Main.fases.get(Integer.toString(pronostico.partido.fase));
                    }
                } else if (!(rondaActual == pronostico.partido.ronda)){
                    aciertosRonda = 1;
                    rondaActual = pronostico.partido.ronda;
                    rondaLen = Main.rondas.get(Integer.toString(pronostico.partido.ronda));
                }
            }
        }
        puntaje = aciertos + rondaEntera*10 + faseEntera*100;
    }
}

