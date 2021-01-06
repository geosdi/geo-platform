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
package org.geosdi.geoplatform.jaxb.validation.hibernate;

import org.geosdi.geoplatform.hibernate.validator.support.GPBaseValidator;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.GPValidationMessageStore;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;
import static javax.validation.Validation.buildDefaultValidatorFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBHibernateValidator extends GPBaseValidator<IGPJAXBHibernateValidator.GPJAXBValidationModel, ValidationMessageStore>
        implements IGPJAXBHibernateValidator<IGPJAXBHibernateValidator.GPJAXBValidationModel> {

    private static final Logger logger = LoggerFactory.getLogger(GPJAXBHibernateValidator.class);
    //
    private final Validator validator;

    public GPJAXBHibernateValidator() {
        this.validator = buildDefaultValidatorFactory().getValidator();
    }

    /**
     * @param thePojo
     * @param groups
     * @return {@link ValidationMessageStore}
     */
    @Override
    public ValidationMessageStore validate(GPJAXBValidationModel thePojo, Class<?>... groups) {
        logger.trace("################################ {} - Validating ----------> {}, for Groups : {}\n",
                getValidatorName(), thePojo, groups);
        return this.buildMessage(this.validator.validate(thePojo, groups));
    }

    /**
     * @param constraintViolations
     * @return {@link ValidationMessageStore}
     */
    @Override
    protected ValidationMessageStore buildMessage(Set<ConstraintViolation<GPJAXBValidationModel>> constraintViolations) {
        checkNotNull(constraintViolations, "The Parameter Set<ConstraintViolation<?>> must not be null");
        return new GPValidationMessageStore(constraintViolations.stream()
                .map(VALIDATION_MESSAGE_FUNCTION)
                .filter(validationMessage -> validationMessage != null)
                .collect(toList()));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValidatorName() {
        return "GP_JAXB_HIBERNATE_VALIDATOR";
    }
}
