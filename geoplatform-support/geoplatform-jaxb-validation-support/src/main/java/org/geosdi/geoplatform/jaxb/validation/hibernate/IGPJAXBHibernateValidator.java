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

import jakarta.validation.ConstraintViolation;
import org.geosdi.geoplatform.hibernate.validator.support.base.IGPBaseValidator;
import org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.GPValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;
import java.io.Serializable;
import java.util.function.Function;

import static org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage.WARNING_MESSAGE;
import static org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage.fromValue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJAXBHibernateValidator<P extends IGPJAXBHibernateValidator.GPJAXBValidationModel> extends IGPBaseValidator<P, ValidationMessageStore> {

    Function<ConstraintViolation<GPJAXBValidationModel>, ValidationMessage> VALIDATION_MESSAGE_FUNCTION = new ValidationMessageFunction<>();

    /**
     * @param <P>
     */
    class ValidationMessageFunction<P extends GPJAXBValidationModel> implements Function<ConstraintViolation<P>, ValidationMessage> {

        /**
         * Applies this function to the given argument.
         *
         * @param theConstraintViolation the function argument
         * @return the function result
         */
        @Override
        public ValidationMessage apply(ConstraintViolation<P> theConstraintViolation) {
            GPSeverityMessage severityMessage = fromValue((ISeverityType.SeverityType) theConstraintViolation
                    .getConstraintDescriptor().getAttributes().get("severityType"));
            return new GPValidationMessage(theConstraintViolation.getMessage(), (severityMessage != null) ? severityMessage : WARNING_MESSAGE);
        }
    }

    interface GPJAXBValidationModel extends Serializable {
    }
}