/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.request.extensions.rasterize;

import org.geosdi.geoplatform.connector.geoserver.model.extensions.rasterize.GeoserverRamp;
import org.geosdi.geoplatform.connector.geoserver.model.extensions.rasterize.GeoserverRasterizeType;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonConnectorRequest;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GeoserverRasterizeRequest extends GPJsonConnectorRequest<StyledLayerDescriptor, GeoserverRasterizeRequest> {

    /**
     *
     * @param theRasterName
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withRasterName(@Nonnull(when = When.NEVER) String theRasterName);

    /**
     * @param theGeoserverRamp
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withGeoserverRamp(@Nonnull(when = When.NEVER) GeoserverRamp theGeoserverRamp);

    /**
     * @param theMin
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withMin(@Nonnull(when = When.NEVER) Double theMin);

    /**
     * @param theMax
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withMax(@Nonnull(when = When.NEVER) Double theMax);

    /**
     * @param theClasses
     * @return
     */
    GeoserverRasterizeRequest withClasses(@Nonnull(when = When.NEVER) Integer theClasses);

    /**
     * @param theDigits
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withDigits(@Nonnull(when = When.NEVER) Integer theDigits);

    /**
     * @param theType
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withType(@Nonnull(when = When.NEVER) GeoserverRasterizeType theType);

    /**
     * @param theStartColor
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withStartColor(@Nonnull(when = When.NEVER) String theStartColor);

    /**
     * @param theEndColor
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withEndColor(@Nonnull(when = When.NEVER) String theEndColor);

    /**
     * @param theMidColor
     * @return {@link GeoserverRasterizeRequest}
     */
    GeoserverRasterizeRequest withMidColor(@Nonnull(when = When.NEVER) String theMidColor);
}