package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

public class Repuesto extends Producto{
    public Repuesto(String nombre, float precio, int cantidad, String descripcion) {
        super(nombre, precio, cantidad, descripcion);
    }
    @Override
    public String getTipoProducto() {
        return "REPUESTO";
    }

    @Override
    public String getTipo() {
        return "";
    }

    @Override
    public String getCilindraje() {
        return "";
    }
}
