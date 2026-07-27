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
 * Fluent, thread-safe API of the GetRecords CSW_202 request. Every {@code withXxx(...)} mutator stores its
 * value in per-thread state and returns this same request, so calls can be chained and a single shared
 * instance can be configured concurrently from many threads without cross-thread clobbering. The read side
 * consumed by the {@code v202} handler chain is exposed via {@link CatalogGetRecordsRequestState}.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface CatalogGetRecordsRequest<T> extends CatalogStatefulRequest<T, CatalogGetRecordsRequest<T>> {

    /**
     * @param theConstraintLanguage the value of the constraintLanguage property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withConstraintLanguage(@Nullable ConstraintLanguage theConstraintLanguage);

    /**
     * @param theConstraintLanguageVersion the value of the constraintLanguageVersion property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withConstraintLanguageVersion(@Nullable ConstraintLanguageVersion theConstraintLanguageVersion);

    /**
     * @param theConstraint the value of the constraint property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withConstraint(@Nullable String theConstraint);

    /**
     * @param theCatalogFinder the {@link CatalogFinderBean} carrying the search criteria.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withCatalogFinder(@Nullable CatalogFinderBean theCatalogFinder);

    /**
     * @param theMaxRecords the value of the maxRecords property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withMaxRecords(@Nullable BigInteger theMaxRecords);

    /**
     * @param theStartPosition the value of the startPosition property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withStartPosition(@Nullable BigInteger theStartPosition);

    /**
     * @param theOutputSchema the value of the outputSchema property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withOutputSchema(@Nullable OutputSchema theOutputSchema);

    /**
     * The only admissible parameters are:
     * <p>
     * <ul>
     * <li>results</li>
     * <li>hits</li>
     * <li>validate</li>
     * </ul>
     * <p>The default value is Hits</p>
     * <p/>
     *
     * @param theResultType the value of the resultType property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withResultType(@Nullable String theResultType);

    /**
     * The only admissible parameters are:
     * <p>
     * <ul>
     * <li>brief</li>
     * <li>summary</li>
     * <li>full</li>
     * </ul>
     * <p>The default value is Summary</p>
     * <p/>
     *
     * @param theElementSetName the value of the elementSetName property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withElementSetName(@Nullable String theElementSetName);

    /**
     * @param theTypeName the value of the typeName property.
     * @return {@link CatalogGetRecordsRequest<T>}
     */
    CatalogGetRecordsRequest<T> withTypeName(@Nullable TypeName theTypeName);
}