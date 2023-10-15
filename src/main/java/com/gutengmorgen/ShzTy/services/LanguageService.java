package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;

public class LanguageService {
    private final LanguageRepository languageRepository = new LanguageRepository();
    
    public static void main(String[] args) {
	LanguageService service = new LanguageService();
	
	for (Object string : service.getAllLanguages()) {
	    System.out.println(string.toString());
	}
    }
    
    public List<Language> getAllLanguages(){
	return languageRepository.findAll(); 
    }
}
