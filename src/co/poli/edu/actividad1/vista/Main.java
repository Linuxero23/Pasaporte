package co.poli.edu.actividad1.vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {
    private PasaporteRepositorio repositorio = new PasaporteRepositorio();


    @Override
    public void start(Stage primaryStage) {
        mostrarMenuPrincipal(primaryStage);
    }

    private void mostrarMenuPrincipal(Stage primaryStage) {
        Label titulo = new Label("Menú Principal - Gestión de Pasaportes");

        Button btnInsertar = new Button("Insertar");
        btnInsertar.setOnAction(e -> insertar(primaryStage));

        Button btnEliminar = new Button("Eliminar");
        //btnEliminar.setOnAction(e -> eliminar());

        Button btnActualizar = new Button("Actualizar");
        //btnActualizar.setOnAction(e -> actualizar());

        Button btnLeer = new Button("Leer");
        //btnLeer.setOnAction(e -> leer());

        VBox root = new VBox(10, titulo, btnInsertar, btnEliminar, btnActualizar, btnLeer);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestión de Pasaportes");
        primaryStage.show();
    }
    private void insertar(Stage primaryStage) {
        TextField idField = new TextField();
        idField.setPromptText("Nombre completo");

        TextField fechaField = new TextField();
        fechaField.setPromptText("Fecha de Expedición");

        TextField paisField = new TextField();
        paisField.setPromptText("País");

        Button guardarBtn = new Button("Guardar Pasaporte");

        guardarBtn.setOnAction(e -> {
            Pasaporte pasaporte = new Pasaporte(
                    idField.getText(),
                    fechaField.getText(),
                    paisField.getText()
            );

            String resultado = repositorio.insertar(pasaporte);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, resultado);
            alert.showAndWait();
        });
        VBox root = new VBox(10, idField, fechaField, paisField, guardarBtn);
        root.setPadding(new Insets(20));
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("Gestión de Pasaportes");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
