package com.gutengmorgen.ShzTy.views.Extras;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ForGUI {
    int Class = 0;
    String name();
    ParmType type() default ParmType.SIMPLE_TEXT;
    String useEntity() default "";
}
