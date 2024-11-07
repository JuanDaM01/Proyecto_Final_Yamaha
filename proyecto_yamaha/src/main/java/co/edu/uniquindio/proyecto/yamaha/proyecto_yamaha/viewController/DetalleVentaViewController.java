package co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class DetalleVentaViewController {

    @FXML
    private TextField CantidadDeProducto;

    @FXML
    private TextField PrecioVenta;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelarVenta;

    @FXML
    private Button btnPasarVenta;

    @FXML
    private TableColumn<?, ?> tcCantidad;

    @FXML
    private TableColumn<?, ?> tcPrecio;

    @FXML
    private TableColumn<?, ?> tcProducto;

    @FXML
    private ComboBox<?> tipoProducto;

    @FXML
    void onAgregar(ActionEvent event) {

    }

    @FXML
    void onCancelarVenta(ActionEvent event) {

    }

    @FXML
    void onPasarVenta(ActionEvent event) {

    }

}
