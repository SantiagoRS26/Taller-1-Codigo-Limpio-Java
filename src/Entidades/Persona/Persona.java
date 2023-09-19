package Entidades.Persona;

public class Persona {
    private String nombre;
    private String genero;
    private int edad;
    private String cedula;
    private String direccion;

    public Persona(String cedula, String nombre, String genero, int edad, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
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