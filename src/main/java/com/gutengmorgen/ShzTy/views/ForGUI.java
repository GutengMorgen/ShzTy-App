package com.gutengmorgen.ShzTy.views;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ForGUI {
    int Class = 0;
    String name();
    PropertieType type() default PropertieType.SIMPLE_TEXT;
    String shareClass() default "";
}