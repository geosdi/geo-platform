/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.primitive.string.responsibility;

import java.util.Optional;

import static java.util.Arrays.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPrimitiveParserFromStringHandler<Type extends Object> {

    /**
     * @param value
     * @return {@link Class<Type>}
     * @throws Exception
     */
    Class<Type> parseValue(String value) throws Exception;

    /**
     * @param <ParserType>
     * @return {@link ParserType}
     */
    <ParserType extends IGPPrimitiveParserFromStringType> ParserType getParserType();

    /**
     * @return {@link Class<Type>}
     */
    Class<Type> getPrimitiveType();

    /**
     * @param theSuccessor
     * @param <Successor>
     * @return
     */
    <Successor extends GPPrimitiveParserFromStringHandler> void setSuccessor(Successor theSuccessor);

    /**
     *
     */
    interface IGPPrimitiveParserFromStringType {

        /**
         * @return {@link String}
         */
        String getType();

        enum GPPrimitiveParserFromStringType implements IGPPrimitiveParserFromStringType {

            INTEGER("Interger"),
            INTEGER_ARRAY("Interger[]"),
            DOUBLE("Double"),
            DOUBLE_ARRAY("Double[]"),
            NUMBER("Number"),
            LONG("Long"),
            LONG_ARRAY("Long[]"),
            TEMPORAL("Temporal"),
            STRING("String");

            private final String type;

            /**
             * @param theType
             */
            GPPrimitiveParserFromStringType(String theType) {
                this.type = theType;
            }

            /**
             * @return {@link String}
             */
            @Override
            public String getType() {
                return this.type;
            }

            @Override
            public String toString() {
                return this.type;
            }

            /**
             * @param theType
             * @return {@link GPPrimitiveParserFromStringType}
             */
            public static GPPrimitiveParserFromStringType forType(String theType) {
                Optional<GPPrimitiveParserFromStringType> optional = stream(GPPrimitiveParserFromStringType.values())
                        .filter(v -> (theType != null) && !(theType.trim().isEmpty()))
                        .filter(v -> v.getType().equalsIgnoreCase(theType))
                        .findFirst();
                return optional.orElse(null);
            }
        }
    }
}