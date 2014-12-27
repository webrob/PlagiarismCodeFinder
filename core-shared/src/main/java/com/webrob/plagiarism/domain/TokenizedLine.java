package com.webrob.plagiarism.domain;

import java.io.File;

/**
 * Created by Robert on 2014-12-25.
 */
public class TokenizedLine
{
    public static final int MIN_VALID_LINE_LENGTH = 3;
    private final String realLine;
    private final int cleanLineHash;
    private final File file;
    private final int startChar;
    private final int endChar;
    private final int lineInFile;
    private final boolean valid;

    public TokenizedLine(String realLine, String cleanLine, File file, int start, int end, int lineInFile)
    {
	this.realLine = realLine;
	this.file = file;
	this.startChar = start;
	this.endChar = end;
	this.lineInFile = lineInFile;
	this.valid = cleanLine.length() > MIN_VALID_LINE_LENGTH;
	this.cleanLineHash = cleanLine.hashCode();
    }

    public String getRealLine()
    {
	return realLine;
    }

    public int getCleanLineHash()
    {
	return cleanLineHash;
    }

    public File getFile()
    {
	return file;
    }

    public int getStartChar()
    {
	return startChar;
    }

    public int getEndChar()
    {
	return endChar;
    }

    public int getLineInFile()
    {
	return lineInFile;
    }

    public boolean isValid()
    {
	return valid;
    }

}
