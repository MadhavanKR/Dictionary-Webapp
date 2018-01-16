package com.dictionary.services;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("testing hello world");
//		HttpClientImplementor httpClient = new HttpClientImplementor();
//		try
//		{
//			httpClient.sendGet("https://www.dictionaryapi.com/api/v1/references/collegiate/xml/retinue?key=396338bb-5f4a-4cf4-8dc3-c04601312b19");
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		MeaningFetcher fetcher = new MeaningFetcher();
		System.out.println(fetcher.getWordMeaning("change"));
	}

}
