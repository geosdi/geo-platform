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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.server.request.TransactionIdGen;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.TransactionParameters;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.stax.writer.AbstractStaxStreamWriter;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.FeaturesNamespace.*;
import static org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.WFSTransactionParam.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractFeatureStreamWriter<T extends Object> extends AbstractStaxStreamWriter<T> {

    private final String wfsVersion;
    private final String gmlVersion;
    protected final WKTReader wktReader;

    /**
     * @param theWFSVersion
     * @param theGMLVersion
     */
    protected AbstractFeatureStreamWriter(@Nonnull(when = NEVER) String theWFSVersion, @Nonnull(when = NEVER) String theGMLVersion) {
        checkArgument((theWFSVersion != null) && !(theWFSVersion.trim().isEmpty()), "The Parameter wfsVersion must not be null or an empty string.");
        checkArgument((theGMLVersion != null) && !(theGMLVersion.trim().isEmpty()), "The Parameter gmlVersion must not be null or an empty string.");
        this.wfsVersion = theWFSVersion;
        this.gmlVersion = theGMLVersion;
        this.wktReader = new WKTReader(new GeometryFactory());
    }

    /**
     * @param <T extends WFSTransactionRequest> request
     * @throws XMLStreamException
     * @throws Exception
     */
    protected final <T extends WFSTransactionRequest> void writeDocument(@Nonnull(when = NEVER) T request) throws XMLStreamException, Exception {
        checkArgument(request != null, "The Parameter request must not be null.");
        this.writeStartDocument(request.getTypeName());
        this.writeTransactionRequest(request);
        writer().writeEndDocument();
    }

    /**
     * @param geometry
     * @param typeName
     * @throws XMLStreamException
     * @throws Exception
     */
    protected abstract void writeGeometryAttribute(@Nonnull(when = NEVER) GeometryAttributeDTO geometry, @Nonnull(when = NEVER) QName typeName) throws XMLStreamException, Exception;

    /**
     * @param typeName
     * @throws XMLStreamException
     */
    private void writeStartDocument(@Nonnull(when = NEVER) QName typeName) throws XMLStreamException {
        checkArgument(typeName != null, "The Parameter typeName must not be null.");
        checkArgument(typeName.getPrefix() != null, "The Parameter prefix must not be null.");
        writer().writeStartDocument("UTF-8", "1.0");
        writer().setPrefix(GML.PREFIX(), GML.NAMESPACE());
        writer().setPrefix(XSI.PREFIX(), XSI.NAMESPACE());
        writer().setPrefix(WFS.PREFIX(), WFS.NAMESPACE());
        writer().setPrefix(OGC.PREFIX(), OGC.NAMESPACE());
        writer().setPrefix(XS.PREFIX(), XS.NAMESPACE());
        writer().setPrefix(typeName.getPrefix(), typeName.getNamespaceURI());
    }

    /**
     * @param request
     * @param <T>
     * @throws XMLStreamException
     * @throws Exception
     */
    private <T extends WFSTransactionRequest> void writeTransactionRequest(T request) throws XMLStreamException, Exception {
        writer().writeStartElement(WFS.PREFIX(), TransactionParameters.getParam(TRANSACTION), WFS.NAMESPACE());
        writer().writeAttribute("service", this.getService());
        writer().writeAttribute("version", this.getWfsVersion());
        this.writeTransactionInsert(request);
        writer().writeEndElement();
    }

    /**
     * @param request
     * @throws XMLStreamException
     * @throws Exception
     */
    private void writeTransactionInsert(WFSTransactionRequest request) throws XMLStreamException, Exception {
        writer().writeStartElement(WFS.PREFIX(), TransactionParameters.getParam(TRANSACTION_INSERT), WFS.NAMESPACE());
        TransactionIdGen idGen = request.getTransactionIdGen();
        if (idGen != null) {
            writer().writeAttribute(WFS.PREFIX(), WFS.NAMESPACE(), TransactionParameters.getParam(ID_GEN), idGen.value());
        }
        String inputFormat = request.getInputFormat();
        if (inputFormat != null) {
            writer().writeAttribute(WFS.PREFIX(), WFS.NAMESPACE(), TransactionParameters.getParam(INPUT_FORMAT), inputFormat);
        }
        String srsName = request.getSRS();
        if (srsName != null) {
            writer().writeAttribute(WFS.PREFIX(), WFS.NAMESPACE(), TransactionParameters.getParam(SRS_NAME), srsName);
        }
        this.writeFeature(request);
        writer().writeEndElement();
    }

    /**
     * @param request
     * @throws XMLStreamException
     * @throws Exception
     */
    private void writeFeature(WFSTransactionRequest request) throws XMLStreamException, Exception {
        QName typeName = request.getTypeName();
        writer().writeStartElement(typeName.getLocalPart());
        List<AttributeDTO> attributes = request.getAttributes();
        for (AttributeDTO attributeDTO : attributes) {
            if (attributeDTO instanceof GeometryAttributeDTO) {
                writeGeometryAttribute((GeometryAttributeDTO) attributeDTO, typeName);
            } else {
                writeAttribute(attributeDTO, typeName);
            }
        }
        writer().writeEndElement();
    }

    /**
     * @param attribute
     * @param typeName
     * @throws XMLStreamException
     */
    private void writeAttribute(AttributeDTO attribute, QName typeName) throws XMLStreamException {
        super.writeElement(typeName.getPrefix() + ":" + attribute.getName(), attribute.getValue());
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
        return getClass().getSimpleName() + " { \n"
                + "service = " + getService()
                + "\n, wfsVersion = " + wfsVersion
                + "\n, gmlVersion = " + gmlVersion
                + "\n}";
    }
}