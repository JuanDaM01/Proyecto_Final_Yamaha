module co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;


    opens co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha to javafx.fxml;
    exports co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha;
    exports co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController;
    opens co.edu.uniquindio.proyecto.yamaha.proyecto_yamaha.viewController to javafx.fxml;


}