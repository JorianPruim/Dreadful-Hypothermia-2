package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.FIELD})
public @interface TemporaryHack {
    String value();
}
