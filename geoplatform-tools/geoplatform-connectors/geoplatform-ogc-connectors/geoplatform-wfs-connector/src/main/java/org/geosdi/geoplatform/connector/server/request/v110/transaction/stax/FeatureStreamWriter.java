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
package org.geosdi.geoplatform.connector.server.request.v110.transaction.stax;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.geosdi.geoplatform.connector.AbstractFeatureStreamWriter;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLJAXBContext;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextFactoryV311;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextType;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.GeometryAttributeDTO;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureStreamWriter extends AbstractFeatureStreamWriter<WFSTransactionRequest> {

    static {
        gmlContext = GMLContextFactoryV311.createJAXBContext(
                GMLContextType.POOLED);
    }
    //
    private static final GMLJAXBContext gmlContext;

    public FeatureStreamWriter() {
        super("1.1.0", "3.1.1");
    }

    @Override
    public void write(WFSTransactionRequest target,
            Object output) throws XMLStreamException, Exception {

        super.acquireWriter(output);

        QName typeName = target.getTypeName();
        
        writer.writeStartElement(typeName.getLocalPart());
        
        List<AttributeDTO> attributes = target.getAttributes();
        for (AttributeDTO attributeDTO : attributes) {
            if (attributeDTO instanceof GeometryAttributeDTO) {
                writeGeometryAttribute((GeometryAttributeDTO) attributeDTO,
                        typeName);
            } else {
                writeAttribute(attributeDTO, typeName);
            }
        }
        
        writer.writeEndElement();

        writer.flush();
    }

    private void writeGeometryAttribute(GeometryAttributeDTO geometry,
            QName typeName)
            throws XMLStreamException, Exception {
        
        writer.writeStartElement(typeName.getNamespaceURI(), geometry.getName());
        
        String wktGeometry = geometry.getValue();
        Geometry jtsGeometry = this.wktReader.read(wktGeometry);
        gmlContext.acquireMarshaller().marshal(jtsGeometry, writer);
        
        writer.writeEndElement();
    }

    private void writeAttribute(AttributeDTO attribute,
            QName typeName) throws XMLStreamException {
        super.writeElement(typeName.getPrefix() + ":" + attribute.getName(),
                attribute.getValue());
    }
}
