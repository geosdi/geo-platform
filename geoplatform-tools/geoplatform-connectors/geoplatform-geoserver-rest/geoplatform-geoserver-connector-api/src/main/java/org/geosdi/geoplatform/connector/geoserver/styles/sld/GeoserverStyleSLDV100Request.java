/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import org.geosdi.geoplatform.connector.geoserver.request.styles.base.GeoserverBaseStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.GPJacksonJAXBXmlSupport;
import org.geosdi.geoplatform.support.jackson.xml.jaxb.JacksonJAXBXmlSupport;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverStyleSLDV100Request extends GeoserverBaseStyleRequest<StyledLayerDescriptor, GeoserverStyleSLDV100Request> {

    JacksonJAXBXmlSupport JACKSON_JAXB_XML_SUPPORT = new GPJacksonJAXBXmlSupport();

    /**
     * @param theStyleName
     * @return {@link GeoserverStyleSLDV100Request}
     */
    @Override
    GeoserverStyleSLDV100Request withStyleName(@Nonnull(when = NEVER) String theStyleName);

    /**
     * @param theServer
     * @return {@link GeoserverStyleSLDV100Request}
     */
    static GeoserverStyleSLDV100Request of(@Nonnull(when = NEVER) GPServerConnector theServer) {
        return new GPGeoserverStyleSLDV100Request(theServer);
    }
}