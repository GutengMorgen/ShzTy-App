package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public class DtoMapper {
    
    public static void main(String[] args) {
//	System.out.println(nameExtrator("tests-Artists"));
//	map("table-Artists", DtoTypes.UPDATE);
	map(Artist.class, DtoTypes.CREATE);
    }
    
    private static String nameExtrator(String input) {
	String splited = input.split("-")[1];
//	System.out.println("slited: " + splited);
	
	Pattern pattern = Pattern.compile("s?$");
	Matcher matcher = pattern.matcher(splited);
	
	if (matcher.find()) {
            String result = matcher.replaceAll("");
            return result;
        } else {
            throw new RuntimeException("El nombre " + input + " no es valido");
        }
    }

    public static Class<?> map(String entityName, DtoTypes dtoType) {
	String name = nameExtrator(entityName);
	Class<?> clazz;
	try {
	    clazz = Class.forName("com.gutengmorgen.ShzTy.models.Artists." + name);
	    System.out.println("name: " + clazz.getName());
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return null;
    }

    //TODO: what about pass the entity instead to find by the name
    public static Class<?> map(Class<?> entity, DtoTypes dtoType) {
	
	try {
	    Method method = entity.getMethod("dtoRooter", DtoTypes.class);
	    Class<?> result = (Class<?>) method.invoke(null, dtoType);
	    System.out.println(result.getName());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return null;
    }
}
