package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory.ModelFactory;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Venta;

import java.util.List;

public class VentaController {

    private static ModelFactory modelFactory;

    public VentaController(){
        modelFactory = ModelFactory.getInstancia();
    }
    public List<Venta> obtenerVentas() {
        return modelFactory.obtenerVentas();
    }
}
