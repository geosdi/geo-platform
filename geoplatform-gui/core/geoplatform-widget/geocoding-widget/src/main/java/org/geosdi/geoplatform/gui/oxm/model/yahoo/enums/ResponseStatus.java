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
package org.geosdi.geoplatform.gui.oxm.model.yahoo.enums;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class ResponseStatus {

    public enum EnumResponseStatus {
    	
        STATUS_NO_ERROR("No error"),
        STATUS_FEATURE_NOT_SUPPORTED("Feature not supported"),
        STATUS_NO_INPUT_PARAMETERS("No input parameters"),
        STATUS_ADDRESS_DATA_NOT_RECOGNIZED_AS_VALID_UTF8("Address data not recognized as valid UTF-8"),
        STATUS_INSUFFICIENT_ADDRESS_DATA("Insufficient address data"),
        STATUS_UNKNOWN_LANGUAGE("Unknown language"),
        STATUS_NO_COUNTRY_DETECTED("No country detected"),
        STATUS_COUNTRY_NOT_SUPPORTED("Country not supported"),
        STATUS_INTERNAL_PROBLEM_DETECTED("Internal problem detected"),
        //
        CODE_NO_ERROR("0"),
        CODE_FEATURE_NOT_SUPPORTED("1"),
        CODE_NO_INPUT_PARAMETERS("100"),
        CODE_ADDRESS_DATA_NOT_RECOGNIZED_AS_VALID_UTF8("102"),
        CODE_INSUFFICIENT_ADDRESS_DATA("103"),
        CODE_UNKNOWN_LANGUAGE("104"),
        CODE_NO_COUNTRY_DETECTED("105"),
        CODE_COUNTRY_NOT_SUPPORTED("106"),
        CODE_INTERNAL_PROBLEM_DETECTED("10NN"),
        //
        NO_DATA("NO_DATA");

        private String value;

        EnumResponseStatus(String value) {
            this.value = value;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }
}
