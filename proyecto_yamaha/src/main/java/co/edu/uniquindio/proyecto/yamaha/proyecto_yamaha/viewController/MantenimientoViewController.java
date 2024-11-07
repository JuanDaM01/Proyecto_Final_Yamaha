package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Mantenimiento;
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
import java.time.LocalDate;

public class MantenimientoViewController {

    @FXML
    private Button btnActualizarMantenimiento;

    @FXML
    private Button btnGuardarMantenimiento;

    @FXML
    private Button btnNuevoMantenimiento;

    @FXML
    private Button btnAtrasVentas;

    @FXML
    private Button btnEliminarMantenimiento;

    @FXML
    private DatePicker fechaMantenimiento;

    @FXML
    private TextArea prodDescripcion;

    @FXML
    private RadioButton radioPendiente;

    @FXML
    private RadioButton radioTerminado;

    @FXML
    private TableView<Mantenimiento> tablaMantenimiento;

    @FXML
    private TableColumn<Mantenimiento, String> tcDescripcion;

    @FXML
    private TableColumn<Mantenimiento, String> tcEstado;

    @FXML
    private TableColumn<Mantenimiento, String> tcFecha;


    // Lista para almacenar los mantenimientos
    private ObservableList<Mantenimiento> mantenimientoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializar la tabla
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstadoString()));
        tcFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaString()));

        tablaMantenimiento.setItems(mantenimientoList); // Asignar la lista a la tabla
    }

    @FXML
    void onActualizarMantenimiento(ActionEvent event) {
        Mantenimiento seleccionado = tablaMantenimiento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            System.out.println("Debe seleccionar un mantenimiento para actualizar");
            return;
        }

        String nuevaDescripcion = prodDescripcion.getText();
        String nuevoEstado = radioPendiente.isSelected() ? "PENDIENTE" : "TERMINADO";
        LocalDate nuevaFecha = fechaMantenimiento.getValue();

        seleccionado.setDescripcion(nuevaDescripcion);
        seleccionado.cambiarEstado(nuevoEstado);
        seleccionado.setFecha(nuevaFecha);

        actualizarTabla();
        limpiarCampos();
    }


    @FXML
    void onGuardarMantenimiento(ActionEvent event) {
        if (fechaMantenimiento.getValue() == null || prodDescripcion.getText().isEmpty()) {
            System.out.println("Debe completar todos los campos");
            return;
        }

        String descripcion = prodDescripcion.getText();
        String estado = radioPendiente.isSelected() ? "PENDIENTE" : "TERMINADO";
        LocalDate fecha = fechaMantenimiento.getValue();

        Mantenimiento nuevoMantenimiento = new Mantenimiento(fecha, estado, descripcion);
        mantenimientoList.add(nuevoMantenimiento);

        actualizarTabla();
        limpiarCampos();
    }

    @FXML
    void onEliminarMantenimiento(ActionEvent event) {
        Mantenimiento seleccionado = tablaMantenimiento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            System.out.println("Debe seleccionar un mantenimiento para eliminar");
            return;
        }

        mantenimientoList.remove(seleccionado);
        actualizarTabla();
        limpiarCampos();
    }

    private void actualizarTabla() {
        tablaMantenimiento.setItems(mantenimientoList);
    }

    private void limpiarCampos() {
        fechaMantenimiento.setValue(null);
        prodDescripcion.clear();
        radioPendiente.setSelected(false);
        radioTerminado.setSelected(false);
    }




    @FXML
    void onHabilitarCamposPendiente(ActionEvent event) {

    }

    @FXML
    void onHabilitarCamposTerminado(ActionEvent event) {

    }

    @FXML
    void onNuevoMantenimiento(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void onAtrasVentas(ActionEvent event) {
        try {
            // Cargar la vista de ventas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto/yamaha/proyecto_yamaha/Dashboard/admin-dashboard-view.fxml"));
            Parent ventasView = loader.load();

            // Mostrar la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(ventasView));
            stage.setTitle("Ventas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción de carga
        }
    }

    // Método para mostrar mensajes de error
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}

