package Persona.TipoPersona;
import Persona.Persona;

class Estudiante extends Persona {
    private String idCarnet;
    private String carrera;
    private float promedio;
    private int semestre;

    public Estudiante(String nombre, String genero, int edad, String cedula, String direccion, String idCarnet, String carrera, float promedio, int semestre) {
        super(nombre, genero, edad, cedula, direccion);
        this.idCarnet = idCarnet;
        this.carrera = carrera;
        this.promedio = promedio;
        this.semestre = semestre;
    }

    public void Estudiar() {
        System.out.println("Estoy estudiando");
    }

    public void Aprender() {
        System.out.println("Estoy aprendiendo");
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idCarnet='" + idCarnet + '\'' +
                ", carrera='" + carrera + '\'' +
                ", promedio=" + promedio +
                ", semestre=" + semestre +
                '}';
    }
}