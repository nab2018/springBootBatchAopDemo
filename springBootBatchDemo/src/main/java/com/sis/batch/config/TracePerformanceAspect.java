package com.sis.batch.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracePerformanceAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.sis.batch.config.BatchConfig.*(..))")
	public Object logTraceJdbcTemplatePerformanceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Batch execution time of : " + className + "." + methodName + " :: " + (end - start) + " ms");
		return result;
	}
}
