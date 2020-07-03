package controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Service;
import model.Specialite;
import service.IParametrage;
import service.ParametrageDAO;
import utils.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SpecialiteController implements Initializable {

    @FXML
    private Button enregistrerBtn;

    @FXML
    private ComboBox<Service> serviceCbx;

    @FXML
    private Button supprimerBtn;

    @FXML
    private TextField specialiteTfd;

    @FXML
    private TableColumn<Specialite, String> colLibelle;

    @FXML
    private TableColumn<Specialite, String> colService;

    @FXML
    private TableColumn<Specialite, String> colId;

    @FXML
    private Button modifierBtn;

    @FXML
    private Button nouveauBtn;

    @FXML
    private TableView<Specialite> specialiteTbv;

    private IParametrage iParametrage;

    @FXML
    void nouvauHandle(ActionEvent event) {
        verrou(false);
        specialiteTbv.getSelectionModel().clearSelection();
        clear();
    }
    private void clear(){
        specialiteTfd.setText("");
        serviceCbx.getSelectionModel().clearSelection();
    }

    @FXML
    void saveHandle(ActionEvent event) {
        // controle à faire
        if(iParametrage.findSpecialiteByLibelle(specialiteTfd.getText().trim().toLowerCase()) != null){
            Utils.showMessage("GESTION RH","Gestion des spécialité",
                    "Cette specialite existe déjà");
            return;
        }
        Specialite sp = new Specialite();
        sp.setLibelle(specialiteTfd.getText().trim().toLowerCase());
        sp.setService(serviceCbx.getValue());
        try {
            iParametrage.saveSpecialite(sp);
            Utils.showMessage("GESTION RH","Gestion des spécialité",
                    "Spécialité sauvegardée !");
            clear();
            specialiteTbv.getItems().add(sp);
            specialiteTbv.refresh();
        }
        catch (Exception e){
            Utils.showMessage("GESTION RH","Gestion des spécialité",
                    "Erreur : "+e.getMessage());
        }
    }

    @FXML
    void updateHandle(ActionEvent event) {

    }

    @FXML
    void deleteHandle(ActionEvent event) {

    }

    private Specialite selectedSpecialite = null;

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

        specialiteTbv.setOnMouseClicked((event)->{
            verrou(true);
            selectedSpecialite = specialiteTbv.getSelectionModel().
                    getSelectedItem();
            specialiteTfd.setText(selectedSpecialite.getLibelle());
            serviceCbx.setValue(selectedSpecialite.getService());
        });

        enregistrerBtn.setDisable(true);
        modifierBtn.setDisable(true);
        supprimerBtn.setDisable(true);
    }

    private void verrou(boolean active){
        enregistrerBtn.setDisable(active);
        modifierBtn.setDisable(!active);
        supprimerBtn.setDisable(!active);
    }
}
