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
package org.geosdi.geoplatform.jaxb.validation.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GPSeverityMessage implements ValidationMessage.ISeverityMessage<GPSeverityMessage> {

    WARNING_MESSAGE(0, WARNING),
    ERROR_MESSAGE(1, ERROR),
    FATAL_ERROR_MESSAGE(2, FATAL_ERROR);

    private final Integer degree;
    private final SeverityType type;

    /**
     * @param theDegree
     * @param theType
     */
    GPSeverityMessage(Integer theDegree, SeverityType theType) {
        this.degree = theDegree;
        this.type = theType;
    }


    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getDegree() {
        return this.degree;
    }

    /**
     * @return {@link SeverityType}
     */
    @Override
    public SeverityType getType() {
        return this.type;
    }

    /**
     * @param value
     * @return {@link GPSeverityMessage}
     * @throws IllegalArgumentException
     */
    public static GPSeverityMessage fromValue(Integer value) throws IllegalArgumentException {
        switch (value) {
            case 0:
                return WARNING_MESSAGE;
            case 1:
                return ERROR_MESSAGE;
            case 2:
                return FATAL_ERROR_MESSAGE;
            default:
                throw new IllegalArgumentException("No SeverityMessage found for value : " + value);
        }
    }

    /**
     * @param value
     * @return {@link GPSeverityMessage}
     * @throws IllegalArgumentException
     */
    public static GPSeverityMessage fromValue(SeverityType value) throws IllegalArgumentException {
        switch ((value != null) ? value : ERROR) {
            case WARNING:
                return WARNING_MESSAGE;
            case ERROR:
                return ERROR_MESSAGE;
            case FATAL_ERROR:
                return FATAL_ERROR_MESSAGE;
            default:
                throw new IllegalArgumentException("No SeverityMessage found for value : " + value);
        }
    }
}