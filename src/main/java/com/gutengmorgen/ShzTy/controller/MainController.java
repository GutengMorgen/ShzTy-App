package com.gutengmorgen.ShzTy.controller;

import java.util.List;
import java.util.function.Function;

import com.gutengmorgen.ShzTy.services.AlbumFormatService;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.GenreService;
import com.gutengmorgen.ShzTy.services.LanguageService;
import com.gutengmorgen.ShzTy.services.PlayListService;
import com.gutengmorgen.ShzTy.services.TrackService;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.views.AllEntities;

public class MainController {

	public static MainServices<?> search(AllEntities entityName) {
        return switch (entityName) {
            case Artist ->
//	    return new ArtistService().init();
                    new ArtistService();
            case Album -> new AlbumService();
            case Track -> new TrackService();
            case Languages -> new LanguageService();
            case Genre -> new GenreService();
            case AlbumFormat -> new AlbumFormatService();
            case PlayList -> new PlayListService();
            default -> throw new RuntimeException("No se encontro la entidad relacionada. " + entityName);
        };
	}

	public static Function<String, List<String>> lookup(AllEntities entity) {
		List<String> values = search(entity).getAllName();

		Function<String, List<String>> lookup = text -> values.stream().filter(
				v -> v.toLowerCase().contains(text.toLowerCase()) && !v.equals(text)).toList();
		return lookup;
	}

	public static Long getIdByName(String name, AllEntities entity) {
		return search(entity).getIdByName(name);
	}

}
