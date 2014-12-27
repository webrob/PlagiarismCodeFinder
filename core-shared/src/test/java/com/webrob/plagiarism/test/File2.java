package com.webrob.plagiarism.test;

import com.webrob.plagiarism.model.Plagiarism;

/**
 * Created by Robert on 2014-12-27.
 */
public class File2
{
    private Plagiarism testName;
    private Boolean name1;
    private Boolean name2;
    private Boolean name3;

    public void fun(Plagiarism plagiarism)
    {
	plagiarism.calculate();
	plagiarism.run();
	plagiarism.setFilePaths(null);
    }
}