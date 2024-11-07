package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller;


import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory.ModelFactory;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.EmpleadoBuilder;

import java.util.List;

public class EmpleadoController {

    private static ModelFactory modelFactory;

    public EmpleadoController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Empleado> obtenerEmpleados() {
        return modelFactory.obtenerEmpleados();
    }

    public boolean crearEmpleado(Empleado empleado) {
        return modelFactory.crearEmpleado(empleado);
    }


    public boolean actualizarEmpleado(Empleado empleadoSeleccionado, Empleado empleadoFormulario) {

        EmpleadoBuilder empleadoBuilder = new EmpleadoBuilder()
                .nombre(empleadoSeleccionado.getNombre())
                .cedula(empleadoSeleccionado.getCedula())
                .email(empleadoSeleccionado.getEmail())
                .celular(empleadoSeleccionado.getCelular())
                .edad(empleadoSeleccionado.getEdad())
                .tipo(empleadoSeleccionado.getTipoString());

        Empleado empleadoActualizado = empleadoBuilder
                .nombre(empleadoFormulario.getNombre())
                .email(empleadoFormulario.getEmail())
                .celular(empleadoFormulario.getCelular())
                .edad(empleadoFormulario.getEdad())
                .tipo(empleadoFormulario.getTipoString()).build();

        return modelFactory.actualizarEmpleado(empleadoActualizado);
    }

    public boolean eliminarEmpleado(Empleado empleadoSeleccionado) {
        return modelFactory.eliminarEmpleado(empleadoSeleccionado);
    }
}
