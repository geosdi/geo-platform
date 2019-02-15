package org.geosdi.geoplatform.services.request.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Documented
@Constraint(validatedBy = GPWMSGetFeatureInfoLayersAllowed.GPWMSGetFeatureInfoLayersAllowedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPWMSGetFeatureInfoLayersAllowed {

    /**
     * @return {@link String}
     */
    String message() default "{wms_get_feature_info_layers_each_value_not_empty}";

    /**
     * @return {@link Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * @return {@link Class<? extends  Payload >[]}}
     */
    Class<? extends Payload>[] payload() default {};

    class GPWMSGetFeatureInfoLayersAllowedValidator implements ConstraintValidator<GPWMSGetFeatureInfoLayersAllowed, List<String>> {

        /**
         * Initializes the validator in preparation for
         * {@link #isValid(Object, ConstraintValidatorContext)} calls.
         * The constraint annotation for a given constraint declaration
         * is passed.
         * <p>
         * This method is guaranteed to be called before any use of this instance for
         * validation.
         * <p>
         * The default implementation is a no-op.
         *
         * @param constraintAnnotation annotation instance for a given constraint declaration
         */
        @Override
        public void initialize(GPWMSGetFeatureInfoLayersAllowed constraintAnnotation) {
        }

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
        public boolean isValid(List<String> value, ConstraintValidatorContext context) {
            if (value == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{wms_get_feature_info_layers_allowed_not_null}")
                        .addConstraintViolation();
                return FALSE;
            }
            return value.stream().allMatch(layer -> ((layer != null) && !(layer.trim().isEmpty())));
        }
    }
}