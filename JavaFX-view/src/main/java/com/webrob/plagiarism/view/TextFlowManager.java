package com.webrob.plagiarism.view;

import com.webrob.plagiarism.domain.PlagiarismDetails;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Created by Robert on 2014-12-26.
 */
public class TextFlowManager
{
    private final TextFlow secondFileTextFlow;
    private final TextFlow firstFileTextFlow;

    public TextFlowManager(TextFlow secondFileTextFlow, TextFlow firstFileTextFlow)
    {
        this.secondFileTextFlow = secondFileTextFlow;
        this.firstFileTextFlow = firstFileTextFlow;
    }


    public void setPlagiarismDetailsToTextFlows(PlagiarismDetails plagiarismDetails)
    {

        String firstFileText = plagiarismDetails.getFirstFileText();
        Text text1 = new Text(firstFileText);
        //Text text2 = new Text("Some Text");
        // text2.setStyle("-fx-font-weight: bold");
        Platform.runLater(() -> firstFileTextFlow.getChildren().setAll(text1));


        String secondFileText = plagiarismDetails.getSecondFileText();
        Text text2 = new Text(secondFileText);
        //Text text2 = new Text("Some Text");
        // text2.setStyle("-fx-font-weight: bold");
        Platform.runLater(() -> secondFileTextFlow.getChildren().setAll(text2));

    }

}
