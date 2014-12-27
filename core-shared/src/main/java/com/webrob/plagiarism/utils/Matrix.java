package com.webrob.plagiarism.utils;

import com.webrob.plagiarism.domain.PlagiarizedLines;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Robert on 2014-12-25.
 */
public class Matrix
{
    private final Hashtable<Long, Boolean> values;
    private final int width;

    public Matrix(int width)
    {
	this.width = width;
	values = new Hashtable<>(width);
    }

    public PlagiarizedLines getPlagiarizedLines()
    {
	int size = values.size();
	List<Integer> lineNumbersFromFile1 = new ArrayList<>(size);
	List<Integer> lineNumbersFromFile2 = new ArrayList<>(size);

	List<Long> longs = new ArrayList<>();
	longs.add(22l);
	longs.add(44l);
	longs.add(55l);
	longs.add(66l);

        /*
	Hashtable<String, Integer> numbers
                        = new Hashtable<String, Integer>();
        Enumeration<Integer> elements = numbers.elements();
        Enumeration<String> keys = numbers.keys();
        */

	Enumeration<Long> longs1 = values.keys();

	while (longs1.hasMoreElements())
	{
	    Long key = longs1.nextElement();

	    lineNumbersFromFile1.add(0, (int) (key % width));
	    lineNumbersFromFile2.add(0, (int) (key / width));
	}

	// Collections.reverse(lineNumbersFromFile1);
	// Collections.reverse(lineNumbersFromFile2);

        /*
        for (Long key: longs)
        {
            lineNumbersFromFile1.add((int)(key % width));
            lineNumbersFromFile2.add((int)(key / width));
        }
        */

	return new PlagiarizedLines(lineNumbersFromFile1, lineNumbersFromFile2);
    }

    public void put(int x, int y)
    {
	Long key = (long) (x + width * y);
	values.put(key, true);
    }

    public Boolean get(int x, int y)
    {
	Long value = (long) (x + width * y);
	return values.get(value);
    }

}
