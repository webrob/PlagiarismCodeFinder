package com.webrob.plagiarism.controller;

import com.webrob.plagiarism.MainWindowController;
import com.webrob.plagiarism.domain.PlagiarismDetails;
import com.webrob.plagiarism.domain.RowSummary;
import com.webrob.plagiarism.messages.*;
import com.webrob.plagiarism.model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Robert on 2014-12-25.
 */
public class ControllerImpl implements Controller, PropertyChangeListener
{
    private MainWindowController viewController;
    private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>(3);

    public void setViewController(MainWindowController viewController)
    {
	this.viewController = viewController;
    }

    public void setModel(Plagiarism model)
    {
	model.setMessageQueue(messageQueue);
	model.addPropertyChangeListener(this);
    }

    protected void sendMessage(Message message)
    {
	 messageQueue.offer(message);

    }

    public void findPlagiarism()
    {
        FindPlagiarismMessage message = new FindPlagiarismMessage();
        sendMessage(message);
    }

    @Override public void setDirectoryPath(String directoryPath)
    {
        SetFilePathsMessage message = new SetFilePathsMessage(directoryPath);
        sendMessage(message);
    }

    @Override public void getPlagiarismDetails(int selectedIndex)
    {
        SelectedPlagiarismMessage message = new SelectedPlagiarismMessage(selectedIndex);
        sendMessage(message);
    }

    @Override public void setMaxLineGapValue(int value)
    {
        SetMaxLineGapValueMessage message = new SetMaxLineGapValueMessage(value);
        sendMessage(message);
    }

    @Override public void setMinChainLengthValue(int value)
    {
        SetMinChainLengthValueMessage message = new SetMinChainLengthValueMessage(value);
        sendMessage(message);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String propertyName = evt.getPropertyName();
        switch (propertyName)
	{
	    case PropertyNames.PLAGIARISM_SUMMARY:
	    {
                List< RowSummary > rowsSummary = (List<RowSummary>)evt.getNewValue();
                viewController.setPlagiarismsToTableView(rowsSummary);
		break;
	    }
            case PropertyNames.PLAGIARISM_DETAILS:
            {
                PlagiarismDetails plagiarismDetails = (PlagiarismDetails) evt.getNewValue();
                viewController.setPlagiarismDetails(plagiarismDetails);
                break;
            }
	}
    }
}
