package com.webrob.plagiarism.domain;

import java.util.List;

/**
 * Created by Robert on 2014-12-26.
 */
public class PlagiarizedLines
{
    private final List<Integer> lineNumbersFromFile2;
    private final List<Integer> lineNumbersFromFile1;

    public PlagiarizedLines(List<Integer> lineNumbersFromFile1, List<Integer> lineNumbersFromFile2)
    {
	this.lineNumbersFromFile1 = lineNumbersFromFile1;
	this.lineNumbersFromFile2 = lineNumbersFromFile2;
    }
    public List<Integer> getLineNumbersFromFile2()
    {
        return lineNumbersFromFile2;
    }

    public List<Integer> getLineNumbersFromFile1()
    {
        return lineNumbersFromFile1;
    }
}
