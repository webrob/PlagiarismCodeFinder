package com.webrob.plagiarism;

import com.webrob.plagiarism.controller.Controller;
import com.webrob.plagiarism.controller.ControllerEmpty;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Robert
 */
public class MainWindowController implements Initializable
{
    @FXML private TextArea secondFileTextArea;
    @FXML private TableView filesTableView;
    @FXML private TableColumn firstFileTableColumn;
    @FXML private TableColumn secondFileTableColumn;
    @FXML private TextArea firstFileTextArea;
    private Stage stage;
    private Controller controller = new ControllerEmpty();
    private String directoryPath;

    public void setStage(Stage stage)
    {
	this.stage = stage;
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
    }

    private String selectFilePathFromFileChooser()
    {
	DirectoryChooser chooser = new DirectoryChooser();
	chooser.setTitle("Choose folder with source code");
	File selectedDirectory = chooser.showDialog(stage);
	if (selectedDirectory != null)
	{
	    directoryPath = selectedDirectory.getAbsolutePath();
	}
	return directoryPath;
    }

    @FXML
    private void chooseDirectoryPressed()
    {
	directoryPath = selectFilePathFromFileChooser();
	System.out.println(directoryPath);
    }

    @FXML
    private void startDetectionPressed()
    {
	directoryPath = "C:\\Users\\Robert\\IdeaProjects\\PlagiatDetectorTest\\src\\testFiles";

	controller.setDirectoryPath(directoryPath);
	controller.findPlagiarism();
    }

    public void setController(Controller controller)
    {
	this.controller = controller;
    }

    public void setFirstFileText(String text)
    {
	Platform.runLater(() -> firstFileTextArea.setText(text));
    }

}

