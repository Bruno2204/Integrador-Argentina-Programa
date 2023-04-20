package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Partido> resultados = new ArrayList<Partido>();

        try (BufferedReader brResultados = new BufferedReader(new FileReader("src/main/resources/resultados.csv")) ;
             BufferedReader brPronosticos = new BufferedReader(new FileReader("src/main/resources/pronosticos.csv"))) {

            List<String> fileResultados = brResultados.lines().toList();
            AddPartidos(resultados,fileResultados);

            List<String> filePronosticos = brPronosticos.lines().toList();
            AddPronosticos(filePronosticos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ObtenerPuntaje(resultados);
        }
    }

    public static void AddPartidos(List<Partido> lista,List<String> file) {
        for (int i = 1 ; i < file.size() ; i++) {
            String[] datos = file.get(i).split(",");
            Equipo equipo1 = Equipo.GetEquipo(datos[0]);
            int goles1 = Integer.parseInt(datos[1]);
            int goles2 = Integer.parseInt(datos[2]);
            Equipo equipo2 = Equipo.GetEquipo(datos[3]);
            lista.add(new Partido(equipo1,goles1,goles2,equipo2));
        }
    }

    public static void AddPronosticos(List<String> file) {
        for (int i = 1 ; i < file.size() ; i++) {
            String[] datos = file.get(i).split(",");
            Equipo equipo1 = Equipo.GetEquipo(datos[1]);
            int ganador = 0;
            if (datos[2].contains("x")){
                ganador = 1;
            } else if (datos[4].contains("x")) {
                ganador = 2;
            }
            Equipo equipo2 = Equipo.GetEquipo(datos[5]);
            Participante.GetParticipante(datos[0]).pronosticos
                    .add(new Pronostico(equipo1,ganador,equipo2));
        }
    }
    /*
    public static void ObtenerPuntaje(List<Partido> resultados) {
        int puntaje = 0;

        for (int i = 0 ; i < resultados.size() ; i++) {
            if (pronosticos.get(i).getGanador() == resultados.get(i).getGanador()) {
                puntaje++;
            }
        }
        System.out.println("Tu puntaje es: " + puntaje);
    }    */
    public static void ObtenerPuntaje(List<Partido> resultados) {
        Participante.participantes.forEach((key,participante)->{
            int puntaje = 0;

            for (int i = 0 ; i < participante.pronosticos.size() ; i++) {
                if (participante.pronosticos.get(i).getGanador() == resultados.get(i).getGanador()) {
                    puntaje++;
                }
            }
            System.out.println(key +": " + puntaje);
        });

    }
}