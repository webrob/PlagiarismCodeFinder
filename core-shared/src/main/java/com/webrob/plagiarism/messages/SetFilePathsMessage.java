package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.AbstractModel;

/**
 * Created by Robert on 2014-12-25.
 */
public class SetFilePathsMessage implements AbstractMessage
{
    private final String directoryPath;

    public SetFilePathsMessage(String directoryPath)
    {
	this.directoryPath = directoryPath;
    }

    @Override public void process(AbstractModel model)
    {
	model.setFilePaths(directoryPath);
    }
    public String getDirectoryPath()
    {
        return directoryPath;
    }

}
