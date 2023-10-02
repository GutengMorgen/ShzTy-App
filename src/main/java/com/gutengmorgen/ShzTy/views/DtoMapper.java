package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.Method;

//NOTE: necesito obtener la clase del modelo para usar su find_DTOs
public class DtoMapper {
    private static String modelsPackage = "com.gutengmorgen.ShzTy.models";
    
    public static void main(String[] args) {
	map("table-Artists", DTO_MODEL.UPDATE);
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
    
    public static Class<?> map(String packageName, String entityName, DTO_MODEL model) {
	String classSimpleName = simpleExtractor(entityName);
	
	try {
	    Class<?> modelClass = ClassFinder.findClassInPackage(modelsPackage, classSimpleName);
	    return executeMethod(modelClass, model);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    private static Class<?> executeMethod(Class<?> modelClass, DTO_MODEL model) throws Exception{
	Method method = modelClass.getMethod("find_DTOs", DTO_MODEL.class);
	return (Class<?>) method.invoke(null, model);
    }
}
