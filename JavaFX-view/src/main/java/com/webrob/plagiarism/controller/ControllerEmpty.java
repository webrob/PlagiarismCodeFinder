package com.webrob.plagiarism.controller;

import com.webrob.plagiarism.MainWindowController;
import com.webrob.plagiarism.model.Plagiarism;

/**
 * Created by Robert on 2014-12-25.
 */
public class ControllerEmpty implements Controller
{
    @Override public void setViewController(MainWindowController mainWindowController)
    {

    }

    @Override public void setModel(Plagiarism plagiarism)
    {

    }

    @Override public void findPlagiarism()
    {

    }

    @Override public void setDirectoryPath(String directoryPath)
    {

    }

    @Override public void getPlagiarismDetails(int selectedIndex)
    {

    }
}
