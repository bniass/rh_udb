package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import service.IUser;
import service.UserDAO;
import utils.LoadView;
import utils.Md5;
import utils.Utils;

public class LoginController {

    @FXML
    private PasswordField pwdPwf;

    @FXML
    private TextField loginTfd;

    @FXML
    void conxionHandle(ActionEvent event) {
        try {
            IUser iUser = new UserDAO();
            String password = Md5.crypter(pwdPwf.getText());
            Utilisateur u = iUser.findUser(loginTfd.getText(), password);
            if(u != null){
                LoadView.showView("GESTION DES RESSOURCES HUMAINE","Menu.fxml", 2);
            }
            else{
                Utils.showMessage("AUTHENTIFICATION", "GESTION DES UTILISATEUES",
                        "Login ou password incorrect !");
            }
        }catch (Exception e){

        }
    }

}
