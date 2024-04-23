package com.maids.librarymanagement.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class ExecutionTimeTrackerAspect {

    @Around("@annotation(com.maids.librarymanagement.aop.annotation.TrackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);

        return result;
    }
}
