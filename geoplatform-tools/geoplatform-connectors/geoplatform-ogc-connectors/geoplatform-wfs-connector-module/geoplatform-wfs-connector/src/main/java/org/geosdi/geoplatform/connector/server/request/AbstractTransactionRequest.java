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
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.QName;
import java.util.List;

import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.TransactionIdGen.GENERATE_NEW;

/**
 * Thread-safe base for the WFS {@code Transaction} request. The configuration set through the fluent
 * {@code withXxx(...)} mutators of {@link WFSTransactionRequest} is kept in {@link ThreadLocal} state, so a
 * single shared instance can be configured concurrently from many threads without cross-thread clobbering;
 * the read side consumed by the transaction strategies and the stax writer is exposed via
 * {@link WFSTransactionRequestState}.
 *
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @TODO : Change Attributes type from AttributeDTO to GPFeatureDescriptor
 */
@ThreadSafe
public abstract class AbstractTransactionRequest<T, Response> extends WFSRequest<T, Response> implements WFSTransactionRequest<T>, WFSTransactionRequestState {

    private static final String DEFAULT_INPUT_FORMAT = "text/xml; subtype=gml/3.1.1";
    //
    protected final ThreadLocal<TransactionOperation> operation;
    protected final ThreadLocal<TransactionIdGen> transactionIdGen;
    protected final ThreadLocal<QName> typeName;
    protected final ThreadLocal<String> srs;
    protected final ThreadLocal<String> inputFormat;
    protected final ThreadLocal<String> fid;
    protected final ThreadLocal<List<? extends AttributeDTO>> attributes;

    public AbstractTransactionRequest(GPServerConnector server) {
        super(server);
        this.operation = withInitial(() -> null);
        this.transactionIdGen = withInitial(() -> null);
        this.typeName = withInitial(() -> null);
        this.srs = withInitial(() -> null);
        this.inputFormat = withInitial(() -> null);
        this.fid = withInitial(() -> null);
        this.attributes = withInitial(() -> null);
    }

    /**
     * @param theOperation
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withOperation(@Nonnull(when = NEVER) TransactionOperation theOperation) {
        this.operation.set(theOperation);
        return self();
    }

    /**
     * @param theTransactionIdGen
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withTransactionIdGen(@Nullable TransactionIdGen theTransactionIdGen) {
        this.transactionIdGen.set(theTransactionIdGen);
        return self();
    }

    /**
     * @param theTypeName
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withTypeName(@Nonnull(when = NEVER) QName theTypeName) {
        this.typeName.set(theTypeName);
        return self();
    }

    /**
     * @param theSRS
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withSRS(@Nullable String theSRS) {
        this.srs.set(theSRS);
        return self();
    }

    /**
     * @param theInputFormat
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withInputFormat(@Nullable String theInputFormat) {
        this.inputFormat.set(theInputFormat);
        return self();
    }

    /**
     * @param theFID
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withFID(@Nullable String theFID) {
        this.fid.set(theFID);
        return self();
    }

    /**
     * @param theAttributes
     * @return {@link WFSTransactionRequest<T>}
     */
    @Override
    public WFSTransactionRequest<T> withAttributes(@Nullable List<? extends AttributeDTO> theAttributes) {
        this.attributes.set(theAttributes);
        return self();
    }

    /**
     * @return the value of the transaction operation.
     */
    @Override
    public TransactionOperation getOperation() {
        return this.operation.get();
    }

    /**
     * @return the transactionIdGen
     */
    @Override
    public TransactionIdGen getTransactionIdGen() {
        TransactionIdGen theTransactionIdGen = this.transactionIdGen.get();
        return ((theTransactionIdGen != null) ? theTransactionIdGen : GENERATE_NEW);
    }

    /**
     * @return the value of the type name query property.
     */
    @Override
    public QName getTypeName() {
        return this.typeName.get();
    }

    /**
     * @return the value of the SRS query property.
     */
    @Override
    public String getSRS() {
        return this.srs.get();
    }

    /**
     * @return the value of the inputFormat property.
     */
    @Override
    public String getInputFormat() {
        String theInputFormat = this.inputFormat.get();
        return ((theInputFormat != null) && !(theInputFormat.isEmpty()) ? theInputFormat : DEFAULT_INPUT_FORMAT);
    }

    /**
     * @return the value of the feature ID property.
     */
    @Override
    public String getFID() {
        return this.fid.get();
    }

    /**
     * @return the value of the attributes property.
     */
    @Override
    public List<? extends AttributeDTO> getAttributes() {
        return this.attributes.get();
    }

    /**
     * @return {@link WFSTransactionRequest<T>}
     */
    protected abstract WFSTransactionRequest<T> self();

    /**
     * Opt-in release of the per-thread configuration held in the {@code ThreadLocal} state.
     */
    @Override
    public void clearState() {
        this.operation.remove();
        this.transactionIdGen.remove();
        this.typeName.remove();
        this.srs.remove();
        this.inputFormat.remove();
        this.fid.remove();
        this.attributes.remove();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "typeName=" + this.typeName.get()
                + ", srs=" + this.srs.get()
                + ", outputFormat=" + this.inputFormat.get()
                + ", FID=" + this.fid.get()
                + ", attributes=" + this.attributes.get()
                + '}';
    }
}
