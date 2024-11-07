package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller;


import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.interfaces.ClienteControllerInterface;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory.ModelFactory;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Cliente;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.ClienteBuilder;

import java.util.List;

public class ClienteController{

    private static ModelFactory modelFactory;

    public ClienteController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Cliente> obtenerClientes() {
        return modelFactory.obtenerClientes();
    }

    public boolean crearCliente(Cliente cliente) {
        return modelFactory.crearCliente(cliente);
    }


    public boolean actualizarCliente(Cliente clienteSeleccionado, Cliente clienteFormulario) {

        ClienteBuilder clienteBuilder = new ClienteBuilder()
                .nombre(clienteSeleccionado.getNombre())
                .cedula(clienteSeleccionado.getCedula())
                .email(clienteSeleccionado.getEmail())
                .celular(clienteSeleccionado.getCelular())
                .edad(clienteSeleccionado.getEdad());

        Cliente clienteActualizado = clienteBuilder
                .nombre(clienteFormulario.getNombre())
                .email(clienteFormulario.getEmail())
                .celular(clienteFormulario.getCelular())
                .edad(clienteFormulario.getEdad()).build();

        return modelFactory.actualizarCliente(clienteActualizado);
    }

    public boolean eliminarCliente(Cliente clienteSeleccionado) {
        return modelFactory.eliminarCliente(clienteSeleccionado);
    }
}
