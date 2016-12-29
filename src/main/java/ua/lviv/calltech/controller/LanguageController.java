package ua.lviv.calltech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.calltech.service.LanguageService;

@Controller
public class LanguageController {
	
	@Autowired
	private LanguageService langService;

	@RequestMapping(value = "/new_language", method = RequestMethod.GET)
	public String newLanguage(){
		return "lang-new";
	}
	
	@RequestMapping(value = "/new_language", method = RequestMethod.POST)
	public String createLanguage(@RequestParam("name")String name){
		langService.createLanguage(name);
		return "redirect:/all_languages";
	}
	
	@RequestMapping(value="/edit_language", method = RequestMethod.POST)
	public String editLanguage(@RequestParam("id")int id, @RequestParam("name")String name){
		langService.editLanguage(id, name);
		return "redirect:/all_languages";
	}
		
	@RequestMapping(value="/all_languages", method = RequestMethod.GET)
	public String viewAll(Model model){
		model.addAttribute("languages", langService.findAll());
		return "lang-all";
	}
	@RequestMapping(value="/delete_lang_{id}", method = RequestMethod.GET)
	public String deleteLanguage(@PathVariable int id){
		langService.deleteLanguage(id);
		return "redirect:/all_languages";
	}
}
