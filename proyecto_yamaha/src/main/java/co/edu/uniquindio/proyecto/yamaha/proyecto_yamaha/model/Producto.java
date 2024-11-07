package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

public abstract class Producto {
    private String nombre;
    private float precio;
    private int cantidad;
    private String descripcion;

    public Producto(String nombre, float precio, int cantidad, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract String getTipoProducto();

    public abstract String getTipo();

    public abstract String getCilindraje();
}
