package Entidades.Persona.TipoPersona;
import Entidades.Persona.Persona;

public class Empleado extends Persona {
    private String idCarnet;
    private String telefono;

    public Empleado(String nombre, String genero, int edad, String cedula, String direccion, String idCarnet, String telefono) {
        super(cedula, nombre, genero, edad, direccion);
        this.idCarnet = idCarnet;
        this.telefono = telefono;
    }
    
    public void Trabajar() {
        System.out.println("Estoy trabajando");
    }

    public void Cobrar() {
        System.out.println("Estoy cobrando");
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idCarnet='" + idCarnet + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
    

}
