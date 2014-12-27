package com.webrob.plagiarism.controller;

import com.webrob.plagiarism.MainWindowController;
import com.webrob.plagiarism.model.Plagiarism;

/**
 * Created by Robert on 2014-12-25.
 */
public interface Controller
{
    void setViewController(MainWindowController mainWindowController);

    void setModel(Plagiarism plagiarism);

    void findPlagiarism();

    void setDirectoryPath(String directoryPath);

    void getPlagiarismDetails(int selectedIndex);

    void setMaxLineGapValue(int value);

    void setMinChainLengthValue(int value);
}
