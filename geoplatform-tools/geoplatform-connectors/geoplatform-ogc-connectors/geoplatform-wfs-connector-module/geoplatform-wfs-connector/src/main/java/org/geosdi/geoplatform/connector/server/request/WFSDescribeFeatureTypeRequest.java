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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * Fluent, thread-safe request to configure and send a WFS {@code DescribeFeatureType}. Every
 * {@code withXxx(...)} mutator stores its value in per-thread state and returns this same request, so calls
 * can be chained and a single shared instance can be configured concurrently from many threads.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSDescribeFeatureTypeRequest<T> extends IGPostConnectorRequest<T> {

    /**
     * @param theTypeName the value of the typeName property.
     * @return {@link WFSDescribeFeatureTypeRequest<T>}
     */
    WFSDescribeFeatureTypeRequest<T> withTypeName(@Nonnull(when = NEVER) List<QName> theTypeName);

    /**
     * Sets the value of the outputFormat property. Default value is "text/xml; subtype=gml/3.1.1".
     *
     * @param theOutputFormat the value of the outputFormat property.
     * @return {@link WFSDescribeFeatureTypeRequest<T>}
     */
    WFSDescribeFeatureTypeRequest<T> withOutputFormat(@Nullable String theOutputFormat);

    /**
     * Releases the per-thread configuration held in the {@code ThreadLocal} state of this request. This is an
     * <b>opt-in</b> operation : it is <b>not</b> invoked automatically, because a configured request may be
     * reused across several terminal calls. Invoke it explicitly when a shared request instance is done being
     * used on a pooled thread, to avoid retaining per-thread values. Subsequent {@code withXxx(...)} calls
     * re-initialize the state.
     */
    void clearState();
}