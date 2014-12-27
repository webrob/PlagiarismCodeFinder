package com.webrob.plagiarism.view;

import com.webrob.plagiarism.domain.PlagiarismDetails;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Created by Robert on 2014-12-26.
 */
public class TextFlowManager
{
    private final TextFlow secondFileTextFlow;
    private final TextFlow firstFileTextFlow;
    private PlagiarismDetails plagiarismDetails;

    public TextFlowManager(TextFlow firstFileTextFlow, TextFlow secondFileTextFlow)
    {
	this.firstFileTextFlow = firstFileTextFlow;
	this.secondFileTextFlow = secondFileTextFlow;
    }

    public void setPlagiarismDetailsToTextFlows(PlagiarismDetails plagiarismDetails)
    {
	this.plagiarismDetails = plagiarismDetails;

	String firstFileText = plagiarismDetails.getFirstFileText();

	System.out.println(firstFileText.substring(plagiarismDetails.getFirstFileStartPlagiarismChar(),
			plagiarismDetails.getFirstFileEndPlagiarismChar()));

	Text text1 = new Text(firstFileText.substring(0, plagiarismDetails.getFirstFileStartPlagiarismChar()));
	Text text2 = new Text(firstFileText.substring(plagiarismDetails.getFirstFileStartPlagiarismChar(),
			plagiarismDetails.getFirstFileEndPlagiarismChar()));
	text2.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
	Text text3 = new Text(firstFileText.substring(plagiarismDetails.getFirstFileEndPlagiarismChar()));

	Platform.runLater(() ->
	{
	    ObservableList<Node> children = firstFileTextFlow.getChildren();
	    children.clear();
	    children.setAll(text1, text2, text3);
	});

	setSecondFileTextFlow();

    }

    private void setSecondFileTextFlow()
    {
	String secondFile = plagiarismDetails.getSecondFileText();

	Text text1 = new Text(secondFile.substring(0, plagiarismDetails.getSecondFileStartPlagiarismChar()));
	Text text2 = new Text(secondFile.substring(plagiarismDetails.getSecondFileStartPlagiarismChar(),
			plagiarismDetails.getSecondFileEndPlagiarismChar()));
	text2.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
	Text text3 = new Text(secondFile.substring(plagiarismDetails.getSecondFileEndPlagiarismChar()));

	Platform.runLater(() ->
	{
	    ObservableList<Node> children = secondFileTextFlow.getChildren();
	    children.clear();
	    children.setAll(text1, text2, text3);
	});
    }

}
