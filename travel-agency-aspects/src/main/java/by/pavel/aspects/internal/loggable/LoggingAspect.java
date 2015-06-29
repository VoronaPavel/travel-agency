package by.pavel.aspects.internal.loggable;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    LoggingHelper loggingHelper = new LoggingHelperImpl();

    @Pointcut("@annotation(by.pavel.aspects.loggable.Loggable)") void loggableMethod() {}

    @Pointcut("!cflowbelow(loggableMethod())") void flowControl() {}

    @AfterThrowing(pointcut = "loggableMethod() && flowControl() && this(o)", throwing = "e", argNames = "joinPoint, o, e")
    public void advice(JoinPoint joinPoint, Object o, Exception e) {
        loggingHelper.doLog(joinPoint, o, e);
    }
}
