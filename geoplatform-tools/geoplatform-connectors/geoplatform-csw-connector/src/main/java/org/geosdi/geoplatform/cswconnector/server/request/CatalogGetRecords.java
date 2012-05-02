/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.cswconnector.server.request;

import java.net.URISyntaxException;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class CatalogGetRecords<T> extends CatalogCSWRequest<T>
        implements CatalogGetRecordsRequest {

//    protected final String version;
    // TODO Filter request
//    private String constraint;
//    private String constraintLanguage;
//    private String constraintLanguageVersion;
    private Integer maxRecords;
    private Integer startPosition;
    private String outputFormat;
    private String outputSchema;
    private ResultType resultType;
    private ElementSetType elementSetName;
    private TypeName typeName;

    public CatalogGetRecords(GPServerConnector server) throws URISyntaxException {
        super(server);
    }

    @Override
    public Integer getMaxRecords() {
        return maxRecords;
    }

    @Override
    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }

    @Override
    public Integer getStartPosition() {
        return startPosition;
    }

    @Override
    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String getOutputFormat() {
        return outputFormat;
    }

    @Override
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    @Override
    public String getOutputSchema() {
        return outputSchema;
    }

    @Override
    public void setOutputSchema(String outputSchema) {
        this.outputSchema = outputSchema;
    }

    @Override
    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public ElementSetType getElementSetName() {
        return elementSetName;
    }

    @Override
    public void setElementSetName(ElementSetType elementSetName) {
        this.elementSetName = elementSetName;
    }

    @Override
    public TypeName getTypeName() {
        return typeName;
    }

    @Override
    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    @Override
    public StringEntity preparePostEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append("{");
//        str.append().append();
//        str.append().append();
//        str.append().append();
//        str.append().append();
        str.append("maxRecords=").append(maxRecords);
        str.append(", startPosition=").append(startPosition);
        str.append(", outputFormat=").append(outputFormat);
        str.append(", outputSchema=").append(outputSchema);
        str.append(", resultType=").append(resultType);
        str.append(", elementSetName=").append(elementSetName);
        str.append(", typeName=").append(typeName);
        return str.append("}").toString();
    }
}
