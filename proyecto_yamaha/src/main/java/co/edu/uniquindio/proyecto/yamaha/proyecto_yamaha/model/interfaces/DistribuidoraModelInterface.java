package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.interfaces;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Cliente;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;

public interface DistribuidoraModelInterface {

    public Empleado agregarEmpleado(Empleado nuevoEmpleado);

    public Empleado obtenerEmpleado(String cedula);

    public boolean actualizarEmpleado(Empleado empleadoActualizado);

    public boolean eliminarEmpleado(String cedula);

    public Cliente agregarCliente(Cliente nuevoCliente);

    public Cliente obtenerCliente(String cedula);

    public boolean actualizarCliente(Cliente clienteActualizado);

    public boolean eliminarCliente(String cedula);
}
