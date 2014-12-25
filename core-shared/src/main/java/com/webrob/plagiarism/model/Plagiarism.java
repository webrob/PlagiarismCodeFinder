package com.webrob.plagiarism.model;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Robert on 2014-12-24.
 */
public class Plagiarism extends AbstractModel implements Runnable
{
    private BlockingQueue<AbstractMessage> messageQueue;
    private String directoryPath;

    @Override
    public void calculate()
    {
	firePropertyChange(PropertyNames.PLAGIARISM_FOUND, "AAA");
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
        while(true){
            try {
                AbstractMessage message = messageQueue.take();
                message.process(this);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
