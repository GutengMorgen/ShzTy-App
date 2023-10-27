package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.PlayLists.DTOs.PlayListViewModel;
import com.gutengmorgen.ShzTy.repositories.PlayListRepository;
import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public class PlayListService implements MainServices<PlayListViewModel>{
	private final PlayListRepository playListRepository = new PlayListRepository();
	
	@Override
	public PlayListViewModel save(InsertDTO origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayListViewModel update(InsertDTO origin, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayListViewModel> viewList() {
		return playListRepository.findAll().stream().map(PlayListViewModel::new).toList();
	}

	@Override
	public ReturnDTO getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getIdByName(String name) {
		return playListRepository.findIdByName("tile", name);
	}

	@Override
	public List<String> getAllName() {
		return playListRepository.findAllByName("tile");
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
