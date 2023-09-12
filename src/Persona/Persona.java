package Persona;

public class Persona {
    private String nombre;
    private String genero;
    private int edad;
    private String cedula;
    private String direccion;

    public Persona (String nombre, String genero, int edad, String cedula, String direccion) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.cedula = cedula;
        this.direccion = direccion;
    }
    
    public void Saludar() {
        System.out.println("Hola, mi nombre es " + this.nombre);
    }

    public void Comer() {
        System.out.println("Estoy comiendo");
    }

    public void Dormir() {
        System.out.println("Zzz");
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                ", cedula='" + cedula + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

}