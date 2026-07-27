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

import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;

import javax.annotation.Nullable;
import java.math.BigInteger;

/**
 * SPI over the per-thread configuration of a {@link CatalogGetRecordsRequest}. It is consumed internally by the
 * {@code v202.responsibility} / {@code v202.cql} / {@code v202.filter} handler chain to build the
 * {@code GetRecords} request while the public {@link CatalogGetRecordsRequest} interface only exposes the
 * fluent {@code withXxx(...)} mutators. A single shared request instance keeps its state isolated per-thread
 * (see the {@code ThreadLocal} backing in {@link CatalogGetRecords}), so reads through this view always return
 * the caller thread's own values.
 * <p>
 * The view is read-only except for {@link #setConstraint(String)}, which the CQL chain needs in order to
 * accumulate the constraint it builds; being backed by a {@code ThreadLocal}, that write is confined to the
 * calling thread and never visible to the other threads sharing the same request instance.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface CatalogGetRecordsRequestState {

    /**
     * @return the value of the constraint language property.
     */
    ConstraintLanguage getConstraintLanguage();

    /**
     * @return the value of the constraint language version property.
     */
    ConstraintLanguageVersion getConstraintLanguageVersion();

    /**
     * @return the value of the constraint property.
     */
    String getConstraint();

    /**
     * Overwrites the constraint held in the calling thread's state. Reserved to the {@code CQL_TEXT} handler
     * chain, which uses it to accumulate the constraint predicate by predicate.
     *
     * @param theConstraint the constraint to store in the per-thread state.
     */
    void setConstraint(@Nullable String theConstraint);

    /**
     * @return the {@link CatalogFinderBean} carrying the search criteria, or {@code null} if no filtering is required.
     */
    CatalogFinderBean getCatalogFinder();

    /**
     * @return the value of the maxRecords property.
     */
    BigInteger getMaxRecords();

    /**
     * @return the value of the startPosition property.
     */
    BigInteger getStartPosition();

    /**
     * @return the value of the outputSchema property.
     */
    OutputSchema getOutputSchema();

    /**
     * @return the value of the resultType property.
     */
    String getResultType();

    /**
     * @return the value of the elementSetName property.
     */
    String getElementSetName();

    /**
     * @return the value of the typeName property.
     */
    TypeName getTypeName();
}