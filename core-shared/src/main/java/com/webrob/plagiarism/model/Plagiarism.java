package com.webrob.plagiarism.model;

import com.webrob.plagiarism.domain.PlagiarismChain;
import com.webrob.plagiarism.domain.TokenizedLine;
import com.webrob.plagiarism.utils.DirectoryHelper;
import com.webrob.plagiarism.utils.Tokenization;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Robert on 2014-12-24.
 */
public class Plagiarism extends AbstractModel implements Runnable
{
    private List<PlagiarismChain> plagiarismChains = new ArrayList<>();
    private BlockingQueue<AbstractMessage> messageQueue;
    private String directoryPath;

    @Override
    public void calculate()
    {
	plagiarismChains.clear();
	DirectoryHelper directoryHelper = new DirectoryHelper(directoryPath);
	List<File> filesFromDirectory = directoryHelper.getFilesFromDirectory();

	int size = filesFromDirectory.size();

	try
	{
	    int i = 0;
	    for (File file1 : filesFromDirectory)
	    {
		Tokenization tokenization1 = new Tokenization(file1);
		List<TokenizedLine> tokenizedLines1 = tokenization1.getTokenizedLines();

		for (int j = i + 1; j < size; j++)
		{
		    File file2 = filesFromDirectory.get(j);
		    Tokenization tokenization2 = new Tokenization(file2);
		    List<TokenizedLine> tokenizedLines2 = tokenization2.getTokenizedLines();
		    findPlagiarism(tokenizedLines1, tokenizedLines2);
		}
		i++;
	    }
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}

	firePropertyChange(PropertyNames.PLAGIARISM_FOUND, "");
    }

    private void findPlagiarism(List<TokenizedLine> tokenizedLines1, List<TokenizedLine> tokenizedLines2)
    {
	PlagiarismFinder finder = new PlagiarismFinder(tokenizedLines1, tokenizedLines2);
	finder.calculatePlagiarismChains(plagiarismChains);
    }

    @Override public void setFilePaths(String directoryPath)
    {
	this.directoryPath = directoryPath;
    }

    public void setMessageQueue(BlockingQueue<AbstractMessage> messageQueue)
    {
	this.messageQueue = messageQueue;
    }

    @Override public void run()
    {
	while (true)
	{
	    try
	    {
		AbstractMessage message = messageQueue.take();
		message.process(this);
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace();
	    }
	}
    }
}
