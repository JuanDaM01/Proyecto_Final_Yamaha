package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.memento.ClienteMemento;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.ClienteController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Cliente;
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


public class ClienteViewController {
    ClienteController clienteControllerService;
    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    FilteredList<Cliente> filteredData = new FilteredList<>(FXCollections.observableList(listaClientes));

    Cliente clienteSeleccionado;

    ClienteMemento clienteMemento;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnLimpiarCliente;

    @FXML
    private Button btnRestaurarCliente;

    @FXML
    private TextField clienteCedula;

    @FXML
    private TextField clienteCelular;

    @FXML
    private TextField clienteEdad;

    @FXML
    private TextField clienteEmail;

    @FXML
    private TextField clienteNombre;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> tccedula;

    @FXML
    private TableColumn<Cliente, String> tccelular;

    @FXML
    private TableColumn<Cliente, String> tcedad;

    @FXML
    private TableColumn<Cliente, String> tcemail;

    @FXML
    private TableColumn<Cliente, String> tcnombre;


    @FXML
    void initialize() {
        clienteControllerService = new ClienteController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        actualizarTabla();
    }

    private void actualizarTabla(){

        tablaClientes.getItems().clear();
        obtenerClientes();
        tablaClientes.setItems(listaClientes);
        listenerSelection();
        ListenerFilter();
    }

    private void ListenerFilter() {
        tablaClientes.setRowFactory(tableView -> {
            TableRow<Cliente> row = new TableRow<>();
            row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), false);
            row.itemProperty().addListener((obs, oldOrder, newOrder) -> {
                boolean assignClass = filteredData.contains(newOrder);
                row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), assignClass);
            });
            return row;
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
                tablaClientes.setItems(filterList(listaClientes, newValue.toLowerCase()))
        );


    }
    private ObservableList<Cliente> filterList(List<Cliente> list, String searchText){
        List<Cliente> filteredList = new ArrayList<>();
        for (Cliente order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }
    private boolean searchFindsOrder(Cliente cliente, String searchText){
        System.out.print(searchText);
        return (cliente.getNombre().toLowerCase().contains(searchText))
                || (cliente.getCedula().toLowerCase().contains(searchText))
                || (searchText.contains(String.valueOf(cliente.getEdad())))
                || (cliente.getEmail().toLowerCase().contains(searchText));
    }
    private void agregarCliente() {
        if(validarFormulario()){
            Cliente cliente = construirDatosClientes();
            if(clienteControllerService.crearCliente(cliente)){
                listaClientes.add(cliente);
                clienteMemento = cliente.guardarMemento();
                mostrarMensaje("Notificación Cliente", "Cliente creado", "El cliente se ha creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposCliente();
            }else{
                mostrarMensaje("Notificación Cliente", "Cliente no creado", "El cliente no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Cliente", "Cliente no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private Cliente construirDatosClientes() {
        return Cliente.builder()
                .nombre(clienteNombre.getText())
                .cedula(clienteCedula.getText())
                .email(clienteEmail.getText())
                .celular(clienteCelular.getText())
                .edad(Integer.parseInt(clienteEdad.getText()))
                .build();
    }



    private void limpiarCamposCliente() {
        clienteNombre.setText("");
        clienteCedula.setText("");
        clienteEmail.setText("");
        clienteCelular.setText("");
        clienteEdad.setText("");
    }

    private boolean validarFormulario() {
        if(clienteNombre.getText().isEmpty()){
            return false;
        }
        if(clienteCedula.getText().isEmpty()){
            return false;
        }
        if(clienteEmail.getText().isEmpty()){
            return false;
        }
        if(clienteCelular.getText().isEmpty()){
            return false;
        }
        if(clienteEdad.getText().isEmpty()){
            return false;
        }
        return true;
    }


    private void listenerSelection() {
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteSeleccionado = newSelection;
            clienteMemento = clienteSeleccionado.guardarMemento();
            mostrarInformacionCliente(clienteSeleccionado);
        });

    }
    private void mostrarInformacionCliente(Cliente clienteSeleccionado) {

        if (clienteSeleccionado != null) {
            clienteNombre.setText(clienteSeleccionado.getNombre());
            clienteCedula.setText(clienteSeleccionado.getCedula());
            clienteEmail.setText(clienteSeleccionado.getEmail());
            clienteCelular.setText(clienteSeleccionado.getCelular());
            clienteEdad.setText(String.valueOf(clienteSeleccionado.getEdad()));
        }
    }
    private void obtenerClientes() {
        listaClientes.addAll(clienteControllerService.obtenerClientes());

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
    void onActualizarCliente(ActionEvent event) {
        actualizarCliente();

    }

    private void actualizarCliente() {
        if(validarFormulario()){
            Cliente clienteActualizado = construirDatosClientes();
            if(clienteControllerService.actualizarCliente(clienteSeleccionado, clienteActualizado)){
                actualizarTabla();
                mostrarMensaje("Notificación Cliente", "Cliente actualizado", "El cliente se ha actualizo con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposCliente();
            }else{
                mostrarMensaje("Notificación Cliente", "Cliente no actualizado", "El cliente no se ha actualizado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Cliente", "Cliente no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onCrearCliente(ActionEvent event) {
        agregarCliente();

    }

    @FXML
    void onEliminarCliente(ActionEvent event) {
        eliminarCliente();

    }

    private void eliminarCliente() {
        if(clienteControllerService.eliminarCliente(clienteSeleccionado)){
            actualizarTabla();
            mostrarMensaje("Notificación Cliente", "Cliente eliminado", "El cliente se ha eliminado con éxito", Alert.AlertType.INFORMATION);
            limpiarCamposCliente();
        }else{
            mostrarMensaje("Notificación Cliente", "Cliente no eliminado", "El cliente no se ha eliminado", Alert.AlertType.ERROR);
        }

    }
    @FXML
    void onLimpiarCliente(ActionEvent event) {
        limpiarCamposCliente();

    }

    @FXML
    void restaurarCliente(ActionEvent event) {
        if (clienteMemento != null)
        {
            clienteSeleccionado.restaurarDesdeMemento(clienteMemento);
            mostrarInformacionCliente(clienteSeleccionado);
        }

    }


}



