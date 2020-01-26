package no.comp.finalproject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution("
			// private/protected/public
			+ " "
			// return type
			+ " *"
			// type or package
			+ " no.comp.finalproject.controller.*"
			// method
			+ ".*(..))")
	public void logBeforePageControllerCommands(JoinPoint joinPoint) {
		for (Object obj: joinPoint.getArgs()) {
			System.out.println("PageController." + joinPoint.getSignature().getName() + ": " + obj);
		}
	}
}
