package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.AbstractModel;

/**
 * Created by Robert on 2014-12-26.
 */
public class SelectedPlagiarismMessage implements  AbstractMessage
{
    private final int selectedIndex;

    public SelectedPlagiarismMessage(int selectedIndex)
    {
	this.selectedIndex = selectedIndex;
    }

    @Override public void process(AbstractModel model)
    {
	model.fireSourceFiles(selectedIndex);
    }
}
