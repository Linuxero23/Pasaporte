module pasaporte {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens co.poli.edu.actividad1.controlador to javafx.fxml;
    exports co.poli.edu.actividad1.controlador;

    opens co.poli.edu.actividad1.vista to javafx.fxml;
    exports co.poli.edu.actividad1.vista;

    exports co.poli.edu.actividad1.repositorio;

}