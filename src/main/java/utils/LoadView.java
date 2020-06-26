package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadView
{

	private static Parent root;
    private static BorderPane rootBorder;

	private static int type;

	private static Scene scene;
	private static Stage stage;
	private static String title;
	private static String name;

	private static void initStatge()
	{
		if(stage==null){
			stage=new Stage();
		}
	}

	public static void showView(String titre,String nom, int letype) throws IOException
	{
		title=titre;
		name=nom;
		type = letype;
		initStatge();
		Load(type);
	}


	private static void Load(int type) throws IOException
	{
		if(type == 1){
			root=FXMLLoader.load(LoadView.class.getResource("/views/"+name)); //load a simple view
			scene=new Scene(root);
		}
		else if(type == 2) // load borderpane
		{
			rootBorder=FXMLLoader.load(LoadView.class.getResource("/views/"+name));
			scene=new Scene(rootBorder);
		}
		else{
            root=FXMLLoader.load(LoadView.class.getResource("/views/"+name)); //load a simple view
            if(rootBorder != null){
                rootBorder.setCenter(root);
            }
            else{
                scene=new Scene(root);
            }
        }
		stage.setScene(scene);
		stage.setTitle(title);
		stage.centerOnScreen();
		showed();
	}

	private static void showed()
	{
		stage.show();
	}

	public static Stage getStage()
	{
		return stage;
	}


}
