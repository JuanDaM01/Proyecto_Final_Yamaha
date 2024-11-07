package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.ClienteBuilder;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.builder.EmpleadoBuilder;

public class Persona {
    private String nombre;
    private String cedula;
    private String email;
    private String celular;
    private int edad;

    public Persona(String nombre, String cedula, String email, String celular, int edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.email = email;
        this.celular = celular;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public int getEdad() {
        return edad;
    }
}

