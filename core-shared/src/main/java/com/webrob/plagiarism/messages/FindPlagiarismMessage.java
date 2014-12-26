package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.AbstractModel;

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
