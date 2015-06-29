package by.pavel.aspects.internal.loggable;

import org.aspectj.lang.JoinPoint;

interface LoggingHelper {
    void doLog(JoinPoint joinPoint, Object o, Exception e);
}
