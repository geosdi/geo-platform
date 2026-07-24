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
package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * Fluent, thread-safe request to configure and send a WFS {@code GetFeature}. Every {@code withXxx(...)}
 * mutator stores its value in per-thread state and returns this same request, so calls can be chained and a
 * single shared instance can be configured concurrently from many threads without clobbering. The read side
 * used internally to build the request is exposed by {@link WFSGetFeatureRequestState}.
 *
 * @param <T>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface WFSGetFeatureRequest<T> extends WFSStatefulRequest<T, WFSGetFeatureRequest<T>> {

    /**
     * @param theTypeName the value of the type name query property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withTypeName(@Nonnull(when = NEVER) QName theTypeName);

    /**
     * @param theFeatureIDs the value of the feature ID query property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withFeatureIDs(@Nullable List<String> theFeatureIDs);

    /**
     * @param thePropertyNames the Property Names to retrieve in {@link org.geosdi.geoplatform.xml.wfs.v110.QueryType} query.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withPropertyNames(@Nullable List<String> thePropertyNames);

    /**
     * @param theBBox the value of the BBox query property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withBBox(@Nullable BBox theBBox);

    /**
     * @param theGeometryName the geometry attribute name.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withGeometryName(@Nullable String theGeometryName);

    /**
     * @param theSRS the value of the SRS query property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withSRS(@Nullable String theSRS);

    /**
     * Sets the value of the resultType property. The only admissible parameters are {@code results} and
     * {@code hits}. Default value is {@code results}.
     *
     * @param theResultType the value of the resultType property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withResultType(@Nullable String theResultType);

    /**
     * Sets the value of the outputFormat property. Default value is {@link WFSGetFeatureOutputFormat#GML_311}.
     *
     * @param theOutputFormat the value of the outputFormat property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withOutputFormat(@Nullable GPWFSGetFeatureOutputFormat theOutputFormat);

    /**
     * Sets the value of the maxFeatures property. There is no default value defined and the absence of the
     * attribute means that all feature type instances in the result should be returned to the client.
     *
     * @param theMaxFeatures the value of the maxFeatures property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withMaxFeatures(@Nullable BigInteger theMaxFeatures);

    /**
     * @param theQueryDTO {@link QueryDTO} class contains all Restrictions for Attributes.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withQueryDTO(@Nullable QueryDTO theQueryDTO);

    /**
     * @param theCqlFilter the value of the cql filter property.
     * @return {@link WFSGetFeatureRequest<T>}
     */
    WFSGetFeatureRequest<T> withCqlFilter(@Nullable String theCqlFilter);
}
