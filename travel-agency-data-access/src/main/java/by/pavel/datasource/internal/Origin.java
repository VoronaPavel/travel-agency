package by.pavel.datasource.internal;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for mysql DataSource.
 */
@Retention(RUNTIME)
@BindingAnnotation
public @interface Origin {
}
