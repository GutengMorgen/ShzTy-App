package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.models.Languages.DTOs.LanguageViewModel;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;
import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public class LanguageService implements MainServices<LanguageViewModel> {
	private final LanguageRepository languageRepository = new LanguageRepository();

	public List<Language> getAllLanguages() {
		return languageRepository.findAll();
	}

	@Override
	public LanguageViewModel save(InsertDTO origin) {
		return null;
	}

	@Override
	public LanguageViewModel update(InsertDTO origin, Long id) {
		return null;
	}

	@Override
	public List<LanguageViewModel> viewList() {
		return null;
	}

	@Override
	public ReturnDTO getById(Long id) {
		return null;
	}

	@Override
	public Long getIdByName(String name) {
		return languageRepository.findIdByName("name", name);
	}

	@Override
	public List<String> getAllName() {
		return languageRepository.findAllByName("name");
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
