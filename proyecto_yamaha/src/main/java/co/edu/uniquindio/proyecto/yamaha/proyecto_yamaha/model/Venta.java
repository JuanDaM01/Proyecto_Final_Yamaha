package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    enum MEDIOSPAGO {EFECTIVO, TARJETA}
    private LocalDate fecha;
    private Empleado empleado;
    private Cliente cliente;
    private MEDIOSPAGO medioPago;
    List<DetalleVenta> listaDetallesVenta = new ArrayList<DetalleVenta>();
    List<Mantenimiento> listaMantenimiento = new ArrayList<Mantenimiento>();
    private float total;

    public Venta(LocalDate fecha, Empleado empleado, Cliente cliente, String medioPagoString) {
        this.fecha = fecha;
        this.empleado = empleado;
        this.cliente = cliente;
        this.medioPago = obtenerMedioPago(medioPagoString);
        this.total = 0;
    }

    private MEDIOSPAGO obtenerMedioPago(String medioPagoString) {
        if ("EFECTIVO".equals(medioPagoString)) {
            return MEDIOSPAGO.EFECTIVO;
        } else if ("TARJETA".equals(medioPagoString)) {
            return MEDIOSPAGO.TARJETA;
        } else {
            System.out.println("Medio de pago no válido");
            return null;
        }
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MEDIOSPAGO getMedioPago() {
        return medioPago;
    }

    public String getMedioPagoString()
    {
        return medioPago.name();
    }

    public void setMedioPago(MEDIOSPAGO medioPago) {
        this.medioPago = medioPago;
    }

    public List<DetalleVenta> getListaDetallesVenta() {
        return listaDetallesVenta;
    }

    public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
        this.listaDetallesVenta = listaDetallesVenta;
    }

    public List<Mantenimiento> getListaMantenimiento() {
        return listaMantenimiento;
    }

    public void setListaMantenimiento(List<Mantenimiento> listaMantenimiento) {
        this.listaMantenimiento = listaMantenimiento;
    }

    public float getTotal() {
        // Reiniciar total para recalcularlo
        for (DetalleVenta detalle : listaDetallesVenta) {
            total += detalle.getPrecio();
        }
        return total;
    }
}
