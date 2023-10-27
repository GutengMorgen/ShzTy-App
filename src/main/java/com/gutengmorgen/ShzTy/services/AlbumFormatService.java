package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.AlbumFormats.DTOs.AlbumFormatViewModel;
import com.gutengmorgen.ShzTy.repositories.AlbumFormatRepository;
import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public class AlbumFormatService implements MainServices<AlbumFormatViewModel>{
	private final AlbumFormatRepository albumFormatRepository = new AlbumFormatRepository();
	
	@Override
	public AlbumFormatViewModel save(InsertDTO origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlbumFormatViewModel update(InsertDTO origin, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlbumFormatViewModel> viewList() {
		return albumFormatRepository.findAll().stream().map(AlbumFormatViewModel::new).toList();
	}

	@Override
	public ReturnDTO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getIdByName(String name) {
		return albumFormatRepository.findIdByName("name", name);
	}

	@Override
	public List<String> getAllName() {
		return albumFormatRepository.findAllByName("name");
	}

	@Override
	public void delete(Long id) {
	}

}
