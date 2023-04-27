package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Participante {

    public static HashMap<String, Participante> participantes = new HashMap<>();
    private String nombre;
    private int puntaje;

    private int aciertos;

    private List<Pronostico> pronosticos = new ArrayList<>();

    public Participante(String nombre) {
        this.setNombre(nombre);
    }

    public static Participante GetParticipante(String key) {
        if (!participantes.containsKey(key)) {
            participantes.put(key,new Participante(key));
        }
        return participantes.get(key);
    }

    public void ObtenerPuntaje() {
        setPuntaje(0);
        setAciertos(0);
        int rondasAcertadas = 0;
        int fasesAcertadas = 0;
        int partidosPorRonda;
        int rondasPorFase;
        int partidosAcertadosRondaActual = 0;
        int rondasAcertadasFaseActual = 0;
        int rondaLoopeada = 1;
        int faseLoopeada = 1;
        for (int i = 0 ; i < this.getPronosticos().size() ; i++) {
            Pronostico pronostico = getPronosticos().get(i);
            int faseActual = pronostico.getPartido().getFase();
            int rondaActual = pronostico.getPartido().getRonda();
            partidosPorRonda = Main.rondas.get(Integer.toString(rondaActual));
            rondasPorFase = Main.fases.get(Integer.toString(faseActual));
            if (pronostico.isAcierto()) {
                setAciertos(getAciertos() + 1);
                partidosAcertadosRondaActual++;
                if (rondaActual != rondaLoopeada) {
                    rondaLoopeada = rondaActual;
                    partidosAcertadosRondaActual = 1;
                }
                if (partidosAcertadosRondaActual == partidosPorRonda) {
                    rondasAcertadasFaseActual++;
                    rondasAcertadas++;
                    if (faseActual != faseLoopeada) {
                        faseLoopeada = faseActual;
                        rondasAcertadasFaseActual = 1;
                    }
                    if (rondasAcertadasFaseActual == rondasPorFase) {
                        fasesAcertadas++;
                    }
                }
            }
        }
        setPuntaje(aciertos * Main.puntosPorAcierto + rondasAcertadas * 100 + fasesAcertadas * 1000);
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*public void ObtenerPuntajee() {
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
                System.out.println("AciertosRonda:"+aciertosRonda+" rondaLen:"+ rondaLen+" rondaLoopeando"+rondaActual+" rondaActual:"+pronostico.partido.ronda);
                if (aciertosRonda == rondaLen && rondaActual == pronostico.partido.ronda){
                    rondaEntera++;
                    aciertosFase++;
                    System.out.println("AciertosFase:"+aciertosFase+" FaseLen:"+ faseLen+" FaseLoopeando"+faseActual+" FaseActual:"+pronostico.partido.fase);
                    if (aciertosFase == faseLen && faseActual == pronostico.partido.fase){
                        faseEntera++;
                        aciertosFase=0;
                    } else if (!(faseActual == pronostico.partido.fase)){
                        aciertosFase = 1;
                        faseActual = pronostico.partido.fase;
                        faseLen = Main.fases.get(Integer.toString(pronostico.partido.fase));
                    }
                } else if (!(rondaActual == pronostico.partido.ronda)){
                    System.out.println("AciertosFase:"+aciertosFase+" FaseLen:"+ faseLen+" FaseLoopeando"+faseActual+" FaseActual:"+pronostico.partido.fase);
                    aciertosRonda = 1;
                    rondaActual = pronostico.partido.ronda;
                    rondaLen = Main.rondas.get(Integer.toString(pronostico.partido.ronda));
                }
            }
        }
        puntaje = aciertos + rondaEntera*10 + faseEntera*100;
    }*/
}

