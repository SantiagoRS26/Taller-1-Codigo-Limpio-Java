package Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Persona.Persona;

public class VentaServicio {
    private String idVentaServicio;
    private LocalDateTime fechaVenta;
    private Persona persona;
    private List<DetalleVentaServicio> detalles;

    public VentaServicio(String idVentaServicio, LocalDateTime fechaVenta, Persona persona) {
        this.idVentaServicio = idVentaServicio;
        this.fechaVenta = fechaVenta;
        this.persona = persona;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleVentaServicio detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal() {
        double total = 0;
        for (DetalleVentaServicio detalle : detalles) {
            total += detalle.calcularSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "VentaServicio{" +
                "idVentaServicio='" + idVentaServicio + '\'' +
                ", fechaVenta=" + fechaVenta +
                ", persona=" + persona +
                ", detalles=" + detalles +
                '}';
    }
}