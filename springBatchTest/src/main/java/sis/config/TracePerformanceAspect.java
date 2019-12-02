package sis.config;

import javassist.bytecode.SignatureAttribute.MethodSignature;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracePerformanceAspect {
	
	private final Logger logger = LogManager.getLogger(TracePerformanceAspect.class);
	
	@Around("execution(* sis.service.*.*(..)))")
	public Object logTracePerformanceAspect(ProceedingJoinPoint joinPoint)
			throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint
				.getSignature();
		String className = ((Signature) methodSignature).getDeclaringType()
				.getSimpleName();
		String methodName = ((Signature) methodSignature).getName();
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Execution time of " + className + "." + methodName
				+ " :: " + (end - start) + " ms");
		return result;
	}

	@Pointcut("execution(* sis.service.*.*(..))")
	public void logForAllMethods(){}
}
