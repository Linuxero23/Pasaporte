package app;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Pasaporte;
import repositorio.PasaporteRepositorio;
public class Main extends Application {
    private PasaporteRepositorio repositorio = new PasaporteRepositorio();

    @Override
    public void start(Stage primaryStage) {

        TextField idField = new TextField();
        idField.setPromptText("ID del Pasaporte");

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
