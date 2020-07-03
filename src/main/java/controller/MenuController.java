package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.LoadView;

public class MenuController {

    @FXML
    void nouvelSpecialiteHandle(ActionEvent event) throws  Exception {
        LoadView.showView("AUTHENTIFICATION","FormSpecialite.fxml", 3);
    }

}
