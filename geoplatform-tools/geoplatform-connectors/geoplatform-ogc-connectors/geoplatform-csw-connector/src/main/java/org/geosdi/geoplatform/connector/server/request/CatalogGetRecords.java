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

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;

import java.math.BigInteger;

/**
 * Abstract class of GetRecords CSW_202 request
 *
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Getter
@Setter
public abstract class CatalogGetRecords<T, Request> extends CatalogCSWRequest<T, Request> implements CatalogGetRecordsRequest<T> {

    protected ConstraintLanguage constraintLanguage;
    protected ConstraintLanguageVersion constraintLanguageVersion;
    protected String constraint;
    protected CatalogFinderBean catalogFinder;
    protected BigInteger maxRecords;
    protected BigInteger startPosition;
    protected OutputSchema outputSchema;
    protected String resultType;
    protected String elementSetName;
    protected TypeName typeName;

    /**
     * @param server
     */
    public CatalogGetRecords(GPServerConnector server) {
        super(server);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("constraintLanguage").append(constraintLanguage);
        str.append(", constraintLanguageVersion").append(constraintLanguageVersion);
        str.append(", constraint").append(constraint);
        str.append(", catalogFinder").append(catalogFinder);
        str.append(", maxRecords=").append(maxRecords);
        str.append(", startPosition=").append(startPosition);
        str.append(", outputSchema=").append(outputSchema);
        str.append(", resultType=").append(resultType);
        str.append(", elementSetName=").append(elementSetName);
        str.append(", typeName=").append(typeName);
        return str.append("}").toString();
    }
}
