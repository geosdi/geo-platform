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

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.geosdi.geoplatform.connector.protocol.GeoPlatformHTTP;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguage;
import org.geosdi.geoplatform.xml.csw.ConstraintLanguageVersion;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetNameType;
import org.geosdi.geoplatform.xml.csw.v202.ElementSetType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordsType;
import org.geosdi.geoplatform.xml.csw.v202.QueryConstraintType;
import org.geosdi.geoplatform.xml.csw.v202.QueryType;
import org.geosdi.geoplatform.xml.csw.v202.ResultType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class CatalogGetRecords<T> extends CatalogCSWRequest<T>
        implements CatalogGetRecordsRequest<T> {

    private ConstraintLanguage constraintLanguage;
    private ConstraintLanguageVersion constraintLanguageVersion;
    private String constraint;
    private BigInteger maxRecords;
    private BigInteger startPosition;
    private String outputFormat;
    private OutputSchema outputSchema;
    private ResultType resultType;
    private ElementSetType elementSetName;
    private TypeName typeName;

    public CatalogGetRecords(GPServerConnector server) throws URISyntaxException {
        super(server);
    }

    @Override
    public ConstraintLanguage getConstraintLanguage() {
        return constraintLanguage;
    }

    @Override
    public void setConstraintLanguage(ConstraintLanguage constraintLanguage) {
        // TODO Support FILTER ConstraintLanguage
        if (constraintLanguage == ConstraintLanguage.FILTER) {
            throw new UnsupportedOperationException("FILTER constraint language is not supported yet. "
                    + "Use CQL_TEXT constraint language.");
        }
        this.constraintLanguage = constraintLanguage;
    }

    @Override
    public ConstraintLanguageVersion getConstraintLanguageVersion() {
        return constraintLanguageVersion;
    }

    @Override
    public String getConstraint() {
        return constraint;
    }

    @Override
    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    @Override
    public void setConstraintLanguageVersion(ConstraintLanguageVersion constraintLanguageVersion) {
        this.constraintLanguageVersion = constraintLanguageVersion;
    }

    @Override
    public BigInteger getMaxRecords() {
        return maxRecords;
    }

    @Override
    public void setMaxRecords(BigInteger maxRecords) {
        this.maxRecords = maxRecords;
    }

    @Override
    public BigInteger getStartPosition() {
        return startPosition;
    }

    @Override
    public void setStartPosition(BigInteger startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String getOutputFormat() {
        return outputFormat;
    }

    @Override
    public void setOutputFormat(String outputFormat) {
        if (!GeoPlatformHTTP.CONTENT_TYPE_XML.equals(outputFormat)) {
            throw new IllegalArgumentException("The output format must be \""
                    + GeoPlatformHTTP.CONTENT_TYPE_XML + "\"");
        }
        this.outputFormat = outputFormat;
    }

    @Override
    public OutputSchema getOutputSchema() {
        return outputSchema;
    }

    @Override
    public void setOutputSchema(OutputSchema outputSchema) {
        // TODO GMD list
        if (outputSchema != OutputSchema.CSW) {
            throw new UnsupportedOperationException("GMD output schema is not supported yet. "
                    + "Use CSW output schema.");
        }
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
    protected HttpEntity preparePostEntity()
            throws JAXBException, UnsupportedEncodingException {

        Marshaller marshaller = cswContext.acquireMarshaller();

        GetRecordsType request = this.prepareGetRecordsRequest();
        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);

        return new StringEntity(writer.toString(),
                GeoPlatformHTTP.CONTENT_TYPE_XML, HTTP.UTF_8);
    }

    private GetRecordsType prepareGetRecordsRequest() {
        GetRecordsType request = new GetRecordsType();

        request.setResultType(resultType != null ? resultType : ResultType.HITS);

        request.setOutputFormat(outputFormat != null ? outputFormat
                : GeoPlatformHTTP.CONTENT_TYPE_XML);

        request.setOutputSchema(outputSchema != null ? outputSchema.toString()
                : OutputSchema.CSW.toString());

        QueryType query = new QueryType();
        request.setAbstractQuery(query);

        List<QName> typNameList = new ArrayList<QName>();
        typNameList.add(typeName != null ? typeName.getQName()
                : TypeName.RECORD.getQName());
        query.setTypeNames(typNameList);

        ElementSetNameType elementSetNameType = new ElementSetNameType();
        elementSetNameType.setValue(elementSetName != null ? elementSetName
                : ElementSetType.BRIEF);
        query.setElementSetName(elementSetNameType);

        if (constraint != null) {
            if (constraintLanguage == null) {
                throw new IllegalArgumentException("If 'Constraint' is setted, "
                        + "'Constraint Language' must not be null.");
            }

            QueryConstraintType queryConstraintType = new QueryConstraintType();

            switch (constraintLanguage) {
                case FILTER:
                    // TODO Support FILTER ConstraintLanguage
                    // queryConstraintType.setFilter(this.createFilterType());
                    break;
                case CQL_TEXT:
                    if (constraintLanguageVersion == null) {
                        throw new IllegalArgumentException("For 'Constraint Language' \"CQL_TEXT\", "
                                + "'Constraint Language Version' must not be null.");
                    }

                    queryConstraintType.setVersion(constraintLanguageVersion.toString());
                    queryConstraintType.setCqlText(constraint);
            }

            query.setConstraint(queryConstraintType);
        }

        if (startPosition != null) {
            request.setStartPosition(startPosition);
        }

        if (maxRecords != null) {
            request.setMaxRecords(maxRecords);
        }

        return request;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append("constraintLanguage").append(constraintLanguage);
        str.append(", constraintLanguageVersion").append(constraintLanguageVersion);
        str.append(", constraint").append(constraint);
        str.append(", maxRecords=").append(maxRecords);
        str.append(", startPosition=").append(startPosition);
        str.append(", outputFormat=").append(outputFormat);
        str.append(", outputSchema=").append(outputSchema);
        str.append(", resultType=").append(resultType);
        str.append(", elementSetName=").append(elementSetName);
        str.append(", typeName=").append(typeName);
        return str.append("}").toString();
    }
}
