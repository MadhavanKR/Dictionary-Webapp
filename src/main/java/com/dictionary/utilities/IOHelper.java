package com.dictionary.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class IOHelper {
	BufferedReader reader;
	BufferedWriter writer;
	
	public Properties getPropertyFile(String propFileName) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propFileName));
			return prop;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public BufferedReader getBufferedReader(String filename)
	{
		try {
			return new BufferedReader(new FileReader(new File(filename)));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedWriter getBufferedWriter(String filename)
	{
		try {
			return new BufferedWriter(new FileWriter(new File(filename)));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] readLinesIntoStringArray(String filename)
	{
		StringBuilder temp = new StringBuilder();
		String line;
		reader = getBufferedReader(filename);
		try {
			while((line=reader.readLine())!=null)
			{
				temp.append(line+"\n");
			}
			return temp.toString().split("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String readFileIntoString(String filename)
	{
		StringBuilder temp = new StringBuilder();
		String line;
		reader = getBufferedReader(filename);
		if(reader==null)
			return null;
		try {
			while((line=reader.readLine())!=null)
			{
				temp.append(line.trim()+"\r\n");
			}
			return temp.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void writeToFile(String input,String fileName)
	{
		IOHelper ioH = new IOHelper();
		File testFile = new File(fileName);
		if(testFile.exists() == false)
		{
			try {
				testFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedWriter writer = ioH.getBufferedWriter(fileName);
		try
		{
			writer.write(input);
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
		
}
