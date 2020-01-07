package org.geosdi.geoplatform.rs.support.request.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Documented
@Constraint(validatedBy = GPPageableSize.GPPageableSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPPageableSize {

    /**
     * @return {@link Integer}
     */
    int size() default 20;

    /**
     * @return {@link String}
     */
    String message() default "{gp.pageable_size_message}";

    /**
     * @return {@link Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * @return {@link Class<? extends Payload>[]}}
     */
    Class<? extends Payload>[] payload() default {};

    class GPPageableSizeValidator implements ConstraintValidator<GPPageableSize, Integer> {

        private Integer size;

        /**
         * Initializes the validator in preparation for
         * {@link #isValid(Integer, ConstraintValidatorContext)} calls.
         * The constraint annotation for a given constraint declaration
         * is passed.
         * <p/>
         * This method is guaranteed to be called before any use of this instance for
         * validation.
         *
         * @param constraintAnnotation annotation instance for a given constraint declaration
         */
        @Override
        public void initialize(GPPageableSize constraintAnnotation) {
            this.size = constraintAnnotation.size();
        }

        /**
         * Implements the validation logic.
         * The state of {@code value} must not be altered.
         * <p/>
         * This method can be accessed concurrently, thread-safety must be ensured
         * by the implementation.
         *
         * @param value   object to validate
         * @param context context in which the constraint is evaluated
         * @return {@code false} if {@code value} does not pass the constraint
         */
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            if ((value != null) && !((value >= 1) && (value <= this.size))) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{gp.pageable_size_message}")
                        .addConstraintViolation();
                return FALSE;
            }
            return TRUE;
        }
    }
}