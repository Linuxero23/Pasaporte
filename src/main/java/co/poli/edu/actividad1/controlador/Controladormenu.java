package co.poli.edu.actividad1.controlador;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import co.poli.edu.actividad1.servicios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controladormenu {

    public Button btt8;
    @FXML
    private Button btt1; // Crear

    @FXML
    private Button btt2; // Actualizar

    @FXML
    private Button btt3; // Eliminar

    @FXML
    private Button btt4; // Consultar uno

    @FXML
    private Button btt5; // Consultar todos

    @FXML
    private Button btt6;

    @FXML
    private Button btt7;

    @FXML
    private Button bttCadena; // NUEVO botón para probar la cadena

    @FXML
    private SplitMenuButton split;

    @FXML
    private TextField txt1; // ID

    @FXML
    private TextField txt2; // Nombre o titular

    @FXML
    private TreeView<EspacioGeografico> treePaises;

    ObservableList<Memento> mementos = FXCollections.observableArrayList();
    @FXML
    private ListView<Memento> flist;
    @FXML
    private TextField txt3; // Misión o motivo de viaje

    @FXML
    private Label lblCadenaResultado; // NUEVO label para mostrar resultado

    private EspacioGeografico cur;

    private final PasaporteRepositorio repo = new PasaporteRepositorio();

    private String tipoSeleccionado = "";

    public void showTree() {
        Region raiz = new Region("Colombia");
        Region region1 = new Region("Andina");
        Region region2 = new Region("Orinoquia");
        Region region3 = new Region("Cundinamarca");
        Region region4 = new Region("Tolima");
        Region region5 = new Region("Meta");
        AdaptadorCiudad ciudad1 = new AdaptadorCiudad(new Ciudad("1", "Bogota"));
        AdaptadorCiudad ciudad2 = new AdaptadorCiudad(new Ciudad("2", "Chia"));
        AdaptadorCiudad ciudad3 = new AdaptadorCiudad(new Ciudad("3", "Acacias"));
        AdaptadorCiudad ciudad4 = new AdaptadorCiudad(new Ciudad("4", "Villavicencio"));
        AdaptadorCiudad ciudad5 = new AdaptadorCiudad(new Ciudad("5", "Ibague"));
        AdaptadorCiudad ciudad6 = new AdaptadorCiudad(new Ciudad("6", "Mariquita"));
        AdaptadorCiudad ciudad7 = new AdaptadorCiudad(new Ciudad("7", "Cartagena"));
        raiz.add(region1);
        raiz.add(region2);
        raiz.add(ciudad7);
        region1.add(region3);
        region1.add(region4);
        region2.add(region5);
        region3.add(ciudad1);
        region3.add(ciudad2);
        region5.add(ciudad3);
        region5.add(ciudad4);
        region4.add(ciudad5);
        region4.add(ciudad6);

        TreeItem<EspacioGeografico> rootItem = new TreeItem<>(raiz);
        rootItem.setExpanded(true);
        treePaises.setRoot(rootItem);

        treePaises.setOnMouseClicked(event -> {
            TreeItem<EspacioGeografico> selectedItem = treePaises.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                EspacioGeografico eg = selectedItem.getValue();
                cur = eg;
                if (eg instanceof Region) {
                    if (selectedItem.getChildren().isEmpty()) {
                        Region region = (Region) eg;
                        for (EspacioGeografico hijo : region.getChildren()) {
                            selectedItem.getChildren().add(new TreeItem<>(hijo));
                        }
                    } else {
                        selectedItem.getChildren().clear();
                    }
                }
            }
        });
    }

    @FXML
    void select(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        tipoSeleccionado = item.getText();
        split.setText(tipoSeleccionado);
    }

    boolean ini = true;

    @FXML
    void Click(ActionEvent event) {
        Object source = event.getSource();
        if (source == btt1) {
            crearPasaporte();
        } else if (source == btt2) {
            actualizarPasaporte();
        } else if (source == btt3) {
            eliminarPasaporte();
        } else if (source == btt4) {
            consultarPasaporte();
        } else if (source == btt5) {
            consultarTodos();
        } else if (source == btt6) {
            showTree();
        } else if (source == btt7) {
            if (ini) {
                init();
                ini = false;
            }
            guardar();
        } else if (source == btt8) {
            restaurar();
        } else if (source == bttCadena) { // NUEVO botón
            probarCadena(event);
        }
    }

    CareTaker CT;
    AdaptadorPasaporte aapp = new AdaptadorPasaporte(null);
    String codigo, nombre, mision, tipo;

    private void init() {
        mementos = FXCollections.observableArrayList();
        flist.setItems(mementos);
        flist.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Memento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    int index = getIndex() + 1;
                    setText("Estado #" + index);
                }
            }
        });
    }

    private void restaurar() {
        int indice = flist.getSelectionModel().getSelectedIndex();
        if (indice == -1) {
            mostrarAlerta("Error", "No seleccionaste ningún elemento para restaurar");
            return;
        }
        Pasaporte p = aapp.restore(CT.undo(indice));
        txt1.setText(p.getId());
        txt2.setText(p.getTitular());
    }

    private void guardar() {
        boolean flag = false;
        if (CT == null) {
            CT = new CareTaker();
            flag = true;
        }
        if (tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de pasaporte.");
            return;
        }
        Pasaporte pasaporte;
        if (tipoSeleccionado.equals("Ordinario")) {
            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId(txt1.getText());
            po.setTitular(txt2.getText());
            po.setFechaEx("14/09/2025");
            po.setPais(cur.toString());
            po.setRazonDeViaje(txt3.getText());
            pasaporte = po;
        } else {
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId(txt1.getText());
            pd.setTitular(txt2.getText());
            pd.setFechaEx("14/09/2025");
            pd.setPais(cur.toString());
            pd.setMision(txt3.getText());
            pasaporte = pd;
        }
        AdaptadorPasaporte ap = new AdaptadorPasaporte(pasaporte);
        CT.add(ap.save());
        mementos.clear();
        mementos.addAll(CT.getHistory());
        if (flag)
            crearPasaporte();
        else
            actualizarPasaporte();
    }

    private void crearPasaporte() {
        if (tipoSeleccionado == null || tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de pasaporte.");
            return;
        }

        if (cur == null) {
            mostrarAlerta("Error", "Debe seleccionar un país o región en el árbol.");
            return;
        }

        Pasaporte pasaporte;

        String tipo = tipoSeleccionado.trim().toLowerCase(); // 🔥 normalizamos el texto

        if (tipo.contains("ordinario")) {
            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId(txt1.getText());
            po.setTitular(txt2.getText());
            po.setFechaEx("14/09/2025");
            po.setPais(cur.toString());
            po.setRazonDeViaje(txt3.getText());
            pasaporte = po;
        } else if (tipo.contains("diplom")) { // 🔥 detecta "Diplomático" o "Diplomatico"
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId(txt1.getText());
            pd.setTitular(txt2.getText());
            pd.setFechaEx("14/09/2025");
            pd.setPais(cur.toString());
            pd.setMision(txt3.getText()); // ✅ ahora se guarda la misión
            pasaporte = pd;
        } else if (tipo.contains("emerg")) {
            PasaporteEmergencia pe = new PasaporteEmergencia();
            pe.setId(txt1.getText());
            pe.setTitular(txt2.getText());
            pe.setFechaEx("14/09/2025");
            pe.setPais(cur.toString());
            pasaporte = pe;
        } else {
            mostrarAlerta("Error", "Tipo de pasaporte no reconocido: " + tipoSeleccionado);
            return;
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

        pas.setTitular(txt2.getText());
        pas.setPais(cur.toString());
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

    // ======= NUEVO MÉTODO: probar la cadena de responsabilidad =======
    @FXML
    private void probarCadena(ActionEvent event) {
        if (tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Seleccione un tipo de pasaporte antes de probar la cadena.");
            return;
        }

        Pasaporte pasaporte;
        if (tipoSeleccionado.equals("Ordinario")) {
            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId("TEST001");
            po.setTitular("Ordinario");
            po.setPais("Colombia");
            pasaporte = po;
        } else if (tipoSeleccionado.equals("Diplomático")) {
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId("TEST002");
            pd.setTitular("Ejemplo Diplomático");
            pd.setPais("Colombia");
            pasaporte = pd;
        } else {
            PasaporteEmergencia pe = new PasaporteEmergencia();
            pe.setId("TEST003");
            pe.setTitular("Ejemplo Emergencia");
            pe.setPais("Colombia");
            pasaporte = pe;
        }

        PasaporteService servicio = new PasaporteService();
        String resultado = servicio.procesarPasaporte(pasaporte);

        lblCadenaResultado.setText("Resultado de la cadena: " + resultado);
    }
}
