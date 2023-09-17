package Entidades.Servicio;

public class DetalleVentaServicio {
    private String idDetalleVentaServicio;
    private int cantidad;
    private Servicio servicio;

    public DetalleVentaServicio(String idDetalleVentaServicio, int cantidad, Servicio servicio) {
        this.idDetalleVentaServicio = idDetalleVentaServicio;
        this.cantidad = cantidad;
        this.servicio = servicio;
    }

    public double calcularSubtotal() {
        return cantidad * servicio.getPrecio();
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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
