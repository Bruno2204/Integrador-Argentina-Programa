package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static HashMap<String,Partido> resultados = new HashMap<String,Partido>();
    public static HashMap<String,Integer> fases = new HashMap<String,Integer>();
    public static HashMap<String,Integer> rondas = new HashMap<String,Integer>();

    public static void main(String[] args) {

        try (BufferedReader brResultados = new BufferedReader(new FileReader("src/main/resources/resultados.csv")) ;
             BufferedReader brPronosticos = new BufferedReader(new FileReader("src/main/resources/pronosticos.csv"))) {

            List<String> fileResultados = brResultados.lines().toList();
            AddPartidos(resultados,fileResultados);

            List<String> filePronosticos = brPronosticos.lines().toList();
            AddPronosticos(filePronosticos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MostrarPuntajes();
        }
    }

    public static void AddPartidos(HashMap<String,Partido> lista,List<String> file) {
        for (int i = 1 ; i < file.size() ; i++) {
            String[] datos = file.get(i).split(",");
            int fase = Integer.parseInt(datos[0]);
            int ronda = Integer.parseInt(datos[1]);
            Equipo equipo1 = Equipo.GetEquipo(datos[2]);
            int goles1 = Integer.parseInt(datos[3]);
            int goles2 = Integer.parseInt(datos[4]);
            Equipo equipo2 = Equipo.GetEquipo(datos[5]);
            Partido partido = new Partido(fase,ronda,equipo1,goles1,goles2,equipo2);
            lista.put(datos[2]+datos[5],partido);
            if (!rondas.containsKey(datos[1])){
                rondas.put(datos[1],1);
                if (!fases.containsKey(datos[0])){
                    fases.put(datos[0],1);
                }else {
                    fases.put(datos[0],fases.get(datos[0])+1);
                }
            }else {
                rondas.put(datos[1],rondas.get(datos[1])+1);
            }
        }
    }

    public static void AddPronosticos(List<String> file) {
        for (int i = 1 ; i < file.size() ; i++) {
            String[] datos = file.get(i).split(",");
            int ganador = 0;
            if (datos[2].contains("x")){
                ganador = 1;
            } else if (datos[4].contains("x")) {
                ganador = 2;
            }
            Partido partido = resultados.get(datos[1]+datos[5]);
            Participante.GetParticipante(datos[0]).pronosticos
                    .add(new Pronostico(ganador,partido));
        }
    }
    public static void MostrarPuntajes() {
        Participante.participantes.forEach((key,participante)->{
            participante.ObtenerPuntaje();
            System.out.println(participante.getNombre() +" Puntaje: " + participante.puntaje);
            System.out.println(participante.getNombre() +" Aciertos: " + participante.aciertos);
        });
    }
}