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
package org.geosdi.geoplatform.connector.server.request.kvp;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.reader.GPConnectorReader;
import org.geosdi.geoplatform.connector.server.request.GPWMSRequestKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.net.URLDecoder.decode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.function.Function.identity;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.WMSRequestKey.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSRequestKvpReader extends GPConnectorReader<GPWMSRequestKeyValuePair, String, String> {

    WMSServiceKeyValuePair WMS_SERVICE_KEY_VALUE_PAIR = new WMSServiceKeyValuePair();
    WMSRequestKeyValuePair WMS_REQUEST_KEY_VALUE_PAIR = new WMSRequestKeyValuePair();

    /**
     * @param theValue
     * @return {@link Map<String, GPWMSRequestKeyValuePair>}
     * @throws Exception
     */
    Map<String, GPWMSRequestKeyValuePair> read(@Nonnull(when = NEVER) String theValue) throws Exception;

    @ThreadSafe
    class WMSRequestKvpReader implements GPWMSRequestKvpReader {

        private static final Logger logger = LoggerFactory.getLogger(WMSRequestKvpReader.class);

        /**
         * @param theValue
         * @return {@link Map<String, GPWMSRequestKeyValuePair>}
         * @throws Exception
         */
        @Override
        public Map<String, GPWMSRequestKeyValuePair> read(@Nonnull(when = NEVER) String theValue) throws Exception {
            checkArgument(((theValue != null) && !(theValue.trim().isEmpty())), "The Parameter value must not be null.");
            logger.trace("########################{} trying to read : {}\n", this.getClass().getSimpleName(), theValue);
            String value = decode(theValue, UTF_8.name());
            int pos = value.indexOf(URL_DELIMITER.toKey());
            value = ((pos > 0) ? value.substring(pos + 1) : value);
            checkArgument(((value != null) && !(value.trim().isEmpty())), "The Parameter value after removing special characters ? must not be null.");
            return compile(CHAIN.toKey()).splitAsStream(value)
                    .filter(Objects::nonNull)
                    .filter(v -> !(v.trim().isEmpty()))
                    .map(v -> v.split(KVP_SEPARATOR.toKey()))
                    .filter(Objects::nonNull)
                    .filter(values -> values.length == 2)
                    .filter(values -> (((values[0] != null) && !(values[0].trim().isEmpty())) && ((values[1] != null) && !(values[1].trim().isEmpty()))))
                    .map(this::internalRead)
                    .collect(toMap(GPWMSRequestKey::toKey, identity(), (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        }

        /**
         * @param theValues
         * @return {@link GPWMSRequestKeyValuePair}
         */
        private GPWMSRequestKeyValuePair internalRead(String... theValues) {
            switch (theValues[0].toUpperCase()) {
                case "SERVICE" :
                    return WMS_SERVICE_KEY_VALUE_PAIR;
                case "VERSION" :
                    return new WMSVersionKeyValuePair(theValues[1]);
                case "REQUEST" :
                    return WMS_REQUEST_KEY_VALUE_PAIR;
                case "LAYERS" :
                    return new WMSLayersKeyValuePair(theValues[1]);
                case "SRS" :
                    return new WMSSrsKeyValuePair(theValues[1]);
                case "CRS" :
                    return new WMSCrsKeyValuePair(theValues[1]);
                case "WIDTH":
                    return new WMSWidthKeyValuePair(theValues[1]);
                case "HEIGHT" :
                    return new WMSHeightKeyValuePair(theValues[1]);
                case "BBOX" :
                    return new WMSBoundingBoxKeyValuePair(theValues[1]);
                case "STYLES" :
                    return new WMSStylesKeyValuePair(theValues[1]);
                default:
                    return new WMSGetMapBaseRequestKeyValuePair<String>(theValues[0].toUpperCase()) {

                        @Override
                        public String toValue() {
                            return theValues[1];
                        }
                    };
            }
        }
    }
}