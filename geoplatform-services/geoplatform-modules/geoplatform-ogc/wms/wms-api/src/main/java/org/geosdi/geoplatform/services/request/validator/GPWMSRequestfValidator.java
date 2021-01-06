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
package org.geosdi.geoplatform.services.request.validator;

import jakarta.validation.ConstraintViolation;
import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpolator.IGPI18NMessageInterpolator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

import java.util.Set;

import static java.util.stream.Collectors.joining;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSRequestfValidator extends GPI18NValidator<GPI18NRequestValidator, String> {

    /**
     * @param theMessageInterpolator
     */
    public GPWMSRequestfValidator(IGPI18NMessageInterpolator theMessageInterpolator) {
        super(theMessageInterpolator);
    }

    /**
     * @param constraintViolations
     * @return {@link String}
     */
    @Override
    protected String buildMessage(Set<ConstraintViolation<GPI18NRequestValidator>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .filter(message -> ((message != null) && !(message.isEmpty())))
                .distinct()
                .collect(joining("\n"));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValidatorName() {
        return "GP_WMS_REQUEST_VALIDATOR";
    }
}