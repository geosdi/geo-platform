package org.geosdi.geoplatform.connector.parser;

import org.geojson.Geometry;
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
    <GeoJsonGeometry extends Geometry> GeoJsonGeometry parse(@Nonnull(when = NEVER) XMLStreamReader theReader) throws Exception;

    interface GPWMSSRSParser {

        /**
         * @param gmlGeometry
         * @param jtsGeometry
         * @throws Exception
         */
        void parseSRS(@Nonnull(when = NEVER) AbstractGeometryType gmlGeometry, @Nonnull(when = NEVER) org.locationtech.jts.geom.Geometry jtsGeometry) throws Exception;

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
                            final MessageFormat format = new MessageFormat(pattern);
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
                            logger.trace("DefaultSRSBaseParser - Parser "
                                    + "Exception @@@@@@@@@@@@@@@@@ " + e);
                        }
                    }

                    if (jtsGeometry.getUserData() != null) {
                        throw new IllegalStateException(MessageFormat.format("Could not parse SRS name [{0}].", srsName));
                    } else {
                        jtsGeometry.setUserData(srsName);
                    }
                }
            }
        }
    }
}