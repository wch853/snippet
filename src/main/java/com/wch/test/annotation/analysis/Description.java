package com.wch.test.annotation.analysis;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {

    /**
     * the description of the annotated element
     *
     * @return String
     */
    String desc() default "";

    /**
     * the info of author
     * @return String
     */
    String author();
}
