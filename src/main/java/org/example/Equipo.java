package org.example;

import java.util.HashMap;

public class Equipo {
    public static HashMap<String, Equipo> equipos = new HashMap<String, Equipo>();
    private String nombre;

    public Equipo(String nombre) {
        this.setNombre(nombre);
    }

    public static Equipo GetEquipo(String key) {
        if (!equipos.containsKey(key)) {
            equipos.put(key,new Equipo(key));
        }
        return equipos.get(key);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
