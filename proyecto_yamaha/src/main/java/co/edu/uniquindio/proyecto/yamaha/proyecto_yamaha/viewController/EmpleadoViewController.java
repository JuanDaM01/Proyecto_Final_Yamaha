package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.EmpleadoController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EmpleadoViewController {
    EmpleadoController empleadoControllerService;
    ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();

    FilteredList<Empleado> filteredData = new FilteredList<>(FXCollections.observableList(listaEmpleados));

    Empleado empleadoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarEmpleado;

    @FXML
    private Button btnCrearEmpleado;

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private Button btnLimpiarEmpleado;

    @FXML
    private RadioButton ADMIN;

    @FXML
    private RadioButton VENDEDOR;

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
    private TextField txtSearch;

    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private TableColumn<Empleado, String> tccedula;

    @FXML
    private TableColumn<Empleado, String> tccelular;

    @FXML
    private TableColumn<Empleado, String> tcedad;

    @FXML
    private TableColumn<Empleado, String> tcemail;

    @FXML
    private TableColumn<Empleado, String> tcnombre;

    @FXML
    private ToggleGroup tipos;



    @FXML
    void initialize() {
        empleadoControllerService = new EmpleadoController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        actualizarTabla();
    }

    private void actualizarTabla(){

        tablaEmpleados.getItems().clear();
        obtenerEmpleados();
        tablaEmpleados.setItems(listaEmpleados);
        listenerSelection();
        ListenerFilter();
    }

    private void ListenerFilter() {
        tablaEmpleados.setRowFactory(tableView -> {
            TableRow<Empleado> row = new TableRow<>();
            row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), false);
            row.itemProperty().addListener((obs, oldOrder, newOrder) -> {
                boolean assignClass = filteredData.contains(newOrder);
                row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), assignClass);
            });
            return row;
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
                tablaEmpleados.setItems(filterList(listaEmpleados, newValue.toLowerCase()))
        );


    }
    private ObservableList<Empleado> filterList(List<Empleado> list, String searchText){
        List<Empleado> filteredList = new ArrayList<>();
        for (Empleado order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }
    private boolean searchFindsOrder(Empleado empleado, String searchText){
        System.out.print(searchText);
        return (empleado.getNombre().toLowerCase().contains(searchText))
                || (empleado.getCedula().toLowerCase().contains(searchText))
                || (searchText.contains(String.valueOf(empleado.getEdad())))
                || (empleado.getEmail().toLowerCase().contains(searchText));
    }
    private void agregarEmpleado() {
        if(validarFormulario()){
            Empleado empleado = construirDatosEmpleados();
            if(empleadoControllerService.crearEmpleado(empleado)){
                listaEmpleados.add(empleado);
                mostrarMensaje("Notificación Empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación Empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private Empleado construirDatosEmpleados() {
        String tipoString = obtenerTipoSeleccionado();
        return Empleado.builder()
                .nombre(empleadoNombre.getText())
                .cedula(empleadoCedula.getText())
                .email(empleadoEmail.getText())
                .celular(empleadoCelular.getText())
                .edad(Integer.parseInt(empleadoEdad.getText()))
                .tipo(tipoString)
                .build();
    }

    private String obtenerTipoSeleccionado() {
        if (ADMIN.isSelected()){
            return ADMIN.getText();
        }

        return VENDEDOR.getText();
    }

    private void limpiarCamposEmpleado() {
        empleadoNombre.setText("");
        empleadoCedula.setText("");
        empleadoEmail.setText("");
        empleadoCelular.setText("");
        empleadoEdad.setText("");
    }

    private boolean validarFormulario() {
        if(empleadoNombre.getText().isEmpty()){
            return false;
        }
        if(empleadoCedula.getText().isEmpty()){
            return false;
        }
        if(empleadoEmail.getText().isEmpty()){
            return false;
        }
        if(empleadoCelular.getText().isEmpty()){
            return false;
        }
        if(empleadoEdad.getText().isEmpty()){
            return false;
        }
        return true;
    }


    private void listenerSelection() {
        tablaEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });

    }
    private void mostrarInformacionEmpleado(Empleado empleadoSeleccionado) {

        if (empleadoSeleccionado != null) {
            empleadoNombre.setText(empleadoSeleccionado.getNombre());
            empleadoCedula.setText(empleadoSeleccionado.getCedula());
            empleadoEmail.setText(empleadoSeleccionado.getEmail());
            empleadoCelular.setText(empleadoSeleccionado.getCelular());
            empleadoEdad.setText(String.valueOf(empleadoSeleccionado.getEdad()));
        }
    }
    private void obtenerEmpleados() {
        listaEmpleados.addAll(empleadoControllerService.obtenerEmpleados());

    }

    private void initDataBinding() {

        tcnombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tccedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        tcemail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tccelular.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCelular()));
        tcedad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));

    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    @FXML
    void onActualizarEmpleado(ActionEvent event) {
        actualizarEmpleado();

    }

    private void actualizarEmpleado() {
        if(validarFormulario()){
            Empleado empleadoActualizado = construirDatosEmpleados();
            if(empleadoControllerService.actualizarEmpleado(empleadoSeleccionado, empleadoActualizado)){
                actualizarTabla();
                mostrarMensaje("Notificación Empleado", "Empleado actualizado", "El empleado se ha actualizo con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación Empleado", "Empleado no actualizado", "El empleado no se ha actualizado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Empleado", "Empleado no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onCrearEmpleado(ActionEvent event) {
        agregarEmpleado();

    }

    @FXML
    void onEliminarEmpleado(ActionEvent event) {
        eliminarEmpleado();

    }

    private void eliminarEmpleado() {
        if(empleadoControllerService.eliminarEmpleado(empleadoSeleccionado)){
            actualizarTabla();
            mostrarMensaje("Notificación Empleado", "Empleado eliminado", "El empleado se ha eliminado con éxito", Alert.AlertType.INFORMATION);
            limpiarCamposEmpleado();
        }else{
            mostrarMensaje("Notificación Empleado", "Empleado no eliminado", "El empleado no se ha eliminado", Alert.AlertType.ERROR);
        }

    }
    @FXML
    void onLimpiarEmpleado(ActionEvent event) {
        limpiarCamposEmpleado();

    }


}



