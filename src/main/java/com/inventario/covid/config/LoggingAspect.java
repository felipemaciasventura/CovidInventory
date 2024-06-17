package com.inventario.covid.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.inventario.covid.web.controller.EmployeeController.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        var args = Arrays.toString(joinPoint.getArgs());
        log.info("INIT AOP LOGGING CONTROL...");
        log.debug("Run " + joinPoint.getSignature().getName() + " method execution started at " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        log.info("Entering method: {} with arguments: {} ,Class Name:{}", methodName, args, joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* com.inventario.covid.web.controller.EmployeeController.*(..))",
            returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.debug("Method: {} returned value is: {}", methodName, result);
        log.info("Exiting method: {} with result: {} ,Class Name:{}", joinPoint.getSignature().getName(), result, joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterThrowing(
            pointcut = "execution(* com.inventario.covid.web.controller.EmployeeController.*(..))",
            throwing = "exception")
    public void logOnException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().toShortString();
        log.debug("Method: {} threw exception: {}", methodName, exception.getMessage());
    }

    @After("execution(* com.inventario.covid.web.controller.EmployeeController.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.debug("Completed execution of method: {}", methodName);
    }

    @Around("execution(* com.inventario.covid.web.controller.EmployeeController.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String methodName = joinPoint.getSignature().toShortString();

        log.debug("Method: {} executed in {} ms", methodName, executionTime);
        log.info("FINISHED AOP LOGGING CONTROL...");
        return result;
    }

}
