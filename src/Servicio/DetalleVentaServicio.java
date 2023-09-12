package Servicio;

public class DetalleVentaServicio {
    private String idDetalleVentaServicio;
    private int cantidad;
    private Servicio servicio;

    public DetalleVentaServicio(String idDetalleVentaServicio, int cantidad) {
        this.idDetalleVentaServicio = idDetalleVentaServicio;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return cantidad * servicio.getPrecio();
    }

    @Override
    public String toString() {
        return "DetalleVentaServicio{" +
                "idDetalleVentaServicio='" + idDetalleVentaServicio + '\'' +
                ", cantidad=" + cantidad +
                ", servicio=" + servicio +
                '}';
    }
}
