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
package org.geosdi.geoplatform.connector.geoserver.request.classify;

import org.geosdi.geoplatform.connector.geoserver.model.classify.GeoserverMethod;
import org.geosdi.geoplatform.connector.geoserver.model.rasterize.GeoserverRamp;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.GPJacksonJAXBXmlSupport;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.JacksonJAXBXmlSupport;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverClassifyRequest extends GPJsonConnectorRequest<String, GeoserverClassifyRequest> {

    JacksonJAXBXmlSupport JACKSON_JAXB_XML_SUPPORT = new GPJacksonJAXBXmlSupport();

    /**
     *
     * @param theVectorName
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withVectorName(@Nonnull(when = When.NEVER) String theVectorName);

    /**
     * @param theAttribute
     * @return
     */
    GeoserverClassifyRequest withAttribute(@Nonnull(when = When.NEVER) String theAttribute);

    /**
     * @param theGeoserverRamp
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withGeoserverRamp(@Nonnull(when = When.NEVER) GeoserverRamp theGeoserverRamp);

    /**
     * @param theIntervals
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withIntervals(@Nonnull(when = When.NEVER) Integer theIntervals);

    /**
     * @param theMethod
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withMethod(@Nonnull(when = When.NEVER) GeoserverMethod theMethod);

    /**
     * @param theOpen
     * @return
     */
    GeoserverClassifyRequest withOpen(@Nonnull(when = When.NEVER) Boolean theOpen);

    /**
     * @param theReverse
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withReverse(@Nonnull(when = When.NEVER) Boolean theReverse);

    /**
     * @param theNormalize
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withNormalize(@Nonnull(when = When.NEVER) Boolean theNormalize);

    /**
     * @param theStartColor
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withStartColor(@Nonnull(when = When.NEVER) String theStartColor);

    /**
     * @param theEndColor
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withEndColor(@Nonnull(when = When.NEVER) String theEndColor);

    /**
     * @param theMidColor
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withMidColor(@Nonnull(when = When.NEVER) String theMidColor);

    /**
     * @param theSize
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withSize(@Nonnull(when = When.NEVER) Boolean theSize);

    /**
     * @param theSymbol
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withSymbol(@Nonnull(when = When.NEVER) String theSymbol);

    /**
     * @param theFormat
     * @return {@link GeoserverClassifyRequest}
     */
    GeoserverClassifyRequest withFormat(@Nonnull(when = When.NEVER) String theFormat);
}