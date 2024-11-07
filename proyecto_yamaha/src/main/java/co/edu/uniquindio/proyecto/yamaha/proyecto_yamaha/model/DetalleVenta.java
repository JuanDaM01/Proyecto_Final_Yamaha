package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private float precio;

    public DetalleVenta(Producto producto, int cantidad, float precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
