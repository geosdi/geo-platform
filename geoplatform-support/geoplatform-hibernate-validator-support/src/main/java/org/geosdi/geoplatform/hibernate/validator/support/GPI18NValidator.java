/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.support.i18n.IGPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.IGPI18NMessageInterpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Locale;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPI18NValidator<P extends Object, MESSAGE extends Object> implements IGPI18NValidator<P, MESSAGE> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final IGPI18NMessageInterpolator messageInterpolator;
    private final javax.validation.Validator validator;

    /**
     * @param theMessageInterpolator
     */
    public GPI18NValidator(@Nonnull(when = NEVER) IGPI18NMessageInterpolator theMessageInterpolator) {
        checkArgument(theMessageInterpolator != null, "The Parameter MessageInterpoletor must not be null.");
        this.messageInterpolator = theMessageInterpolator;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        this.validator = configuration.messageInterpolator(this.messageInterpolator).buildValidatorFactory().getValidator();
    }

    /**
     * <p>Return Null, if there are no errors.</p>
     *
     * @param thePojo
     * @param locale
     * @return {@link MESSAGE}
     */
    @Override
    public MESSAGE validate(P thePojo, Locale locale) {
        logger.trace("################################ {} - Validating........ {} , with Locale : {}\n",
                getValidatorName(), thePojo, locale);
        this.messageInterpolator.setDefaultLocale(locale);
        Set<ConstraintViolation<P>> constraintViolations = this.validator.validate(thePojo);
        return constraintViolations.isEmpty() ? null : buildMessage(constraintViolations);
    }

    /**
     * <p>Return Null, if there are no errors.</p>
     *
     * @param thePojo
     * @param locale
     * @param groups
     * @return {@link MESSAGE}
     */
    @Override
    public MESSAGE validate(P thePojo, Locale locale, Class<?>... groups) {
        logger.trace("################################ {} - Validating........ {} , with Locale : {}, " +
                "with Groups : {}\n", getValidatorName(), thePojo, locale, groups);
        this.messageInterpolator.setDefaultLocale(locale);
        Set<ConstraintViolation<P>> constraintViolations = this.validator.validate(thePojo, groups);
        return constraintViolations.isEmpty() ? null : buildMessage(constraintViolations);
    }

    /**
     * @param constraintViolations
     * @return {@link MESSAGE}
     */
    protected abstract MESSAGE buildMessage(Set<ConstraintViolation<P>> constraintViolations);
}