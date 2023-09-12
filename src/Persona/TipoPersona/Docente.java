package Persona.TipoPersona;

import Persona.Persona;

public class Docente extends Persona {
    private String idCarnet;
    private String materia;
    private String facultad;

    public Docente(String nombre, String genero, int edad, String cedula, String direccion, String idCarnet,
            String telefono, String materia, String facultad) {
        super(nombre, genero, edad, cedula, direccion);
        this.idCarnet = idCarnet;
        this.materia = materia;
        this.facultad = facultad;
    }

    public void Enseñar() {
        System.out.println("Estoy enseñando");
    }

    public void Evaluar() {
        System.out.println("Estoy evaluando");
    }

    @Override
    public String toString() {
        return "Docente{" +
                "idCarnet='" + idCarnet + '\'' +
                ", materia='" + materia + '\'' +
                ", facultad='" + facultad + '\'' +
                '}';
    }
}