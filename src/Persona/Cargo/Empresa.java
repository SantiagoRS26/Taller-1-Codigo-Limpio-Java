package Persona.Cargo;

public class Empresa {
    private String nitEmpresa;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

    public Empresa(String nitEmpresa, String nombre, String direccion, String telefono, String email) {
        this.nitEmpresa = nitEmpresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nitEmpresa='" + nitEmpresa + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
