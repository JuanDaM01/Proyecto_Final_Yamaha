package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AplicacionView {

    @FXML
    private RadioButton ADMIN;

    @FXML
    private RadioButton VENDEDOR;

    @FXML
    private TableColumn<Empleado, String> cedula;

    @FXML
    private TableColumn<Empleado, String> celular;

    @FXML
    private TableColumn<Empleado, Integer> edad;

    @FXML
    private TableColumn<Empleado, String> email;

    @FXML
    private TextField empleadoCedula;

    @FXML
    private TextField empleadoCelular;

    @FXML
    private TextField empleadoEdad;

    @FXML
    private TextField empleadoEmail;

    @FXML
    private TextField empleadoNombre;

    @FXML
    private TableColumn<Empleado, String> nombre;

    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private ToggleGroup tipos;

    ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList(
            new Empleado("MMMM","2332323","DGGGGF","3223556",21,"ADMIN")
    );


    public void initialize(URL url, ResourceBundle resourceBundle){
        nombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        cedula.setCellValueFactory(new PropertyValueFactory<Empleado,String>("cedula"));
        email.setCellValueFactory(new PropertyValueFactory<Empleado,String>("email"));
        celular.setCellValueFactory(new PropertyValueFactory<Empleado,String>("celular"));
        edad.setCellValueFactory(new PropertyValueFactory<Empleado,Integer>("edad"));
        tablaEmpleados.setItems(listaEmpleados);
    }
    @FXML
    void onCrearEmpleado(ActionEvent event) {

        String nombre = empleadoNombre.getText();
        String cedula = empleadoCedula.getText();
        String email = empleadoEmail.getText();
        String celular = empleadoCelular.getText();
        int edad = Integer.parseInt(empleadoEdad.getText());
        String tipo = "VENDEDOR";
        if (ADMIN.isSelected()){
            tipo = "ADMIN";
        }

        Empleado empleado1 = new Empleado(nombre,cedula,email,celular,edad,tipo);
        System.out.print(empleado1.getNombre());
        listaEmpleados.add(empleado1);
        tablaEmpleados.setItems(listaEmpleados);
    }


}

