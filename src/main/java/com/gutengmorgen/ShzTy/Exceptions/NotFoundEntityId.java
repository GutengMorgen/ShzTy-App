package com.gutengmorgen.ShzTy.Exceptions;

public class NotFoundEntityId extends RuntimeException {

	public NotFoundEntityId(Class<?> entityClass, Long id) {
		super("No se encontro el id < " + id + " > en la clase" + entityClass.getSimpleName());
	}
}
