package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.AplicacionView;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.interfaces.VistaPorRolInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaEmpleado implements VistaPorRolInterface {

    private Empleado vendedor;
    public VistaEmpleado (Empleado vendedor)
    {
        this.vendedor = vendedor;
    }

    @Override
    public void mostrarVista(Class ventanaActual, Stage escena) throws IOException {
        FXMLLoader loader = new FXMLLoader(ventanaActual.getResource("/co/edu/uniquindio/proyecto/yamaha/proyecto_yamaha/Dashboard/empleado-dashboard-view.fxml"));
        Parent root = loader.load();
        AplicacionView app = loader.getController();
        //app.setVendedor(vendedor);

        Scene scene = new Scene(root);
        escena.setScene(scene);
        escena.show();
    }
}
