/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.services.request.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

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
         * {@link #isValid(List, ConstraintValidatorContext)} calls.
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