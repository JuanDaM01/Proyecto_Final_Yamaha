package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.ClienteBuilder;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.memento.ClienteMemento;

public class Cliente extends Persona {

    public Cliente(String nombre, String cedula, String email, String celular, int edad) {
        super(nombre, cedula, email, celular, edad);
    }

    public static ClienteBuilder builder(){
        return new ClienteBuilder();
    }

    public ClienteMemento guardarMemento(){
        return new ClienteMemento(this.getNombre(), this.getCedula(), this.getEmail(), this.getCelular(), this.getEdad());
    }

    public void restaurarDesdeMemento(ClienteMemento clienteMemento)
    {
        Cliente.builder()
                .nombre(clienteMemento.getNombre())
                .cedula(clienteMemento.getCedula())
                .email(clienteMemento.getEmail())
                .celular(clienteMemento.getCelular())
                .edad(clienteMemento.getEdad())
                .build();
    }
}
