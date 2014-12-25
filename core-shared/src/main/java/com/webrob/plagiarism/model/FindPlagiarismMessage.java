package com.webrob.plagiarism.model;

/**
 * Created by Robert on 2014-12-25.
 */
public class FindPlagiarismMessage implements AbstractMessage
{
    @Override
    public void process(AbstractModel model)
    {
	model.calculate();
    }
}
