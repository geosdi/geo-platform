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
public abstract class WPSRequest<T> extends GPPostConnectorRequest<T> {

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

    /**
     * @return {@link Object}
     * @throws Exception
     */
    protected abstract Object createRequest() throws Exception;

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
