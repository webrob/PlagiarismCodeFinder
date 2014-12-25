package com.webrob.plagiarism.controller;

import com.webrob.plagiarism.MainWindowController;
import com.webrob.plagiarism.model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Robert on 2014-12-25.
 */
public class ControllerImpl implements Controller, PropertyChangeListener
{
    private MainWindowController viewController;
    private Plagiarism model;
    private BlockingQueue<AbstractMessage> messageQueue = new SynchronousQueue<>();

    public void setViewController(MainWindowController viewController)
    {
	this.viewController = viewController;
    }

    public void setModel(Plagiarism model)
    {
	this.model = model;
	model.setMessageQueue(messageQueue);
	model.addPropertyChangeListener(this);
    }

    protected void sendMessage(AbstractMessage message)
    {
	messageQueue.offer(message);
    }

    public void findPlagiarism()
    {
	sendMessage(new FindPlagiarismMessage());
    }

    @Override public void setDirectoryPath(String directoryPath)
    {
        SetFilePathsMessage setFilePathsMessage = new SetFilePathsMessage(directoryPath);
        sendMessage(setFilePathsMessage);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String propertyName = evt.getPropertyName();
        switch (propertyName)
	{
	    case PropertyNames.PLAGIARISM_FOUND:
	    {
                String a = (String) evt.getNewValue();
                viewController.setFirstFileText(a);
		break;
	    }
	}
    }
}
