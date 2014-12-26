package com.webrob.plagiarism.utils;

import com.webrob.plagiarism.domain.TokenizedLine;
import com.webrob.plagiarism.lexer.LexerToken;
import com.webrob.plagiarism.lexer.LexerTokenization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 2014-12-25.
 */
public class Tokenization
{
    private final File file;
    private StringBuilder tokenizedText;

    public Tokenization(File file)
    {
	this.file = file;
    }

    public List<TokenizedLine> getTokenizedLines() throws IOException
    {
	List<TokenizedLine> tokenizedLines = new ArrayList<>();
	String originalText = FileHelper.getTextFromFile(file);
	String[] originalLines = splitToSimpleLines(originalText);

	String simpleText = getTokenizedText();
	String[] simpleLines = splitToSimpleLines(simpleText);

	int position = 0;
	for (int i = 0; i < originalLines.length; i++)
	{
	    int lineLength = originalLines[i].length();
	    TokenizedLine tokenizedLine = new TokenizedLine(originalLines[i], simpleLines[i], file, position,
			    position + lineLength, i + 1);
	    tokenizedLines.add(tokenizedLine);
	    position += lineLength + 1;
	}

	return tokenizedLines;
    }

    private String[] splitToSimpleLines(String tokenizedText)
    {
	return tokenizedText.split("\r?\n", -1);
    }

    private String getTokenizedText() throws IOException
    {
	tokenizedText = new StringBuilder();
	try (FileInputStream stream = new FileInputStream(file))
	{
	    Reader reader = new InputStreamReader(stream);
	    LexerTokenization lexer = new LexerTokenization(reader);

	    while (true)
	    {
		LexerToken token = lexer.yylex();
		if (token == null)
		{
		    break;
		}
		appendTokenizedText(token, lexer);
	    }
	}
	return tokenizedText.toString();
    }

    private void appendTokenizedText(LexerToken token, LexerTokenization lexer)
    {
	switch (token)
	{
	    case PASS:
	    {
		tokenizedText.append(lexer.yytext());
		break;
	    }
	    case NEW_LINES:
	    {
		tokenizedText.append(lexer.newLines);
		break;
	    }
	    default:
	    {
		tokenizedText.append(token);
		break;
	    }
	}
    }

}
