package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import javax.annotation.Nonnull;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPWMSBaseGetRequest<T> extends GPGetConnectorRequest<T> {

    private final GPBaseJAXBContext wmsJAXBContext;

    /**
     * @param server
     * @param theWmsJAXBContext
     */
    protected GPWMSBaseGetRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) GPBaseJAXBContext theWmsJAXBContext) {
        super(server);
        checkArgument(theWmsJAXBContext != null, "The Parameter wmsJAXBContext must not be null.");
        this.wmsJAXBContext = theWmsJAXBContext;
    }

    /**
     * @return Marshaller
     * @throws Exception
     */
    @Override
    public Marshaller getMarshaller() throws Exception {
        return this.wmsJAXBContext.acquireMarshaller();
    }

    /**
     * @return Unmarshaller
     * @throws Exception
     */
    @Override
    public Unmarshaller getUnmarshaller() throws Exception {
        return this.wmsJAXBContext.acquireUnmarshaller();
    }
}