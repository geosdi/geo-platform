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
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.connector.jaxb.repository.WPSConnectorJAXBContext;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

import static org.apache.http.entity.ContentType.APPLICATION_XML;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WPSRequest<T, Request> extends GPPostConnectorRequest<T, Request> {

    static {
        wpsContext = JAXBContextConnectorRepository.getProvider(WPSConnectorJAXBContext.WPS_CONTEXT_KEY);
    }

    //
    private static final GPBaseJAXBContext wpsContext;

    /**
     * @param server
     */
    public WPSRequest(GPServerConnector server) {
        super(server);
    }

    /**
     * @return {@link HttpEntity}
     * @throws Exception
     */
    @Override
    protected HttpEntity preparePostEntity() throws Exception {
        Marshaller marshaller = this.getMarshaller();
        Object request = this.createRequest();
        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);
        return new StringEntity(writer.toString(), APPLICATION_XML);
    }

    @Override
    public Marshaller getMarshaller() throws Exception {
        return wpsContext.acquireMarshaller();
    }

    /**
     * @return {@link Unmarshaller}
     * @throws Exception
     */
    @Override
    public Unmarshaller getUnmarshaller() throws Exception {
        return wpsContext.acquireUnmarshaller();
    }

    /**
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String showRequestAsString() throws Exception {
        StringWriter writer = new StringWriter();
        wpsContext.acquireMarshaller().marshal(createRequest(), writer);
        return writer.toString();
    }
}
