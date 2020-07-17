package controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Service;
import model.Specialite;
import service.IParametrage;
import service.ParametrageDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class searchSpecialiteController implements Initializable {
    @FXML
    private ComboBox<Service> serviceCbx;
    @FXML
    private TableColumn<Specialite, String> colLibelle;

    @FXML
    private TableColumn<Specialite, String> colService;

    @FXML
    private TableColumn<Specialite, String> colId;

    @FXML
    private TableView<Specialite> specialiteTbv;

    private IParametrage iParametrage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iParametrage = new ParametrageDAO();
        List<Service> services = iParametrage.findAllServices();
        serviceCbx.setItems(FXCollections.observableArrayList(services));

        colLibelle.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getLibelle()));
        colService.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getService().getLibelle()));
        colId.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getId()+""));

        List<Specialite> specialites = iParametrage.findAllSpecialites();
        specialiteTbv.setItems(FXCollections.observableArrayList(specialites));

        serviceCbx.getSelectionModel().selectedItemProperty().addListener(
                (opt, newValue, oldValue)->{
                    final List<Specialite> lesspecialites = iParametrage.findSpecialitesByServiceId(serviceCbx.getValue().getId());
                    specialiteTbv.setItems(FXCollections.observableArrayList(lesspecialites));
                }
        );

    }
}
