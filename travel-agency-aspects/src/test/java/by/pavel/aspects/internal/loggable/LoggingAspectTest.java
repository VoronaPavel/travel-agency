package by.pavel.aspects.internal.loggable;

import by.pavel.aspects.loggable.Loggable;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class LoggingAspectTest {

    @Mock LoggingHelper mock;

    public LoggingAspectTest() {
        MockitoAnnotations.initMocks(this);
        Aspects.aspectOf(LoggingAspect.class).loggingHelper = mock;
    }

    @Loggable
    private void loggableMethodThatThrowsException() throws Exception {
        throw new Exception();
    }

    @Test
    public void testDoLog() throws Exception {
        assertThatThrownBy(this::loggableMethodThatThrowsException).isExactlyInstanceOf(Exception.class);
        verify(mock, times(1)).doLog(any(JoinPoint.class), eq(this), any(Exception.class));
        verifyNoMoreInteractions(mock);
    }

}