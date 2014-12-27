package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.Model;

/**
 * Created by Robert on 2014-12-27.
 */
public class SetMaxLineGapValueMessage implements Message
{
    private final int value;

    public SetMaxLineGapValueMessage(int value)
    {
	this.value = value;
    }

    @Override
    public void process(Model model)
    {
	model.setMaxLineGapValue(value);
    }
}
