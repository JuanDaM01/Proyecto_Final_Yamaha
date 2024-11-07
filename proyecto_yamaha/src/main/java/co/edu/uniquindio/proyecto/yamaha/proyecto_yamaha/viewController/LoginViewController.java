package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.LoginController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Dto.UsuarioDto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.VistaAdmin;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.VistaContexto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.VistaEmpleado;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController.login.interfaces.VistaPorRolInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    LoginController loginControllerService;
    VistaPorRolInterface vistaPorRol;

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void initialize() {
        loginControllerService = new LoginController();
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    private boolean validarFormulario()
    {
        if(username.getText().isEmpty() || password.getText().isEmpty())
        {
            return false;
        }
        return true;
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        if (!validarFormulario())
        {
            // Si intenta hacer login con los campos en blanco
            mostrarMensaje("Login Invalido", "", "Por favor diligencie los datos del formulario.", Alert.AlertType.ERROR);
            return;
        }

        UsuarioDto usuarioDto = new UsuarioDto(username.getText(), password.getText());
        Empleado empleadoAutenticado = loginControllerService.autenticar(usuarioDto);
        if (empleadoAutenticado == null)
        {
            mostrarMensaje("Login Invalido", "", "No se encontró usuario con estas credenciales.", Alert.AlertType.ERROR);
            return;
        }

        switch (empleadoAutenticado.getTipoString())
        {
            case "ADMIN":
                vistaPorRol = new VistaAdmin();
                break;

            case "VENDEDOR":
                vistaPorRol = new VistaEmpleado(empleadoAutenticado);
                break;

            default:
                throw new IllegalArgumentException("Rol no reconocido: " + empleadoAutenticado.getTipoString());
        }

        Stage stage = (Stage) btnlogin.getScene().getWindow();
        VistaContexto contexto = new VistaContexto(vistaPorRol);
        contexto.mostrarVista(getClass(), stage);
    }
}
