package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.VentaController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Venta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class VentasViewController {

    VentaController ventasControllerService;

    ObservableList<Venta> listaVentas = FXCollections.observableArrayList();

    Venta ventaSeleccionada;

    @FXML
    private RadioButton Efectivo;

    @FXML
    private TextField InfoClienteVenta;

    @FXML
    private ToggleGroup MedioDePago;

    @FXML
    private RadioButton Tarjeta;

    @FXML
    private Button btnAgregarDetalles;

    @FXML
    private Button btnAgregarMantenimiento;

    @FXML
    private Button btnEliminarVenta;

    @FXML
    private Button btnGuardarVenta;

    @FXML
    private Button btnLimpearVenta;

    @FXML
    private DatePicker fechaVenta;

    @FXML
    private TextField infoEmpleadoVenta;

    @FXML
    private TableView<Venta> tablaVenta;

    @FXML
    private TableColumn<Venta, String> tcFecha;

    @FXML
    private TableColumn<Venta, String> tcIdCliente;

    @FXML
    private TableColumn<Venta, String> tcIdEmpleado;

    @FXML
    private TableColumn<Venta, String> tcTotal;

    @FXML
    private TextField totalVenta;

    @FXML
    void initialize()
    {
        ventasControllerService = new VentaController();
        initView();
    }

    private void initView() {
        initDataBinding();
        actualizarTabla();
    }

    private void initDataBinding() {

        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaString()));
        tcIdEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpleado().getCedula()));
        tcIdCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getCedula()));
        tcTotal.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotal())));
    }

    private void actualizarTabla() {
        tablaVenta.getItems().clear();
        obtenerVentas();
        tablaVenta.setItems(listaVentas);
        listenerSelection();
    }

    private void listenerSelection() {
        tablaVenta.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ventaSeleccionada = newSelection;
            mostrarInformacionVenta(ventaSeleccionada);
        });
    }

    private void mostrarInformacionVenta(Venta ventaSeleccionada) {

        if (ventaSeleccionada != null) {
            fechaVenta.setValue(ventaSeleccionada.getFecha());
            infoEmpleadoVenta.setText(ventaSeleccionada.getEmpleado().getCedula());
            InfoClienteVenta.setText(ventaSeleccionada.getCliente().getCedula());
            totalVenta.setText(String.valueOf(ventaSeleccionada.getTotal()));
            if (ventaSeleccionada.getMedioPagoString().equals("EFECTIVO"))
            {
                Efectivo.setSelected(true);
                Tarjeta.setSelected(false);
            }
            else
            {
                Efectivo.setSelected(false);
                Tarjeta.setSelected(true);
            }

        }
    }

    private void obtenerVentas() {
        listaVentas.addAll(ventasControllerService.obtenerVentas());
    }

    @FXML
    void onAgregarDetalles(ActionEvent event) {

    }


    @FXML
    void onAgregarMantenimiento(ActionEvent event) {
        try {
            // Cargar el archivo FXML del mantenimiento
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto/yamaha/proyecto_yamaha/Mantenimiento/MantenimientoView.fxml"));

            Parent mantenimientoView = loader.load();

            // Obtener el stage actual y cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mantenimientoView));
            stage.show(); // Mostrar la nueva vista

        } catch (IOException e) {
            e.printStackTrace();
            // Mostrar un mensaje de error si no puede cargar la vista
            mostrarMensaje("Error", "No se pudo cargar la vista de mantenimiento", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    // Implementación de mostrarMensaje
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    @FXML
    void onEliminarVenta(ActionEvent event) {

    }

    @FXML
    void onGuardarVenta(ActionEvent event) {

    }

    @FXML
    void onLimpearVenta(ActionEvent event) {

    }

}

