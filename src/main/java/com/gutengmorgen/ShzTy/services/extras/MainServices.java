package com.gutengmorgen.ShzTy.services.extras;

import java.util.List;

public interface MainServices<R extends ReturnDTO> {
	R save(InsertDTO origin);

	R update(InsertDTO origin, Long id);
	
	void delete(Long id);

	List<R> viewList();

	ReturnDTO getById(Long id);

	Long getIdByName(String name);
	
	List<String> getAllName();
	
}
