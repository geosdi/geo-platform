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

import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;

import java.math.BigInteger;

/**
 * API of GetRecords CSW_202 request
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface CatalogGetRecordsRequest<T> extends GPJAXBConnectorRequest<T> {

    /**
     * @return {@link ConstraintLanguage}
     */
    ConstraintLanguage getConstraintLanguage();

    /**
     * @param constraintLanguage
     */
    void setConstraintLanguage(ConstraintLanguage constraintLanguage);

    /**
     * @return {@link ConstraintLanguageVersion}
     */
    ConstraintLanguageVersion getConstraintLanguageVersion();

    /**
     * @param constraintLanguageVersion
     */
    void setConstraintLanguageVersion(ConstraintLanguageVersion constraintLanguageVersion);

    /**
     * @return {@link String}
     */
    String getConstraint();

    /**
     * @param constraint
     */
    void setConstraint(String constraint);

    /**
     * @return {@link CatalogFinderBean}
     */
    CatalogFinderBean getCatalogFinder();

    /**
     * @param catalogFinder
     */
    void setCatalogFinder(CatalogFinderBean catalogFinder);

    /**
     * @return {@link BigInteger}
     */
    BigInteger getMaxRecords();

    /**
     * @param maxRecords
     */
    void setMaxRecords(BigInteger maxRecords);

    /**
     * @return {@link BigInteger}
     */
    BigInteger getStartPosition();

    /**
     * @param startPosition
     */
    void setStartPosition(BigInteger startPosition);

    /**
     * @return {@link OutputSchema}
     */
    OutputSchema getOutputSchema();

    /**
     * @param outputSchema
     */
    void setOutputSchema(OutputSchema outputSchema);

    /**
     * @return {@link String}
     */
    String getResultType();

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
     * @param resultType
     */
    void setResultType(String resultType);

    /**
     * @return {@link String}
     */
    String getElementSetName();

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
     * @param elementSetName
     */
    void setElementSetName(String elementSetName);

    /**
     * @return {@link TypeName}
     */
    TypeName getTypeName();

    void setTypeName(TypeName typeName);
}