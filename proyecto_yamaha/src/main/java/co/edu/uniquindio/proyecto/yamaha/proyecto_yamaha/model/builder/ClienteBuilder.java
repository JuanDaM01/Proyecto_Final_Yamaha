package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Cliente;

public class ClienteBuilder {
    protected String nombre;
    protected String cedula;
    protected String email;
    protected String celular;
    protected int edad;

    public ClienteBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public ClienteBuilder cedula(String cedula){
        this.cedula = cedula;
        return this;
    }
    public ClienteBuilder email(String email){
        this.email = email;
        return this;
    }
    public ClienteBuilder celular(String celular){
        this.celular = celular;
        return this;
    }
    public ClienteBuilder edad(int edad){
        this.edad = edad;
        return this;
    }
    public Cliente build() {
        return new Cliente(nombre, cedula, email, celular, edad);
    }
}
