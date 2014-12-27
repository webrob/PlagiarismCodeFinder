package com.webrob.plagiarism.domain;

import com.webrob.plagiarism.utils.Settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2014-12-25.
 */
public class PlagiarismChain
{
    private final List<Integer> positions = new ArrayList<>(40);
    public CodeClone[] codeClones = new CodeClone[2];

    public PlagiarismChain(TokenizedLine line1, int lineNumber1, TokenizedLine line2, int lineNumber2)
    {
	codeClones[0] = new CodeClone(line1);
	codeClones[1] = new CodeClone(line2);

	add(line1, lineNumber1, line2, lineNumber2);
    }

    public PlagiarismChain add(TokenizedLine line1, int lineNumber1, TokenizedLine line2, int lineNumber2)
    {
	positions.add(lineNumber1);
	positions.add(lineNumber2);

	codeClones[0].endChar = line1.getEndChar();
	codeClones[1].endChar = line2.getEndChar();

	codeClones[0].endLineInFile = line1.getLineInFile();
	codeClones[1].endLineInFile = line2.getLineInFile();

	return this;
    }

    public List<Integer> getPositions()
    {
	return positions;
    }

    public boolean isLongEnough()
    {
	return length() >= Settings.minChainLength;
    }

    public int length()
    {
	int width = codeClones[0].endLineInFile - codeClones[0].startLineInFile + 1;
	int height = codeClones[1].endLineInFile - codeClones[1].startLineInFile + 1;
	return Math.min(width, height);
    }

    public class CodeClone
    {
	public final File file;
	public int startChar;
	public int endChar;
	public int startLineInFile;
	public int endLineInFile;

	private CodeClone(TokenizedLine line)
	{
	    file = line.getFile();
	    startChar = line.getStartChar();
	    endChar = line.getEndChar();
	    startLineInFile = (endLineInFile = line.getLineInFile());
	}

	public String toString()
	{
	    return file.getName() + " (" + startLineInFile + "-" + endLineInFile +")";
	}
    }
}
