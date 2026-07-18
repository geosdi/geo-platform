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

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.util.List;

import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;

/**
 * Thread-safe base for the WFS {@code DescribeFeatureType} request. The configuration set through the fluent
 * {@code withXxx(...)} mutators is kept in {@link ThreadLocal} state, so a single shared instance can be
 * configured concurrently from many threads without cross-thread clobbering; the concrete subclass reads the
 * per-thread values directly from the {@link ThreadLocal} fields when building the request.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public abstract class AbstractDescribeFeatureTypeRequest<T, Request> extends WFSRequest<T, Request> implements WFSDescribeFeatureTypeRequest<T> {

    protected final ThreadLocal<List<QName>> typeName;
    protected final ThreadLocal<String> outputFormat;

    /**
     * @param server
     */
    protected AbstractDescribeFeatureTypeRequest(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server);
        this.typeName = withInitial(() -> null);
        this.outputFormat = withInitial(() -> null);
    }

    /**
     * @param theTypeName
     * @return {@link WFSDescribeFeatureTypeRequest<T>}
     */
    @Override
    public WFSDescribeFeatureTypeRequest<T> withTypeName(@Nonnull(when = NEVER) List<QName> theTypeName) {
        this.typeName.set(theTypeName);
        return self();
    }

    /**
     * @param theOutputFormat
     * @return {@link WFSDescribeFeatureTypeRequest<T>}
     */
    @Override
    public WFSDescribeFeatureTypeRequest<T> withOutputFormat(@Nullable String theOutputFormat) {
        this.outputFormat.set(theOutputFormat);
        return self();
    }

    /**
     * @return {@link WFSDescribeFeatureTypeRequest<T>}
     */
    protected abstract WFSDescribeFeatureTypeRequest<T> self();

    /**
     * Opt-in release of the per-thread configuration held in the {@code ThreadLocal} state.
     */
    @Override
    public void clearState() {
        this.typeName.remove();
        this.outputFormat.remove();
    }

    @Override
    public String toString() {
        return getClass()
                .getSimpleName() + "{\n"
                + "typeName = " + this.typeName.get()
                + "\n, outputFormat = " + this.outputFormat.get()
                + "\n}";
    }
}