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
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;

import javax.annotation.Nullable;
import java.math.BigInteger;

import static java.lang.ThreadLocal.withInitial;

/**
 * Thread-safe base of the GetRecords CSW_202 request. The configuration set through the fluent
 * {@code withXxx(...)} mutators of {@link CatalogGetRecordsRequest} is kept in {@link ThreadLocal} state, so a
 * single shared instance can be configured concurrently from many threads without cross-thread clobbering; the
 * read side consumed by the {@code v202} handler chain is exposed via {@link CatalogGetRecordsRequestState}.
 *
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@ThreadSafe
public abstract class CatalogGetRecords<T, Request> extends CatalogCSWRequest<T, Request> implements CatalogGetRecordsRequest<T>, CatalogGetRecordsRequestState {

    protected final ThreadLocal<ConstraintLanguage> constraintLanguage;
    protected final ThreadLocal<ConstraintLanguageVersion> constraintLanguageVersion;
    protected final ThreadLocal<String> constraint;
    protected final ThreadLocal<CatalogFinderBean> catalogFinder;
    protected final ThreadLocal<BigInteger> maxRecords;
    protected final ThreadLocal<BigInteger> startPosition;
    protected final ThreadLocal<OutputSchema> outputSchema;
    protected final ThreadLocal<String> resultType;
    protected final ThreadLocal<String> elementSetName;
    protected final ThreadLocal<TypeName> typeName;

    /**
     * @param server
     */
    public CatalogGetRecords(GPServerConnector server) {
        super(server);
        this.constraintLanguage = withInitial(() -> null);
        this.constraintLanguageVersion = withInitial(() -> null);
        this.constraint = withInitial(() -> null);
        this.catalogFinder = withInitial(() -> null);
        this.maxRecords = withInitial(() -> null);
        this.startPosition = withInitial(() -> null);
        this.outputSchema = withInitial(() -> null);
        this.resultType = withInitial(() -> null);
        this.elementSetName = withInitial(() -> null);
        this.typeName = withInitial(() -> null);
    }

    /**
     * @param theConstraintLanguage
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withConstraintLanguage(@Nullable ConstraintLanguage theConstraintLanguage) {
        this.constraintLanguage.set(theConstraintLanguage);
        return self();
    }

    /**
     * @param theConstraintLanguageVersion
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withConstraintLanguageVersion(@Nullable ConstraintLanguageVersion theConstraintLanguageVersion) {
        this.constraintLanguageVersion.set(theConstraintLanguageVersion);
        return self();
    }

    /**
     * @param theConstraint
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withConstraint(@Nullable String theConstraint) {
        this.constraint.set(theConstraint);
        return self();
    }

    /**
     * @param theCatalogFinder
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withCatalogFinder(@Nullable CatalogFinderBean theCatalogFinder) {
        this.catalogFinder.set(theCatalogFinder);
        return self();
    }

    /**
     * @param theMaxRecords
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withMaxRecords(@Nullable BigInteger theMaxRecords) {
        this.maxRecords.set(theMaxRecords);
        return self();
    }

    /**
     * @param theStartPosition
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withStartPosition(@Nullable BigInteger theStartPosition) {
        this.startPosition.set(theStartPosition);
        return self();
    }

    /**
     * @param theOutputSchema
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withOutputSchema(@Nullable OutputSchema theOutputSchema) {
        this.outputSchema.set(theOutputSchema);
        return self();
    }

    /**
     * @param theResultType
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withResultType(@Nullable String theResultType) {
        this.resultType.set(theResultType);
        return self();
    }

    /**
     * @param theElementSetName
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withElementSetName(@Nullable String theElementSetName) {
        this.elementSetName.set(theElementSetName);
        return self();
    }

    /**
     * @param theTypeName
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    @Override
    public CatalogGetRecordsRequest<T> withTypeName(@Nullable TypeName theTypeName) {
        this.typeName.set(theTypeName);
        return self();
    }

    /**
     * @return {@link ConstraintLanguage}
     */
    @Override
    public ConstraintLanguage getConstraintLanguage() {
        return this.constraintLanguage.get();
    }

    /**
     * @return {@link ConstraintLanguageVersion}
     */
    @Override
    public ConstraintLanguageVersion getConstraintLanguageVersion() {
        return this.constraintLanguageVersion.get();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getConstraint() {
        return this.constraint.get();
    }

    /**
     * @param theConstraint
     */
    @Override
    public void setConstraint(@Nullable String theConstraint) {
        this.constraint.set(theConstraint);
    }

    /**
     * @return {@link CatalogFinderBean}
     */
    @Override
    public CatalogFinderBean getCatalogFinder() {
        return this.catalogFinder.get();
    }

    /**
     * @return {@link BigInteger}
     */
    @Override
    public BigInteger getMaxRecords() {
        return this.maxRecords.get();
    }

    /**
     * @return {@link BigInteger}
     */
    @Override
    public BigInteger getStartPosition() {
        return this.startPosition.get();
    }

    /**
     * @return {@link OutputSchema}
     */
    @Override
    public OutputSchema getOutputSchema() {
        return this.outputSchema.get();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getResultType() {
        return this.resultType.get();
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getElementSetName() {
        return this.elementSetName.get();
    }

    /**
     * @return {@link TypeName}
     */
    @Override
    public TypeName getTypeName() {
        return this.typeName.get();
    }

    /**
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    protected abstract CatalogGetRecordsRequest<T> self();

    /**
     * Opt-in release of the per-thread configuration held in the {@code ThreadLocal} state.
     */
    @Override
    public void clearState() {
        this.constraintLanguage.remove();
        this.constraintLanguageVersion.remove();
        this.constraint.remove();
        this.catalogFinder.remove();
        this.maxRecords.remove();
        this.startPosition.remove();
        this.outputSchema.remove();
        this.resultType.remove();
        this.elementSetName.remove();
        this.typeName.remove();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("constraintLanguage").append(this.constraintLanguage.get());
        str.append(", constraintLanguageVersion").append(this.constraintLanguageVersion.get());
        str.append(", constraint").append(this.constraint.get());
        str.append(", catalogFinder").append(this.catalogFinder.get());
        str.append(", maxRecords=").append(this.maxRecords.get());
        str.append(", startPosition=").append(this.startPosition.get());
        str.append(", outputSchema=").append(this.outputSchema.get());
        str.append(", resultType=").append(this.resultType.get());
        str.append(", elementSetName=").append(this.elementSetName.get());
        str.append(", typeName=").append(this.typeName.get());
        return str.append("}").toString();
    }
}