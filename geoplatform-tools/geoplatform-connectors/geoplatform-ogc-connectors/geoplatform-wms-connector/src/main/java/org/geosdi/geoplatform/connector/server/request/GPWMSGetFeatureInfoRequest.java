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
package org.geosdi.geoplatform.connector.server.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoRequest<T, R extends GPWMSGetFeatureInfoRequest> extends GPConnectorRequest<T> {

    String WMS_GET_FEATURE_INFO_BASE_REQUEST = "${start}SERVICE=WMS&REQUEST=GetFeatureInfo&VERSION=${version}&${MAP_REQUEST_COPY}&QUERY_LAYERS=${query_layers}&INFO_FORMAT=${info_format}&X=${x}&Y=${y}&FEATURE_COUNT=${feature_count}";

    /**
     * @param theWMSGetMapRequest
     * @param <WMSGetMapRequest>
     * @return {@link R}
     */
    <WMSGetMapRequest extends GPWMSGetMapBaseRequest> R withWMSGetMapRequest(@Nonnull(when = When.NEVER) WMSGetMapRequest theWMSGetMapRequest);

    /**
     * @param theQueryLayers
     * @return {@link R}
     */
    R withQueryLayers(@Nonnull(when = When.NEVER) String... theQueryLayers);

    /**
     * @param theInfoFormat
     * @return {@link R}
     */
    R withInfoFormat(@Nonnull(when = When.NEVER) WMSFeatureInfoFormat theInfoFormat);

    /**
     * @param theX
     * @return {@link R}
     */
    R withX(@Nonnull(when = When.NEVER) Integer theX);

    /**
     * @param theY
     * @return {@link R}
     */
    R withY(@Nonnull(when = When.NEVER) Integer theY);

    /**
     * @param theFeatureCount if null default value is 1.
     * @return {@link R}
     */
    R withFeatureCount(@Nullable Integer theFeatureCount);
}