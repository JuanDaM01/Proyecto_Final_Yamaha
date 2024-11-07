package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.interfaces;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;

public interface EmpleadoControllerInterface {

    public Empleado crearEmpleado(String nombre, String cedula, String email, String celular, int edad, String tipo);

    public Empleado obtenerEmpleado(String cedula);

    public boolean actualizarEmpleado(Empleado empleadoActualizado);

    public boolean eliminarEmpleado(String cedula);
}
