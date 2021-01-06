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
package org.geosdi.geoplatform.connector.parser;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.xml.gml.v212.AbstractGeometryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import java.text.MessageFormat;
import java.text.ParseException;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGml2GeoJsonParser {

    /**
     * @param theReader
     * @param <GeoJsonGeometry>
     * @return {@link GeoJsonGeometry}
     * @throws Exception
     */
    <GeoJsonGeometry extends GeoJsonObject> GeoJsonGeometry parse(@Nonnull(when = NEVER) XMLStreamReader theReader) throws Exception;

    interface GPWMSSRSParser {

        /**
         * @param gmlGeometry
         * @param jtsGeometry
         * @throws Exception
         */
        void parseSRS(@Nonnull(when = NEVER) AbstractGeometryType gmlGeometry, @Nonnull(when = NEVER) org.locationtech.jts.geom.Geometry jtsGeometry) throws Exception;

        /**
         * @param gmlGeometry
         * @param vividiSolutionGeometry
         * @throws Exception
         */
        void parseSRS(@Nonnull(when = NEVER) AbstractGeometryType gmlGeometry, @Nonnull(when = NEVER) Geometry vividiSolutionGeometry) throws Exception;

        class WMSSRSParser implements GPWMSSRSParser {

            private static final Logger logger = LoggerFactory.getLogger(WMSSRSParser.class);
            //
            private String[] patterns = new String[]{"EPSG:{0,number,integer}",
                    "urn:ogc:def:crs:EPSG::{0,number,#}",
                    "urn:ogc:def:crs:EPSG:{1}:{0,number,#}",
                    "urn:x-ogc:def:crs:EPSG::{0,number,#}",
                    "urn:x-ogc:def:crs:EPSG:{1}:{0,number,#}",
                    "http://www.opengis.net/gml/srs/epsg.xml#{0,number,#}"};

            /**
             * @param gmlGeometry
             * @param jtsGeometry
             * @throws Exception
             */
            @Override
            public void parseSRS(@Nonnull(when = NEVER) AbstractGeometryType gmlGeometry, @Nonnull(when = NEVER) org.locationtech.jts.geom.Geometry jtsGeometry) throws Exception {
                checkNotNull(gmlGeometry, "The Gml Geometry must not be null");
                checkNotNull(jtsGeometry, "The JTS Geometry must not be null");
                String srsName = gmlGeometry.getSrsName();
                if (srsName != null) {
                    for (String pattern : patterns) {
                        try {
                            MessageFormat format = new MessageFormat(pattern);
                            Object[] codearray = format.parse(srsName);
                            if (codearray.length > 0) {
                                jtsGeometry.setSRID(((Number) codearray[0]).intValue());
                                if (jtsGeometry.getUserData() == null) {
                                    jtsGeometry.setUserData(srsName);
                                    return;
                                }
                            }
                        } catch (ParseException e) {
                            //only trace the ParserException and continues the cycle
                            logger.trace("DefaultSRSBaseParser - Parser Exception @@@@@@@@@@@@@@@@@ " + e);
                        }
                    }

                    if (jtsGeometry.getUserData() != null) {
                        throw new IllegalStateException(MessageFormat.format("Could not parse SRS name [{0}].", srsName));
                    } else {
                        jtsGeometry.setUserData(srsName);
                    }
                }
            }

            /**
             * @param gmlGeometry
             * @param vividiSolutionGeometry
             * @throws Exception
             */
            @Override
            public void parseSRS(@Nonnull(when = NEVER) AbstractGeometryType gmlGeometry, @Nonnull(when = NEVER) Geometry vividiSolutionGeometry) throws Exception {
                checkNotNull(gmlGeometry, "The Gml Geometry must not be null");
                checkNotNull(vividiSolutionGeometry, "The JTS Geometry must not be null");
                String srsName = gmlGeometry.getSrsName();
                if (srsName != null) {
                    for (String pattern : patterns) {
                        try {
                            MessageFormat format = new MessageFormat(pattern);
                            Object[] codearray = format.parse(srsName);
                            if (codearray.length > 0) {
                                vividiSolutionGeometry.setSRID(((Number) codearray[0]).intValue());
                                if (vividiSolutionGeometry.getUserData() == null) {
                                    vividiSolutionGeometry.setUserData(srsName);
                                    return;
                                }
                            }
                        } catch (ParseException e) {
                            //only trace the ParserException and continues the cycle
                            logger.trace("DefaultSRSBaseParser - Parser Exception @@@@@@@@@@@@@@@@@ " + e);
                        }
                    }

                    if (vividiSolutionGeometry.getUserData() != null) {
                        throw new IllegalStateException(MessageFormat.format("Could not parse SRS name [{0}].", srsName));
                    } else {
                        vividiSolutionGeometry.setUserData(srsName);
                    }
                }
            }
        }
    }
}