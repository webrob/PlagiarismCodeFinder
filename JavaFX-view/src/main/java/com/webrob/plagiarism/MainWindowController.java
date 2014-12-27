package com.webrob.plagiarism;

import com.webrob.plagiarism.controller.Controller;
import com.webrob.plagiarism.controller.ControllerEmpty;
import com.webrob.plagiarism.domain.PlagiarismDataForTableView;
import com.webrob.plagiarism.domain.PlagiarismDetails;
import com.webrob.plagiarism.domain.RowSummary;
import com.webrob.plagiarism.utils.DirectoryPath;
import com.webrob.plagiarism.utils.Settings;
import com.webrob.plagiarism.view.TableViewManager;
import com.webrob.plagiarism.view.TextFlowManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
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
    @FXML private Label directoryPathLabel;
    @FXML private Label minChainLengthLabel;
    @FXML private ScrollBar minChainLengthScrollBar;
    @FXML private Label maxLineGapLabel;
    @FXML private ScrollBar maxLineGapsScrollBar;
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

	updateDirectoryPathLabel();

	minChainLengthScrollBar.setValue(Settings.minChainLength);
	setMinChainLengthLabel(Settings.minChainLength);

	minChainLengthScrollBar.valueProperty().addListener((observable, oldValue, newValue) ->
	{
	    int i = newValue.intValue();
	    setMinChainLengthLabel(i);
            controller.setMinChainLengthValue(i);
	});

	maxLineGapsScrollBar.setValue(Settings.maxLineBias);
	setMaxLineGapLabel(Settings.maxLineBias);

	maxLineGapsScrollBar.valueProperty().addListener((observable, oldValue, newValue) ->
	{
	    int i = newValue.intValue();
	    setMaxLineGapLabel(i);
            controller.setMaxLineGapValue(i);
	});
    }

    private void setMinChainLengthLabel(int value)
    {
	setLabelValue(minChainLengthLabel, value);
    }

    private void setMaxLineGapLabel(int value)
    {
	setLabelValue(maxLineGapLabel, value);
    }

    private void setLabelValue(Label label, int value)
    {
	String s = String.valueOf(value);
	label.setText(s);
    }

    private void updateDirectoryPathLabel()
    {
	directoryPath = DirectoryPath.getPath();
	directoryPathLabel.setText(DirectoryPath.getPath());
    }

    private String selectFilePathFromFileChooser()
    {
	DirectoryChooser chooser = new DirectoryChooser();
	chooser.setTitle("Choose folder with source code");
	chooser.setInitialDirectory(new File(DirectoryPath.getPath()));
	File selectedDirectory = chooser.showDialog(stage);
	if (selectedDirectory != null)
	{
	    directoryPath = selectedDirectory.getAbsolutePath();
	    DirectoryPath.setPath(directoryPath);
	    updateDirectoryPathLabel();
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
	controller.setDirectoryPath(directoryPath);
	controller.findPlagiarism();
    }

    public void setController(Controller controller)
    {
	this.controller = controller;
    }

    public void setPlagiarismsToTableView(List<RowSummary> rowsSummary)
    {
	tableViewManager.setDataForTableView(rowsSummary);
    }


    public void setPlagiarismDetails(PlagiarismDetails plagiarismDetails)
    {
	textFlowManager.setPlagiarismDetailsToTextFlows(plagiarismDetails);
    }
}

