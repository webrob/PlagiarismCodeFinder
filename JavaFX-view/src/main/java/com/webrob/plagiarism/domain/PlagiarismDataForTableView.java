package com.webrob.plagiarism.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Robert on 2014-12-26.
 */
public class PlagiarismDataForTableView
{
    private StringProperty firstFile;
    private StringProperty secondFile;


    public PlagiarismDataForTableView(String firstFile, String secondFile)
    {
	this.firstFile = new SimpleStringProperty(firstFile);
	this.secondFile = new SimpleStringProperty(secondFile);
    }


    public StringProperty firstFileProperty()
    {
	return firstFile;
    }

    public StringProperty secondFileProperty()
    {
	return secondFile;
    }
}
