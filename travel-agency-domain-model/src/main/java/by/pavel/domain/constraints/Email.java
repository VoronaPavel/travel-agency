package by.pavel.domain.constraints;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)

@Documented
@Target({PARAMETER, FIELD, LOCAL_VARIABLE})
public @interface Email {
}
