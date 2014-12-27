package com.webrob.plagiarism.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2014-12-26.
 */
public class PlagiarismSummary
{
    private final List<RowSummary> rowsSummary = new ArrayList<>();

    public PlagiarismSummary(List<PlagiarismChain> plagiarismChains)
    {
	for (PlagiarismChain chain : plagiarismChains)
	{
	    PlagiarismChain.CodeClone[] codeClones = chain.codeClones;
            RowSummary rowSummary = new RowSummary(codeClones[0].toString(),codeClones[1].toString());

            rowsSummary.add(rowSummary);
	}
    }

    public List<RowSummary> getRowsSummary()
    {
	return rowsSummary;
    }

}
