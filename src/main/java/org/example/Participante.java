package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Participante {

    public static HashMap<String, Participante> participantes = new HashMap<String, Participante>();
    private String nombre;

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
}
