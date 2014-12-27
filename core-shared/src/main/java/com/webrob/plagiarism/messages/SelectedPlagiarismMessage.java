package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.Model;

/**
 * Created by Robert on 2014-12-26.
 */
public class SelectedPlagiarismMessage implements Message
{
    private final int selectedIndex;

    public SelectedPlagiarismMessage(int selectedIndex)
    {
	this.selectedIndex = selectedIndex;
    }

    @Override public void process(Model model)
    {
	model.fireSourceFiles(selectedIndex);
    }
}
