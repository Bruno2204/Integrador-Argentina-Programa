package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static HashMap<String,Partido> resultados = new HashMap<String,Partido>();
    public static HashMap<String,Integer> fases = new HashMap<String,Integer>();
    public static HashMap<String,Integer> rondas = new HashMap<String,Integer>();
    public static int puntosXAcierto = 1;

    public static void main(String[] args) {

        try {

            //List<String> fileResultados = brResultados.lines().toList();
            //List<String> filePronosticos = brPronosticos.lines().toList();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquiles","root","password");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from resultados");
            AddPartidos(resultados,rs);

            rs = stmt.executeQuery("select * from pronosticos");
            AddPronosticos(rs);

            con.close();
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Cuántos puntos desea por acierto?");
            puntosXAcierto = scanner.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MostrarPuntajes();
        }
    }

/*    public static void AddPartidos(HashMap<String,Partido> lista,List<String> file) {
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
    }*/
    public static void AddPartidos(HashMap<String,Partido> lista,ResultSet file) {
        try{
            while (file.next()) {
                int fase = file.getInt(1);
                int ronda = file.getInt(2);
                Equipo equipo1 = Equipo.GetEquipo(file.getString(3));
                int goles1 = file.getInt(4);
                int goles2 = file.getInt(5);
                Equipo equipo2 = Equipo.GetEquipo(file.getString(6));
                Partido partido = new Partido(fase,ronda,equipo1,goles1,goles2,equipo2);
                lista.put(file.getString(3)+file.getString(6),partido);

                if (!rondas.containsKey(file.getString(2))){

                    rondas.put(file.getString(2),1);
                    if (!fases.containsKey(file.getString(1))){
                        fases.put(file.getString(1),1);
                        }else {
                        fases.put(file.getString(1),fases.get(file.getString(1))+1);
                        }
                }else {
                    rondas.put(file.getString(2),rondas.get(file.getString(2))+1);
                    }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

/*public static void AddPronosticos(List<String> file) {
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
    }*/

    public static void AddPronosticos(ResultSet file) {
        try {
            while (file.next()) {
                int ganador = 0;
                if (file.getString(3).contains("x")){
                    ganador = 1;
                } else if (file.getString(5).contains("x")) {
                    ganador = 2;
                }
                Partido partido = resultados.get(file.getString(2)+file.getString(6));
                Participante.GetParticipante(file.getString(1)).pronosticos
                        .add(new Pronostico(ganador,partido));
            }
        } catch (Exception e){
            e.printStackTrace();
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