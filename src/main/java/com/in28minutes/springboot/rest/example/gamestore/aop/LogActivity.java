package com.in28minutes.springboot.rest.example.gamestore.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogActivity {
	String activity();
	boolean isAuthenticated() default true;
}
