package Entidades.Persona.Cargo;

public class Cargo {
    private String idCargo;
    private String nombre;
    private String descripcion;

    public Cargo(String idCargo, String nombre, String descripcion) {
        this.idCargo = idCargo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo='" + idCargo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
