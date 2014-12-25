package com.webrob.plagiarism.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2014-12-25.
 */
public class DirectoryHelper
{
    private final String directoryPath;

    public DirectoryHelper(String directoryPath)
    {
	this.directoryPath = directoryPath;
    }

    public List<File> getFilesFromDirectory()
    {
	File directory = new File(directoryPath);
	return getFilesFromDirectory(directory);
    }

    private List<File> getFilesFromDirectory(File directory)
    {
	List<File> filesFromDirectory = new ArrayList<>();
	File[] files = directory.listFiles();
	if (files != null)
	{
	    for (File file : files)
	    {
		if (file.isDirectory())
		{
		    List<File> filesFromEmbeddedDirectory = getFilesFromDirectory(file);
		    filesFromDirectory.addAll(filesFromEmbeddedDirectory);
		}
		else if (isValidFileType(file))
		{
		    filesFromDirectory.add(file);
		}
	    }
	}

	return filesFromDirectory;
    }

    private boolean isValidFileType(File file)
    {
	String filename = file.getName();
	return filename.matches("(?i).*\\.(cpp|java)$");
    }

}
