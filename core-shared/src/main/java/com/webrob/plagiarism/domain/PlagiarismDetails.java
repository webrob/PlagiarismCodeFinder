package com.webrob.plagiarism.domain;

import com.webrob.plagiarism.utils.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Robert on 2014-12-26.
 */
public class PlagiarismDetails
{


    private String firstFileText;
    private String secondFileText;
    private int firstFileStartPlagiarismChar;
    private int secondFileStartPlagiarismChar;
    private int firstFileEndPlagiarismChar;
    private int secondFileEndPlagiarismChar;

    public PlagiarismDetails(PlagiarismChain plagiarismChain)
    {
	PlagiarismChain.CodeClone[] codeClones = plagiarismChain.codeClones;

	PlagiarismChain.CodeClone firstCodeClone = codeClones[0];
	PlagiarismChain.CodeClone secondCodeClone = codeClones[1];

	File file1 = firstCodeClone.file;
	File file2 = secondCodeClone.file;
	try
	{
	    firstFileText = FileHelper.getTextFromFile(file1);
	    secondFileText = FileHelper.getTextFromFile(file2);

	    firstFileStartPlagiarismChar = firstCodeClone.startChar;
	    firstFileEndPlagiarismChar = firstCodeClone.endChar;

	    secondFileStartPlagiarismChar = secondCodeClone.startChar;
	    secondFileEndPlagiarismChar = secondCodeClone.endChar;

	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	}
    }

    public String getFirstFileText()
    {
	return firstFileText;
    }

    public String getSecondFileText()
    {
	return secondFileText;
    }

    public int getFirstFileStartPlagiarismChar()
    {
	return firstFileStartPlagiarismChar;
    }

    public int getSecondFileStartPlagiarismChar()
    {
	return secondFileStartPlagiarismChar;
    }

    public int getFirstFileEndPlagiarismChar()
    {
	return firstFileEndPlagiarismChar;
    }

    public int getSecondFileEndPlagiarismChar()
    {
	return secondFileEndPlagiarismChar;
    }
}
