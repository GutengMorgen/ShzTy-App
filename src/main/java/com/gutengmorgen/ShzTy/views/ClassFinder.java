package com.gutengmorgen.ShzTy.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
    public static void main(String[] args) throws ClassNotFoundException, Exception {
	String className = "package-info";
	String packageName = "com.gutengmorgen.ShzTy.models";
//	List<Class<?>> classes = findClassesInPackage(packageName);

	/*
	 * for (Class<?> clazz : classes) { if(clazz.getSimpleName().equals(clazzName))
	 * { // System.out.println(clazz.getName());
	 * 
	 * 
	 * System.out.println(clazz.getAnnotations()); Annotation[] annotations =
	 * clazz.getAnnotations(); for (Annotation annotation : annotations) {
	 * if(annotation instanceof NotBlank) { NotBlank notBlank = (NotBlank)
	 * annotation; System.out.println("esta es mi anotacion: " +
	 * notBlank.message());
	 * 
	 * } }
	 * 
	 * } System.out.println("Clases: " + clazz.getName() + " \nsimple: " +
	 * clazz.getSimpleName()); }
	 */
	
	findClassInPackage(packageName, className);
    }

    public static Class<?> findClassInPackage(String packageName, String classSimpleName) throws Exception {
	List<Class<?>> classes = findClassesInPackage(packageName);

	for (int i = 0; i < classes.size(); i++) {
//	    System.out.println(classes.get(i));
	    if(classes.get(i).getSimpleName().equals(classSimpleName)) {
//		System.out.println(classes.get(i));
		return classes.get(i);
	    }
	}

	return null;
    }

    public static List<Class<?>> findClassesInPackage(String packageName) throws Exception {
	List<Class<?>> classes = new ArrayList<>();

	String packagePath = packageName.replace(".", "/");
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	// Busca en el classpath para encontrar archivos .class
	String classpath = System.getProperty("java.class.path");
	String[] classpathEntries = classpath.split(File.pathSeparator);

	for (String classpathEntry : classpathEntries) {
	    File entry = new File(classpathEntry);

	    if (entry.isDirectory()) {
		File packageDir = new File(entry, packagePath);
		if (packageDir.exists()) {
		    classes.addAll(findClassesInDirectory(packageName, packageDir, classLoader));
		}
	    } else if (entry.isFile() && entry.getName().endsWith(".jar")) {
		classes.addAll(findClassesInJar(packageName, entry, classLoader));
	    }
	}

	return classes;
    }

    private static List<Class<?>> findClassesInDirectory(String packageName, File directory, ClassLoader classLoader)
	    throws ClassNotFoundException {
	List<Class<?>> classes = new ArrayList<>();

	File[] files = directory.listFiles();
	for (File file : files) {
	    if (file.isDirectory()) {
		classes.addAll(findClassesInDirectory(packageName + "." + file.getName(), file, classLoader));
	    } else if (file.getName().endsWith(".class")) {
		String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
		classes.add(Class.forName(className, true, classLoader));
	    }
	}

	return classes;
    }

    private static List<Class<?>> findClassesInJar(String packageName, File jarFile, ClassLoader classLoader)
	    throws Exception, ClassNotFoundException {
	List<Class<?>> classes = new ArrayList<>();

	// Implementar búsqueda de clases en un archivo JAR aquí
	// Esto puede requerir el uso de una biblioteca como JarInputStream o JarFile

	return classes;
    }

}
