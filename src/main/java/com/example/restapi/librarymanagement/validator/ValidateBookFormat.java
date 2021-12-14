package com.example.restapi.librarymanagement.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BookFormatValidator.class)
public @interface ValidateBookFormat {

    String message() default "Invalid Book Format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
