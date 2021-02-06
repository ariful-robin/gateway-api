package com.musalasoft.gateways.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = IpValidator.class)
public @interface ValidIp {
    /** Default message. */
    String message() default "IP address is not valid!! ";

    /** Default class group. */
    Class<?>[] groups() default {};

    /** class playlod. */
    Class<? extends Payload>[] payload() default {};
}
