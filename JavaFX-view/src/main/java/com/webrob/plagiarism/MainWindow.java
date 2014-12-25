package com.webrob.plagiarism;

import com.webrob.plagiarism.controller.Controller;
import com.webrob.plagiarism.controller.ControllerImpl;
import com.webrob.plagiarism.model.Plagiarism;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Robert on 2014-12-21.
 */
public class MainWindow extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
	Parent root = loader.load();

	Plagiarism plagiarism = new Plagiarism();
	Controller controller = new ControllerImpl();

	MainWindowController mainWindowController = loader.getController();
	mainWindowController.setStage(stage);
	mainWindowController.setController(controller);

	controller.setViewController(mainWindowController);
	controller.setModel(plagiarism);

	new Thread(plagiarism).start();

	Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
    }

    public static void main(String[] args)
    {
	launch(args);
    }

}