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

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

import static org.geosdi.geoplatform.connector.jaxb.repository.CSWConnectorJAXBContext.CSW_CONTEXT_KEY;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class CatalogCSWRequest<T, Request> extends GPPostConnectorRequest<T, Request> {

    static {
        cswContext = JAXBContextConnectorRepository.getProvider(CSW_CONTEXT_KEY);
    }

    //
    private static final GPBaseJAXBContext cswContext;

    public CatalogCSWRequest(GPServerConnector server) {
        super(server);
    }

    @Override
    protected HttpEntity preparePostEntity() throws Exception {
        Marshaller marshaller = getMarshaller();

        Object request = this.createRequest();
        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);

        return new StringEntity(writer.toString(), ContentType.APPLICATION_XML);
    }

    @Override
    public Marshaller getMarshaller() throws Exception {
        return cswContext.acquireMarshaller();
    }

    @Override
    public Unmarshaller getUnmarshaller() throws Exception {
        return cswContext.acquireUnmarshaller();
    }

    @Override
    public String showRequestAsString() throws Exception {
        StringWriter writer = new StringWriter();
        cswContext.acquireMarshaller().marshal(createRequest(), writer);
        return writer.toString();
    }
}