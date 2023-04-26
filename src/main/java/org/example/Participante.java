package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Participante {

    public static HashMap<String, Participante> participantes = new HashMap<String, Participante>();
    private String nombre;
    public int puntaje;
    private int aciertos;

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
        for (int i = 0 ; i < this.pronosticos.size() ; i++) {
            int rondaActual = this.pronosticos.get(i).partido.ronda;
            int rondaLength = 0;
            int rondaAciertos = 0;
            for (int j = 0 ; j < 0 ; j++) {
                
            }
            
            
            if (rondaAciertos == rondaLength){
                puntaje+=2;
            }
            aciertos+=rondaAciertos;
        }

        puntaje+=aciertos;
    }
}
