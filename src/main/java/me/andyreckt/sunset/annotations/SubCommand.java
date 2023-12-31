package me.andyreckt.sunset.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {

    String[] names();

    String permission() default "";

    boolean async() default false;

    String description() default "";

    String usage() default "none";
}
