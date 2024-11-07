package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.interfaces;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Cliente;

public interface ClienteControllerInterface {

    public Cliente crearCliente(Cliente cliente);

    public Cliente obtenerCliente(String cedula);

    public boolean actualizarCliente(Cliente clienteActualizado);

    public boolean eliminarCliente(String cedula);
}
