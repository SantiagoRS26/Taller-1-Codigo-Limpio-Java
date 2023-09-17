package Entidades.Servicio;

public class Servicio {
    private String nombre;
    private String descripcion;
    private double precio;

    public Servicio(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public double calcularCostoAdicional(double porcentaje) {
        double costoAdicional = precio * (porcentaje / 100);
        return costoAdicional;
    }

    public double aplicarDescuento(double porcentajeDescuento) {
        double descuento = precio * (porcentajeDescuento / 100);
        return precio - descuento;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
