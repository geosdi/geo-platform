/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.WKTReader;
import org.geosdi.geoplatform.connector.server.request.TransactionIdGen;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeaturesNamespace;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.TransactionParameters;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.WFSTransactionParam;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.stax.writer.AbstractStaxStreamWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.util.List;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractFeatureStreamWriter<T extends Object>
        extends AbstractStaxStreamWriter<T> {

    private final String wfsVersion;
    private final String gmlVersion;
    protected final WKTReader wktReader;

    public AbstractFeatureStreamWriter(String wfsVersion,
            String gmlVersion) {
        this.wfsVersion = wfsVersion;
        this.gmlVersion = gmlVersion;
        this.wktReader = new WKTReader(new GeometryFactory());
    }

    /**
     *
     * @param <T extends WFSTransactionRequest> request
     *
     * @throws XMLStreamException
     * @throws Exception
     */
    protected final <T extends WFSTransactionRequest> void writeDocument(
            T request) throws XMLStreamException, Exception {

        this.writeStartDocument(request.getTypeName());
        this.writeTransactionRequest(request);

        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }

    protected abstract void writeGeometryAttribute(GeometryAttributeDTO geometry,
            QName typeName) throws XMLStreamException, Exception;

    private void writeStartDocument(QName typeName) throws XMLStreamException {
        writer.writeStartDocument("UTF-8", "1.0");

//        writer.setDefaultNamespace(FeaturesNamespace.WFS.NAMESPACE());
        writer.setPrefix(FeaturesNamespace.GML.PREFIX(),
                FeaturesNamespace.GML.NAMESPACE());

        writer.setPrefix(FeaturesNamespace.XSI.PREFIX(),
                FeaturesNamespace.XSI.NAMESPACE());
        writer.setPrefix(FeaturesNamespace.WFS.PREFIX(),
                FeaturesNamespace.WFS.NAMESPACE());
        writer.setPrefix(FeaturesNamespace.OGC.PREFIX(),
                FeaturesNamespace.OGC.NAMESPACE());
        writer.setPrefix(FeaturesNamespace.XS.PREFIX(),
                FeaturesNamespace.XS.NAMESPACE());

        writer.setPrefix(typeName.getPrefix(), typeName.getNamespaceURI());
    }

    private <T extends WFSTransactionRequest> void writeTransactionRequest(
            T request) throws XMLStreamException, Exception {

        writer.writeStartElement(FeaturesNamespace.WFS.PREFIX(),
                TransactionParameters.getParam(WFSTransactionParam.TRANSACTION),
                FeaturesNamespace.WFS.NAMESPACE());
        writer.writeAttribute("service", this.getService());
        writer.writeAttribute("version", this.getWfsVersion());

        this.writeTransactionInsert(request);

        writer.writeEndElement();
    }

    private void writeTransactionInsert(WFSTransactionRequest request)
            throws XMLStreamException, Exception {
        writer.writeStartElement(FeaturesNamespace.WFS.PREFIX(),
                TransactionParameters.getParam(
                WFSTransactionParam.TRANSACTION_INSERT),
                FeaturesNamespace.WFS.NAMESPACE());

        TransactionIdGen idGen = request.getTransactionIdGen();

        if (idGen != null) {
            writer.writeAttribute(FeaturesNamespace.WFS.PREFIX(),
                    FeaturesNamespace.WFS.NAMESPACE(),
                    TransactionParameters.getParam(
                    WFSTransactionParam.ID_GEN),
                    idGen.value());
        }

        String inputFormat = request.getInputFormat();

        if (inputFormat != null) {
            writer.writeAttribute(FeaturesNamespace.WFS.PREFIX(),
                    FeaturesNamespace.WFS.NAMESPACE(),
                    TransactionParameters.getParam(
                    WFSTransactionParam.INPUT_FORMAT),
                    inputFormat);
        }

        String srsName = request.getSRS();

        if (srsName != null) {
            writer.writeAttribute(FeaturesNamespace.WFS.PREFIX(),
                    FeaturesNamespace.WFS.NAMESPACE(),
                    TransactionParameters.getParam(
                    WFSTransactionParam.SRS_NAME),
                    srsName);
        }

        this.writeFeature(request);

        writer.writeEndElement();
    }

    private void writeFeature(WFSTransactionRequest request)
            throws XMLStreamException, Exception {
        QName typeName = request.getTypeName();

        writer.writeStartElement(typeName.getLocalPart());

        List<AttributeDTO> attributes = request.getAttributes();
        for (AttributeDTO attributeDTO : attributes) {
            if (attributeDTO instanceof GeometryAttributeDTO) {
                writeGeometryAttribute((GeometryAttributeDTO) attributeDTO,
                        typeName);
            } else {
                writeAttribute(attributeDTO, typeName);
            }
        }

        writer.writeEndElement();
    }

    private void writeAttribute(AttributeDTO attribute,
            QName typeName) throws XMLStreamException {
        super.writeElement(typeName.getPrefix() + ":" + attribute.getName(),
                attribute.getValue());
    }

    /**
     * @return the wfsVersion
     */
    public String getWfsVersion() {
        return wfsVersion;
    }

    /**
     * @return the gmlVersion
     */
    public String getGmlVersion() {
        return gmlVersion;
    }

    /**
     * @return the service
     */
    public String getService() {
        return "WFS";
    }

    @Override
    public String toString() {
        return "AbstractFeatureStreamWriter { " + "service = " + getService()
                + ", wfsVersion = " + wfsVersion
                + ", gmlVersion = " + gmlVersion + '}';
    }

}
