package com.dictionary.services;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dictionary.utilities.IOHelper;

@Service
public class MeaningFetcher {

	String baseUrl;
	HttpClientImplementor httpClient;
	String key;
	String tempFileName;
	IOHelper ioHelp;
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	org.w3c.dom.Document parseDoc;
	
	public MeaningFetcher()
	{
		baseUrl = "https://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
		key = "396338bb-5f4a-4cf4-8dc3-c04601312b19";
		httpClient = new HttpClientImplementor();
		ioHelp = new IOHelper();
	}
	
	private String formURL(String word)
	{
		return baseUrl+word+"?key="+key;
	}
	
	private void createTempFile(String xml) throws IOException
	{
		tempFileName = new Long(new Date().getTime()).toString();
		File tempFile = new File(tempFileName);
		if(!tempFile.exists())
		{
			tempFile.createNewFile();
		}
		ioHelp.writeToFile(xml, tempFileName);
	}
	
	private void setupParser() throws ParserConfigurationException, SAXException, IOException
	{
		docBuilder=docFactory.newDocumentBuilder();
		parseDoc =  docBuilder.parse(tempFileName);
	}
	
	private String fetchMeaning(String word)
	{
		Element root = parseDoc.getDocumentElement();
		Node defElement = root.getElementsByTagName("def").item(0);
		////System.out.println(defElements.getLength());
		if(defElement==null)
			return "<li class=\"list-group-item\"> No meaning found for this one!!! </li>";
		NodeList definitions = defElement.getChildNodes();
		StringBuilder allDefinitions = new StringBuilder("");
		int meaningCount=1;
		for(int i=0;i<definitions.getLength();i++)
		{
			Node curDef = (Node) definitions.item(i);
			if(curDef.getNodeName().equals("dt"))
				allDefinitions.append("<li class=\"list-group-item\">"+curDef.getTextContent()+"</li>");
		}
		//System.out.println(allDefinitions.toString());
		return allDefinitions.toString();
	}
	
	private void deleteTempFile()
	{
		File tempFile = new File(tempFileName);
		if(tempFile.exists())
			tempFile.delete();
	}
	
	public String getWordMeaning(String word)
	{
		String url = formURL(word);
		String xml;
		try {
			xml = httpClient.sendGet(url);
			createTempFile(xml);
			setupParser();
			String result = fetchMeaning(word);
			deleteTempFile();
			return result;
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
			return "exception : "+e.getMessage();
		}
		catch (IOException e) {
			e.printStackTrace();
			return "exception : "+e.getMessage();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return "exception : "+e.getMessage();
		} 
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception : "+e.getMessage();
		}
	}
}
