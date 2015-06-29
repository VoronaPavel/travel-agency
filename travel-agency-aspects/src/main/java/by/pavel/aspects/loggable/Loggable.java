package by.pavel.aspects.loggable;


import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Loggable {
    @interface Debug {}
    @interface Error {}
    @interface Info {}
}
