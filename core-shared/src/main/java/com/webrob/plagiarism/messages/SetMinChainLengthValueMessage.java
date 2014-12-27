package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.Model;

/**
 * Created by Robert on 2014-12-27.
 */
public class SetMinChainLengthValueMessage implements Message
{
    private final int value;

    public SetMinChainLengthValueMessage(int value)
    {
	this.value = value;
    }

    @Override public void process(Model model)
    {
	model.setMinChainLengthValue(value);
    }
}
