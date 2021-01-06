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
package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoV130Request<T> extends GPWMSGetFeatureInfoRequest<T, GPWMSGetFeatureInfoV130Request<T>> {

    /**
     * @param theWMSGetMapRequest
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    <WMSGetMapRequest extends GPWMSGetMapBaseRequest> GPWMSGetFeatureInfoV130Request<T> withWMSGetMapRequest(@Nonnull(when = NEVER) WMSGetMapRequest theWMSGetMapRequest);

    /**
     * @param theQueryLayers
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV130Request<T> withQueryLayers(@Nonnull(when = NEVER) String... theQueryLayers);

    /**
     * @param theInfoFormat
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV130Request<T> withInfoFormat(@Nonnull(when = NEVER) WMSFeatureInfoFormat theInfoFormat);

    /**
     * @param theX
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV130Request<T> withX(@Nonnull(when = NEVER) Integer theX);

    /**
     * @param theY
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV130Request<T> withY(@Nonnull(when = NEVER) Integer theY);

    /**
     * @param theFeatureCount if null default value is 1.
     * @return {@link GPWMSGetFeatureInfoRequest<T>}
     */
    @Override
    GPWMSGetFeatureInfoV130Request<T> withFeatureCount(@Nullable Integer theFeatureCount);
}