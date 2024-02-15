package com.example.Task.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// Определение аспекта для отслеживания действий пользователя
@Aspect
@Component
public class UserActionAspect {

    // Определение pointcut, целевых точек, к которым будет применяться аспект
    @Pointcut("@annotation(com.example.Task.aop.TrackUserAction)")
    public void trackUserAction() {}

    // Метод аспекта, выполняемый после вызова метода, аннотированного @TrackUserAction
    @After("trackUserAction()")
    public void afterCallingTrackedMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        // Вывод информации о вызванном методе и его аргументах в консоль
        System.out.println("Действия пользователя отслеживаются: " + methodName + " вызывается с аргументами " + args);
    }
}
