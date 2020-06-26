package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.LoadView;
import utils.Md5;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Md5.crypter("passer"));
        LoadView.showView("AUTHENTIFICATION","FormLogin.fxml", 1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
