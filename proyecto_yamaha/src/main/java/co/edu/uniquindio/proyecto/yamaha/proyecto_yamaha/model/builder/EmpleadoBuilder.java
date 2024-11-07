package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;

public class EmpleadoBuilder {
    protected String nombre;
    protected String cedula;
    protected String email;
    protected String celular;
    protected String tipo;
    protected int edad;

    public EmpleadoBuilder tipo(String tipo){
        this.tipo = tipo;
        return this;
    }

    public EmpleadoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public EmpleadoBuilder cedula(String cedula){
        this.cedula = cedula;
        return this;
    }
    public EmpleadoBuilder email(String email){
        this.email = email;
        return this;
    }
    public EmpleadoBuilder celular(String celular){
        this.celular = celular;
        return this;
    }
    public EmpleadoBuilder edad(int edad){
        this.edad = edad;
        return this;
    }
    public Empleado build() {
        return new Empleado(nombre, cedula, email, celular, edad,tipo);
    }
}
