package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.controller.ProductoController;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Moto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Producto;
import co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.model.Repuesto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewController {

    ProductoController productoControllerService;

    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    FilteredList<Producto> filteredData = new FilteredList<>(FXCollections.observableList(listaProductos));

    Producto productoSeleccionado;


    @FXML
    private RadioButton radioMoto;

    @FXML
    private RadioButton radioRepuesto;

    @FXML
    private ComboBox<String> TipoMoto;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnLimpearProducto;

    @FXML
    private TextField prodCantidad;

    @FXML
    private TextField prodCilindraje;

    @FXML
    private TextArea prodDescripcion;

    @FXML
    private TextField prodNombre;

    @FXML
    private TextField prodPrecio;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> tcCantidad;

    @FXML
    private TableColumn<Producto, String> tcCilindraje;

    @FXML
    private TableColumn<Producto, String> tcDescripcion;

    @FXML
    private TableColumn<Producto, String> tcNombre;

    @FXML
    private TableColumn<Producto, String> tcPrecio;

    @FXML
    private TableColumn<Producto, String> tcTipo;

    @FXML
    private TableColumn<Producto, String> tcTipoMoto;

    @FXML
    private ToggleGroup tipoProducto;

    @FXML
    void initialize() {
        productoControllerService = new ProductoController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        actualizarTabla();
    }

    private void actualizarTabla(){
        tablaProductos.getItems().clear();
        obtenerProductos();
        tablaProductos.setItems(listaProductos);
        listenerSelection();
        ListenerFilter();
    }

    private void initDataBinding() {
        tcTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoProducto()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
        tcCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidad())));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tcTipoMoto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTipo())));
        tcCilindraje.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCilindraje()));
    }

    private void ListenerFilter() {
        tablaProductos.setRowFactory(tableView -> {
            TableRow<Producto> row = new TableRow<>();
            row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), false);
            row.itemProperty().addListener((obs, oldOrder, newOrder) -> {
                boolean assignClass = filteredData.contains(newOrder);
                row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), assignClass);
            });
            return row;
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
                tablaProductos.setItems(filterList(listaProductos, newValue.toLowerCase()))
        );
    }

    private ObservableList<Producto> filterList(List<Producto> list, String searchText){
        List<Producto> filteredList = new ArrayList<>();
        for (Producto order : list){
            if(searchFindsOrder(order, searchText)){
                filteredList.add(order);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(Producto producto, String searchText){
        System.out.print(searchText);
        return (producto.getNombre().toLowerCase().contains(searchText));
        }

    private void listenerSelection() {
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarInformacionProducto(productoSeleccionado);
        });
    }

    private void mostrarInformacionProducto(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            // tiposeleccionado
            prodNombre.setText(productoSeleccionado.getNombre());
            prodPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));
            prodCantidad.setText(String.valueOf(productoSeleccionado.getCantidad()));
            prodDescripcion.setText(productoSeleccionado.getDescripcion());
            // tipo moto
            // prodCilindraje
        }
    }

    private void agregarProducto() {
        if(validarFormulario()){
            Producto producto = construirDatosProducto();
            if(productoControllerService.crearProducto(producto)){
                listaProductos.add(producto);
                mostrarMensaje("Notificación Producto", "Producto agregado", "El producto se agrego con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }else{
                mostrarMensaje("Notificación Producto", "Producto no agregado", "El producto no se agrego con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Producto", "Producto no agregado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    private Producto construirDatosProducto() {
        String tipoProducto = obtenerTipoSeleccionado();
        if(tipoProducto.equals("Moto")){
            Moto producto = new Moto(prodNombre.getText(), Float.parseFloat(prodPrecio.getText()),Integer.parseInt(prodCantidad.getText()),prodDescripcion.getText(),TipoMoto.getValue(),prodCilindraje.getText());
            return producto;
        }
        else {
            Repuesto producto = new Repuesto(prodNombre.getText(), Float.parseFloat(prodPrecio.getText()), Integer.parseInt(prodCantidad.getText()), prodDescripcion.getText());
            return producto;
        }
    }

    private void obtenerProductos() {
        listaProductos.addAll(productoControllerService.obtenerProductos());
    }

    private void actualizaProducto() {
        if(validarFormulario()){
            Producto productoActualizado = construirDatosProducto();
            if(productoControllerService.actualizarProducto(productoSeleccionado, productoActualizado)){
                actualizarTabla();
                mostrarMensaje("Notificación Producto", "Producto actualizado", "El producto se ha actualizo con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }else{
                mostrarMensaje("Notificación Producto", "Producto no actualizado", "El producto no se ha actualizado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación Producto", "Producto no actualizado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onActualizarProducto(ActionEvent event) {
        actualizaProducto();
    }

    @FXML
    void onCrearProducto(ActionEvent event) {
        agregarProducto();
    }

    private void eliminarProducto() {
        // Verificar si hay un producto seleccionado
        if (productoSeleccionado == null) {
            mostrarMensaje("Advertencia", "No se ha seleccionado ningún producto", "Por favor, selecciona un producto para eliminar.", Alert.AlertType.WARNING);
            return; // No continuar si no hay producto seleccionado
        }

        // Proceder a eliminar el producto directamente
        boolean eliminadoExitosamente = productoControllerService.eliminarProducto(productoSeleccionado);

        if (eliminadoExitosamente) {
            actualizarTabla(); // Actualizar la tabla de productos
            mostrarMensaje("Notificación Producto", "Producto eliminado", "El producto se ha eliminado con éxito.", Alert.AlertType.INFORMATION);
            limpiarCamposProducto(); // Limpiar los campos del formulario
        } else {
            mostrarMensaje("Error", "Producto no eliminado", "Hubo un problema al intentar eliminar el producto.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onEliminarProducto(ActionEvent event) {
        eliminarProducto();
    }

    @FXML
    void onHabilitarCamposMoto(ActionEvent event) {
        prodCilindraje.setDisable(false);
        TipoMoto.setDisable(false);
        cargarListaTipoMotos();
    }

    @FXML
    void onHabilitarCamposRepuesto(ActionEvent event) {
        prodCilindraje.setDisable(true);
        TipoMoto.setDisable(true);
    }

    @FXML
    void onLimpiarProducto(ActionEvent event) {
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        radioMoto.setSelected(false);
        radioRepuesto.setSelected(false);
        prodNombre.setText("");
        prodPrecio.setText("");
        prodCantidad.setText("");
        prodDescripcion.setText("");
        TipoMoto.setValue("");
        prodCilindraje.setText("");
    }

    private boolean validarFormulario() {

        if(!radioMoto.isSelected() && !radioRepuesto.isSelected()){
            return false;
        }
        if(prodNombre.getText().isEmpty()){
            return false;
        }
        if(prodPrecio.getText().isEmpty()){
            return false;
        }
        if(prodCantidad.getText().isEmpty()){
            return false;
        }
        if(prodDescripcion.getText().isEmpty()){
            return false;
        }

        if (radioMoto.isSelected()) {
            if(TipoMoto.getValue().isEmpty()){
                return false;
            }
            if(prodCilindraje.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }

    private String obtenerTipoSeleccionado() {
        if (radioMoto.isSelected()){
            return radioMoto.getText();
        }
        return radioRepuesto.getText();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void cargarListaTipoMotos() {
        List<String> listaTipoMotos = productoControllerService.listaTipoMotos();
        ObservableList<String> tipoMotos = FXCollections.observableList(listaTipoMotos);
        TipoMoto.setItems(tipoMotos);
    }

    public void listarTipoMotos(Event event) {
        cargarListaTipoMotos();
    }
}
