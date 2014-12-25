package com.webrob.plagiarism.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Robert on 2014-12-25.
 */
public class FileHelper
{
    public static String getTextFromFile(File file) throws FileNotFoundException
    {
	Scanner scanner = new Scanner(file).useDelimiter("\\A");
	return scanner.next();
    }
}
