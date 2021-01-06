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
package org.geosdi.geoplatform.stax.reader.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLInputFactory;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.lang.System.clearProperty;
import static java.lang.System.setProperty;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;
import static javax.xml.stream.XMLInputFactory.IS_NAMESPACE_AWARE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum XMLInputFactoryJDKBuilder implements GPXMLInputFactoryJDKBuilder {

    JDK_BUILDER {
        /**
         * @param theProp
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F withProp(@Nonnull(when = NEVER) Map<String, Object> theProp) {
            checkArgument((theProp != null) && !(theProp.isEmpty()), "The Parameter prop must not be null or an empty Map.");
            try {
                setProperty(XML_INPUT_FACTORY_KEY, XML_INPUT_FACTORY_VALUE);
                XMLInputFactory factory = XMLInputFactory.newInstance();
                Map<String, Object> properties = theProp.entrySet().stream()
                        .filter(Objects::nonNull)
                        .filter(entry -> (entry.getKey() != null) && !(entry.getKey().trim().isEmpty()))
                        .filter(entry -> entry.getValue() != null)
                        .collect(toMap(entry -> entry.getKey(), entry -> entry.getValue()));
                checkArgument((properties != null) && !(properties.isEmpty()), "The Parameter prop must not contains null keys or null values.");
                properties.entrySet().forEach(entry -> factory.setProperty(entry.getKey(), entry.getValue()));
                logger.debug("###########################{}#Creates : {}, with Prop : \n{}\n", this.getClass().getSimpleName(), factory, theProp);
                return (F) factory;
            } finally {
                clearProperty(XML_INPUT_FACTORY_KEY);
            }
        }

        /**
         * @return {@link F}
         */
        @Override
        public <F extends XMLInputFactory> F defaultFactory() {
            try {
                setProperty(XML_INPUT_FACTORY_KEY, XML_INPUT_FACTORY_VALUE);
                XMLInputFactory factory = XMLInputFactory.newInstance();
                factory.setProperty(IS_COALESCING, TRUE);
                factory.setProperty(IS_NAMESPACE_AWARE, TRUE);
                factory.setProperty("http://java.sun.com/xml/stream/properties/report-cdata-event", TRUE);
                logger.debug("###########################{}#Creates : {}\n", this.getClass().getSimpleName(), factory);
                return (F) factory;
            } finally {
                clearProperty(XML_INPUT_FACTORY_KEY);
            }
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(XMLInputFactoryJDKBuilder.class);
}