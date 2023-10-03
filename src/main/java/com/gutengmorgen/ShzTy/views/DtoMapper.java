package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.Method;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

//NOTE: necesito obtener la clase del modelo para usar su find_DTOs
public class DtoMapper {
    private static String modelsPackage = "com.gutengmorgen.ShzTy.models";
    
    public static void main(String[] args) {
	
	long startTime2 = System.currentTimeMillis();

	Class<?> clazz2 = map(Artist.class, DTO_MODEL.UPDATE);
	System.out.println(clazz2);
	
	long endTime2 = System.currentTimeMillis();
	long executionTime2 = endTime2 - startTime2;

	System.out.println("Method took " + executionTime2 + " milliseconds");
    }
    
    private static String simpleExtractor(String input) {
	return(input.split("-")[1]);
    }
    
    public static Class<?> map(String entityName, DTO_MODEL model) {
	String classSimpleName = simpleExtractor(entityName);
	
	try {
	    Class<?> modelClass = ClassFinder.findClassInPackage(modelsPackage, classSimpleName);
	    return executeMethod(modelClass, model);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return null;
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
}
