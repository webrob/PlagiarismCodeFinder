package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.Model;

/**
 * Created by Robert on 2014-12-25.
 */
public class SetFilePathsMessage implements Message
{
    private final String directoryPath;

    public SetFilePathsMessage(String directoryPath)
    {
	this.directoryPath = directoryPath;
    }

    @Override public void process(Model model)
    {
	model.setFilePaths(directoryPath);
    }
}
