package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.factory.ModelFactory;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Dto.UsuarioDto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;

public class LoginController {

    private static ModelFactory modelFactory;

    public LoginController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public Empleado autenticar(UsuarioDto usuarioDto)
    {
        Empleado empleadoAutenticado = modelFactory.autenticarUsuario(usuarioDto);
        return empleadoAutenticado;
    }
}
