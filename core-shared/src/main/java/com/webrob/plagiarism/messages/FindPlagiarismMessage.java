package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.Model;

/**
 * Created by Robert on 2014-12-25.
 */
public class FindPlagiarismMessage implements Message
{
    @Override
    public void process(Model model)
    {
	model.calculate();
    }
}
