package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory.ModelFactory;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Producto;

import java.util.List;

public class ProductoController {
    private static ModelFactory modelFactory;

    public ProductoController(){
        modelFactory = ModelFactory.getInstancia();
    }
    public List<Producto> obtenerProductos() {
        return modelFactory.obtenerProductos();
    }

    public List<String> listaTipoMotos(){
        return modelFactory.listaTipoMotos();
    }

    public boolean crearProducto(Producto producto) { return modelFactory.crearProducto(producto);
    }

    public boolean actualizarProducto(Producto productoSeleccionado, Producto productoActualizado) {
        productoSeleccionado.setNombre(productoActualizado.getNombre());
        productoSeleccionado.setPrecio(productoActualizado.getPrecio());
        productoSeleccionado.setCantidad(productoActualizado.getCantidad());
        productoSeleccionado.setDescripcion(productoActualizado.getDescripcion());

        return modelFactory.actualizarProducto(productoSeleccionado);
    }

    public boolean eliminarProducto(Producto productoSeleccionado) {
        return modelFactory.eliminarProducto(productoSeleccionado);
    }
}
