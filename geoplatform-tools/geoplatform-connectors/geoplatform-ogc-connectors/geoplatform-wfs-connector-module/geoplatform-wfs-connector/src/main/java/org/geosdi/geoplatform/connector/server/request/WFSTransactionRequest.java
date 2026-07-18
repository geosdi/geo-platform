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

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * Fluent, thread-safe WFS_110 Transaction composed of a single operation. Every {@code withXxx(...)}
 * mutator stores its value in per-thread state and returns this same request, so calls can be chained and a
 * single shared instance can be configured concurrently from many threads. The read side used internally to
 * serialize the request is exposed by {@link WFSTransactionRequestState}.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface WFSTransactionRequest<T> extends IGPostConnectorRequest<T> {

    /**
     * @param theOperation the value of the transaction operation.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withOperation(@Nonnull(when = NEVER) TransactionOperation theOperation);

    /**
     * @param theTransactionIdGen the value of the {@link TransactionIdGen} idGen. Default value is GenerateNew.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withTransactionIdGen(@Nullable TransactionIdGen theTransactionIdGen);

    /**
     * @param theTypeName the value of the type name query property.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withTypeName(@Nonnull(when = NEVER) QName theTypeName);

    /**
     * @param theSRS the value of the SRS query property.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withSRS(@Nullable String theSRS);

    /**
     * Sets the value of the inputFormat property. Default value is "x-application/gml:3".
     *
     * @param theInputFormat the value of the inputFormat property.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withInputFormat(@Nullable String theInputFormat);

    /**
     * @param theFID the value of the feature ID property.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withFID(@Nullable String theFID);

    /**
     * @param theAttributes the values of the attributes property.
     * @return {@link WFSTransactionRequest<T>}
     */
    WFSTransactionRequest<T> withAttributes(@Nullable List<? extends AttributeDTO> theAttributes);

    /**
     * Releases the per-thread configuration held in the {@code ThreadLocal} state of this request. This is an
     * <b>opt-in</b> operation : it is <b>not</b> invoked automatically, because a configured request may be
     * reused across several terminal calls. Invoke it explicitly when a shared request instance is done being
     * used on a pooled thread, to avoid retaining per-thread values. Subsequent {@code withXxx(...)} calls
     * re-initialize the state.
     */
    void clearState();
}
