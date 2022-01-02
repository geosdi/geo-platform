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
package org.geosdi.geoplatform.connector.geoserver.styles.sld;

import org.geosdi.geoplatform.connector.geoserver.request.styles.base.GeoserverBaseCreateStyleRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverCreateStyleSLDV100Request extends GeoserverBaseCreateStyleRequest<StyledLayerDescriptor, GeoserverCreateStyleSLDV100Request> {

    /**
     * @param theStyleName
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    GeoserverCreateStyleSLDV100Request withStyleName(@Nonnull(when = NEVER) String theStyleName);

    /**
     * @param theStyleBody
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    @Override
    GeoserverCreateStyleSLDV100Request withStyleBody(@Nonnull(when = NEVER) StyledLayerDescriptor theStyleBody);

    /**
     * @param theStringStyleBody
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    GeoserverCreateStyleSLDV100Request withStringStyleBody(@Nonnull(when = NEVER) String theStringStyleBody);

    /**
     * <p>
     *     When set to "true", will forgo parsing and encoding of the uploaded style content, and instead the style will be
     *     streamed directly to the GeoServer configuration. Use this setting if the content and formatting of the style
     *     is to be preserved exactly. May result in an invalid and unusable style if the payload is malformed.
     *     Allowable values are {@link Boolean#TRUE} or {@link Boolean#FALSE} (default). Only used when uploading a style file.
     * </p>
     *
     * @param theRaw
     * @return {@link GeoserverUpdateStyleSLDV100Request}
     */
    GeoserverCreateStyleSLDV100Request withRaw(@Nullable Boolean theRaw);

    /**
     * @param theServer
     * @return {@link GeoserverCreateStyleSLDV100Request}
     */
    static GeoserverCreateStyleSLDV100Request of(@Nonnull(when = NEVER) GPServerConnector theServer) {
        return new GPGeoserverCreateStyleSLDV100Request(theServer);
    }
}