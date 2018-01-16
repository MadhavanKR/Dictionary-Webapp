package com.dictionary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dictionary.services.MeaningFetcher;

@Controller
public class DictionaryController {

	@Autowired
	MeaningFetcher fetcher;
	
	@RequestMapping(value = "dictionary", method = RequestMethod.GET)
	public String getHandler(ModelMap model)
	{
		return "dictionary";
	}
	
	@RequestMapping(value = "dictionary", method = RequestMethod.POST)
	public String postHandler(ModelMap model,@RequestParam String word)
	{
		word = word.trim();
		if(word.compareTo("")==0)
		{
			//model.addAttribute("errorMessage","please enter a word");
			return "redirect:dictionary?errorMessage=please enter a word";
		}
		model.addAttribute("meaning",fetcher.getWordMeaning(word));
		model.addAttribute("word",word);
		return "dictionary";
	}
}
