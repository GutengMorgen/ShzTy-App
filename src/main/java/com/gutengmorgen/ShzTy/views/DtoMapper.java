package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.Method;

import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {

    public static void main(String[] args) {

	long startTime2 = System.currentTimeMillis();

	Class<?> clazz2 = map(Artist.class, DTO_MODEL.UPDATE);
	System.out.println(clazz2);

	long endTime2 = System.currentTimeMillis();
	long executionTime2 = endTime2 - startTime2;

	System.out.println("Method took " + executionTime2 + " milliseconds");
    }

    public static Class<?> map(Class<?> clazz, DTO_MODEL model) {
	return executeMethod(clazz, model);
    }

    private static Class<?> executeMethod(Class<?> modelClass, DTO_MODEL model) {
	try {
	    Method method = modelClass.getMethod("findDtoClassByModel", DTO_MODEL.class);
	    return (Class<?>) method.invoke(null, model);
	} catch (Exception ex) {
	    throw new RuntimeException("no se encontro el metodo " + ex.getMessage());
	}
    }

    public static String map(String name, Long rowId) {
	String string = "";
	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    string = se.getArtistById(rowId).toString();
	} else if (name.contains("Al")) {
	    AlbumService se = new AlbumService();
	    string = se.getAlbumById(rowId).toString();
	} else if (name.contains("Tr")) {
	    TrackService se = new TrackService();
	    string = se.getTrackById(rowId).toString();
	}

	return string;
    }
}
