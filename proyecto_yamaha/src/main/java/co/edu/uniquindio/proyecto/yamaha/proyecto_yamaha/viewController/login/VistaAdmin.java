package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.interfaces.VistaPorRolInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaAdmin implements VistaPorRolInterface {


    @Override
    public void mostrarVista(Class ventanaActual, Stage escena) throws IOException {
        Parent root = FXMLLoader.load(ventanaActual.getResource("/co/edu/uniquindio/proyecto/yamaha/proyecto_yamaha/Dashboard/admin-dashboard-view.fxml"));

        Scene scene = new Scene(root);
        escena.setScene(scene);
        escena.show();
    }
}
