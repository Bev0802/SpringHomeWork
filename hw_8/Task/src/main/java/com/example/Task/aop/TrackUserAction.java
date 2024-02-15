package com.example.Task.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)// Указывает, что аннотация должна быть доступна во время выполнения программы
public @interface TrackUserAction {
}
