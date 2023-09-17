package Entidades.Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Facturacion {
    private LocalDateTime fechaFactura;
    private String idFactura;
    private List<VentaServicio> ventas;

    public Facturacion(LocalDateTime fechaFactura, String idFactura) {
        this.fechaFactura = fechaFactura;
        this.idFactura = idFactura;
        this.ventas = new ArrayList<>();
    }

    public double GenerarFactura (){
        double total = 0;
        for (VentaServicio venta : ventas) {
            total += venta.calcularTotal();
        }
        return total;
    }

    public void AgregarVenta(VentaServicio venta) {
        ventas.add(venta);
    }

    @Override
    public String toString() {
        return "Facturacion{" +
                "fechaFactura=" + fechaFactura +
                ", idFactura='" + idFactura + '\'' +
                ", ventas=" + ventas +
                '}';
    }
}
