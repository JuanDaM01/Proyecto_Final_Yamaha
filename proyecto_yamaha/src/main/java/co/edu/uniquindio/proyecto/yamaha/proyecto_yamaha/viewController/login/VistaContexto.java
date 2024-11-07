package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.interfaces.VistaPorRolInterface;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaContexto {

    private VistaPorRolInterface vistaPorRolInterface;

    public VistaContexto(VistaPorRolInterface vistaPorRolInterface)
    {
        this.vistaPorRolInterface = vistaPorRolInterface;
    }

    public void mostrarVista(Class ventanaActual, Stage escena) throws IOException {
        vistaPorRolInterface.mostrarVista(ventanaActual, escena);
    }
}

