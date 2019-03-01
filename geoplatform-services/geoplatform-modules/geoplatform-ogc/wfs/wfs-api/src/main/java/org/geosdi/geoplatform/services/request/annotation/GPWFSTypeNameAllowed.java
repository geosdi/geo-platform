package org.geosdi.geoplatform.services.request.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Documented
@Constraint(validatedBy = GPWFSTypeNameAllowed.GPWFSTypeNameAllowedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPWFSTypeNameAllowed {

    /**
     * @return {@link String}
     */
    String message() default "{wfs_type_name_allowed}";

    /**
     * @return {@link Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * @return {@link Class<? extends   Payload  >[]}}
     */
    Class<? extends Payload>[] payload() default {};

    class GPWFSTypeNameAllowedValidator implements ConstraintValidator<GPWFSTypeNameAllowed, String> {

        /**
         * Implements the validation logic.
         * The state of {@code value} must not be altered.
         * <p>
         * This method can be accessed concurrently, thread-safety must be ensured
         * by the implementation.
         *
         * @param value   object to validate
         * @param context context in which the constraint is evaluated
         * @return {@code false} if {@code value} does not pass the constraint
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return ((value != null) && !(value.trim().isEmpty()) ? value.contains(":") : TRUE);
        }
    }
}
