package by.pavel.aspects.internal.loggable;

import by.pavel.aspects.async.Async;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.BasicConfigurator;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoggingHelperImpl implements LoggingHelper {

    private final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    LoggingHelperImpl() {
        BasicConfigurator.configure();
    }

    @Async
    @Override
    public void doLog(JoinPoint joinPoint, Object o, Exception e) {
        logger.debug("{} when executing {} Error message: {}", ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE), MoreObjects.toStringHelper(joinPoint).add("Method:", joinPoint.getSignature()).add("Args: ", joinPoint.getArgs()), e.getMessage());
    }
}
