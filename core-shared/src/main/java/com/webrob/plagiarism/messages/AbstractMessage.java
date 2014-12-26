package com.webrob.plagiarism.messages;

import com.webrob.plagiarism.model.AbstractModel;

public interface AbstractMessage
{
   void process(AbstractModel model);

}
