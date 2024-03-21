// PersistenceUnit.java
package persistence;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistenceUnit {
    String name() default "";
}
