package com.gutengmorgen.ShzTy.repositories;

import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.repositories.extras.RepositoryBase;

public class PlayListRepository extends RepositoryBase<PlayList> {

	public PlayListRepository() {
		super(PlayList.class);
	}

}
