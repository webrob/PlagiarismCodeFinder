package com.webrob.plagiarism;

import com.webrob.plagiarism.controller.Controller;
import com.webrob.plagiarism.controller.ControllerEmpty;
import com.webrob.plagiarism.domain.PlagiarismDataForTableView;
import com.webrob.plagiarism.domain.PlagiarismDetails;
import com.webrob.plagiarism.domain.RowSummary;
import com.webrob.plagiarism.view.TableViewManager;
import com.webrob.plagiarism.view.TextFlowManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Robert
 */
public class MainWindowController implements Initializable
{
    @FXML private TextFlow secondFileTextFlow;
    @FXML private TextFlow firstFileTextFlow;
    @FXML private TableView<PlagiarismDataForTableView> filesTableView;
    @FXML private TableColumn<PlagiarismDataForTableView, String> firstFileTableColumn;
    @FXML private TableColumn<PlagiarismDataForTableView, String> secondFileTableColumn;
    private Stage stage;
    private Controller controller = new ControllerEmpty();
    private String directoryPath;
    private ObservableList<PlagiarismDataForTableView> plagiarisms = FXCollections.observableArrayList();
    private TableViewManager tableViewManager;
    private TextFlowManager textFlowManager;

    public void setStage(Stage stage)
    {
	this.stage = stage;
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
	firstFileTableColumn.setCellValueFactory(cellData -> cellData.getValue().firstFileProperty());
	secondFileTableColumn.setCellValueFactory(cellData -> cellData.getValue().secondFileProperty());

	filesTableView.setItems(plagiarisms);
	tableViewManager = new TableViewManager(plagiarisms);
	textFlowManager = new TextFlowManager(firstFileTextFlow, secondFileTextFlow);

	filesTableView.getSelectionModel().selectedItemProperty().addListener(
			(observable, oldValue, newValue) -> {
			    if (filesTableView.getSelectionModel().getSelectedItem() != null)
			    {
				TableView.TableViewSelectionModel selectionModel = filesTableView.getSelectionModel();
				int selectedIndex = selectionModel.getSelectedIndex();
				controller.getPlagiarismDetails(selectedIndex);
			    }
			});

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
	directoryPath = "C:\\Users\\Robert\\IdeaProjects\\PlagiatDetectorTest\\src";
	if (directoryPath != null)
	{
	    controller.setDirectoryPath(directoryPath);
	    controller.findPlagiarism();
	}
    }

    public void setController(Controller controller)
    {
	this.controller = controller;
    }

    public void setPlagiarismsToTableView(List<RowSummary> rowsSummary)
    {
	tableViewManager.setDataForTableView(rowsSummary);
    }

    public void tableRowClicked()
    {
	/*
	TableView.TableViewSelectionModel selectionModel = filesTableView.getSelectionModel();
        if (selectionModel.getSelectedItem() != null)
        {
            int selectedIndex = selectionModel.getSelectedIndex();
            System.out.println(selectedIndex);
        }
        */

    }

    public void setPlagiarismDetails(PlagiarismDetails plagiarismDetails)
    {
	textFlowManager.setPlagiarismDetailsToTextFlows(plagiarismDetails);
    }
}

