package com.webrob.plagiarism;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    public void setStage(Stage stage)
    {
	this.stage = stage;
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    private void chooseFirstFilePressed()
    {

    }

    @FXML
    private void chooseSecondFilePressed()
    {

    }

    @FXML
    private void startDetectionPressed()
    {

    }
}

