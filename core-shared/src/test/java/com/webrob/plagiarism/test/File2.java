package com.webrob.plagiarism.test;

import com.webrob.plagiarism.model.Plagiarism;

/**
 * Created by Robert on 2014-12-27.
 */
public class File2
{
    private double testName = 0;

    public void fun(Plagiarism plagiarism)
    {
	plagiarism.calculate();
	plagiarism.run();
	plagiarism.setFilePaths(null);
    }
}