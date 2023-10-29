package com.gutengmorgen.ShzTy.views.Extras;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.gutengmorgen.ShzTy.views.AllEntities;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ForGUI {
    int Class = 0;
    String name();
    VarType type() default VarType.SIMPLE_TEXT;
    AllEntities useEntity() default AllEntities.None;
    boolean mandatory() default false;
}
