package com.webrob.plagiarism.model;

import com.webrob.plagiarism.domain.PlagiarismChain;
import com.webrob.plagiarism.domain.PlagiarizedLines;
import com.webrob.plagiarism.domain.TokenizedLine;
import com.webrob.plagiarism.utils.Matrix;

import java.util.List;

/**
 * Created by Robert on 2014-12-25.
 */
public class PlagiarismFinder
{
    private final List<TokenizedLine> tokenizedLines1;
    private final List<TokenizedLine> tokenizedLines2;
    private final boolean[] linesUsed1;
    private final boolean[] linesUsed2;
    private final Matrix matrix;
    private int gapLength;

    public PlagiarismFinder(List<TokenizedLine> tokenizedLines1, List<TokenizedLine> tokenizedLines2)
    {
	this.tokenizedLines1 = tokenizedLines1;
	this.tokenizedLines2 = tokenizedLines2;
	matrix = new Matrix(tokenizedLines1.size());
	linesUsed1 = new boolean[tokenizedLines1.size()];
	linesUsed2 = new boolean[tokenizedLines2.size()];
    }

    private void markTheSameLines()
    {
	for (int x = 0; x < tokenizedLines1.size(); x++)
	{
	    TokenizedLine line1 = tokenizedLines1.get(x);
	    for (int y = x; y < tokenizedLines2.size(); y++)
	    {
		TokenizedLine line2 = tokenizedLines2.get(y);
		if ((line1.getCleanLineHash() == line2.getCleanLineHash()) && (line1.isValid()) && (line2.isValid()))
		{
		    matrix.put(x, y);
		}
	    }
	}
    }

    public void calculatePlagiarismChains(List<PlagiarismChain> plagiarismChains)
    {
	markTheSameLines();
	calculateChains(plagiarismChains);
    }

    private void calculateChains(List<PlagiarismChain> plagiarismChains)
    {
	PlagiarizedLines plagiarizedLines = matrix.getPlagiarizedLines();

	List<Integer> lineNumbersFromFile1 = plagiarizedLines.getLineNumbersFromFile1();
	List<Integer> lineNumbersFromFile2 = plagiarizedLines.getLineNumbersFromFile2();

	for (int i = 0; i < lineNumbersFromFile1.size(); i++)
	{
	    int x = lineNumbersFromFile1.get(i);
	    int y = lineNumbersFromFile2.get(i);

	    TokenizedLine tokenizedLine1 = tokenizedLines1.get(x);
	    TokenizedLine tokenizedLine2 = tokenizedLines2.get(y);

	    PlagiarismChain chain = new PlagiarismChain(tokenizedLine1, x, tokenizedLine2, y);
	    gapLength = 0;
	    extendChain(chain, x, y);

	    if (chain.isLongEnough())
	    {
		plagiarismChains.add(chain);
	    }
	    else
	    {
		markChainUnused(chain);
	    }
	}
	int aa=0;
    }

    private void markChainUnused(PlagiarismChain chain)
    {
	List<Integer> positions = chain.getPositions();
	for (int i = 0; i < positions.size(); i+=2)
	{
	    setLineUsed(positions.get(i), positions.get(i + 1), false);
	}
    }

    private void extendChain(PlagiarismChain chain, int x, int y)
    {
	setLineUsed(x, y, true);
	if (areAvailableLines(x + 1, y + 1))
	{
	    addLineToChain(chain, x + 1, y + 1);
	    return;
	}

	gapLength++;
	if (gapLength <= 2)
	{
	    if (areFreeLines(x + 1, y + 1))
	    {
		addLineToChain(chain, x + 1, y + 1);
	    }
	}
    }

    private void addLineToChain(PlagiarismChain chain, int x, int y)
    {
	chain.add(tokenizedLines1.get(x), x, tokenizedLines2.get(y), y);
	extendChain(chain, x, y);
    }

    private void setLineUsed(int x, int y, boolean used)
    {
	linesUsed1[x] = used;
	linesUsed2[y] = used;
    }

    private boolean areAvailableLines(int x, int y)
    {
	return (areCloneLines(x, y)) && (areFreeLines(x, y));
    }

    private boolean areCloneLines(int x, int y)
    {
	if ((x >= linesUsed1.length) || (y >= linesUsed2.length))
	{
	    return false;
	}
	Boolean isElement = matrix.get(x, y);
	return isElement != null;
    }

    private boolean areFreeLines(int x, int y)
    {
	if ((x >= linesUsed1.length) || (y >= linesUsed2.length))
	{
	    return false;
	}
	return (!linesUsed1[x]) && (!linesUsed2[y]);
    }
}
