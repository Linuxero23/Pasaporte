package co.poli.edu.actividad1.controlador;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import co.poli.edu.actividad1.servicios.FactoriaPasaporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controladormenu {

    @FXML
    private Button btt1; //Crear

    @FXML
    private Button btt2; //Actualizar

    @FXML
    private Button btt3; //Eliminar

    @FXML
    private Button btt4; //Consultar uno

    @FXML
    private Button btt5; //Consultar todos

    @FXML
    private MenuItem opt1; // Diplomático

    @FXML
    private MenuItem opt2; // Ordinario

    @FXML
    private SplitMenuButton split;

    @FXML
    private TextField txt1; // ID

    @FXML
    private TextField txt2; // Nombre o titular

    @FXML
    private TextField txt3; // Misión o motivo de viaje

    private final PasaporteRepositorio repo = new PasaporteRepositorio();
    private String tipoSeleccionado = "";

    @FXML
    void select(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        tipoSeleccionado = item.getText();
        split.setText(tipoSeleccionado);
    }

    @FXML
    void Click(ActionEvent event) {
        Object source = event.getSource();

        if (source == btt1) { // Crear
            crearPasaporte();
        } else if (source == btt2) { // Actualizar
            actualizarPasaporte();
        } else if (source == btt3) { // Eliminar
            eliminarPasaporte();
        } else if (source == btt4) { // Consultar
            consultarPasaporte();
        } else if (source == btt5) { // Consultar todos
            consultarTodos();
        }
    }

    private void crearPasaporte() {
        if (tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de pasaporte.");
            return;
        }

        Pasaporte pasaporte;

        if (tipoSeleccionado.equals("Ordinario")) {

            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId(txt1.getText());
            po.setTitular(102);
            po.setFechaEx("14/09/2025");
            po.setPais(101);
            po.setRazonDeViaje(txt3.getText());
            pasaporte = po;
        } else {
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId(txt1.getText());
            pd.setTitular(102);
            pd.setFechaEx("14/09/2025");
            pd.setPais(101);
            pd.setMision(txt3.getText());
            pasaporte = pd;
        }

        String resultado = repo.insertar(pasaporte);
        mostrarAlerta("Resultado", resultado);
    }

    private void actualizarPasaporte() {
        String id = txt1.getText();
        Pasaporte pas = repo.seleccionar(id);

        if (pas == null) {
            mostrarAlerta("Error", "No existe un pasaporte con ID " + id);
            return;
        }

        if (pas instanceof PasaporteOrdinario) {
            ((PasaporteOrdinario) pas).setRazonDeViaje(txt3.getText());
        } else if (pas instanceof PasaporteDiplomatico) {
            ((PasaporteDiplomatico) pas).setMision(txt3.getText());
        }

        pas.setTitular(Integer.parseInt(txt2.getText()));
        pas.setPais(101);
        pas.setFechaEx("14/09/2025");

        String resultado = repo.actualizar(id, pas);
        mostrarAlerta("Resultado", resultado);
    }

    private void eliminarPasaporte() {
        String id = txt1.getText();
        String resultado = repo.eliminar(id);
        mostrarAlerta("Resultado", resultado);
    }

    private void consultarPasaporte() {
        String id = txt1.getText();
        Pasaporte p = repo.seleccionar(id);

        if (p == null) {
            mostrarAlerta("Consulta", "No se encontró el pasaporte con ID " + id);
        } else {
            mostrarAlerta("Consulta", p.toString());
        }
    }

    private void consultarTodos() {
        var lista = repo.seleccionarTodos();
        StringBuilder sb = new StringBuilder();
        for (Pasaporte p : lista) {
            sb.append(p.toString()).append("\n");
        }
        mostrarAlerta("Todos los pasaportes", sb.toString());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}