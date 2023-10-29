package com.gutengmorgen.ShzTy.Exceptions;

public class UnsupportedDtoTypeException extends RuntimeException {
    private static final long serialVersionUID = 6950837726684270709L;
    
    public UnsupportedDtoTypeException(Class<?> expectedType, Class<?> actualType) {
	super("Expected " + expectedType.getName() + " but received " + actualType.getName());
    }

}
