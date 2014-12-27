package com.webrob.plagiarism.domain;

/**
 * Created by Robert on 2014-12-26.
 */
public class RowSummary
{
    private final String firstFile;
    private final String secondFile;

    public RowSummary(String firstFile, String secondFile)
    {
	this.firstFile = firstFile;
	this.secondFile = secondFile;
    }

    public String getFirstFile()
    {
	return firstFile;
    }


    public String getSecondFile()
    {
	return secondFile;
    }

}