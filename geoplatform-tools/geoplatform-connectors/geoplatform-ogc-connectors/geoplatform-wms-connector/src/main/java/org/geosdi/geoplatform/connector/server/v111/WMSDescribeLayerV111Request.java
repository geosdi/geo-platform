package org.geosdi.geoplatform.connector.server.v111;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPWMSServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPBaseWMSDescribeLayerRequest;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;

import javax.annotation.Nonnull;
import java.io.InputStream;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.v111.GPWMSJAXBContextV111.WMS_JAXB_CONTEXT_V111;
import static org.geosdi.geoplatform.connector.server.v111.GPWMSJAXBContextV111.WMS_SAX_SOURCE_BUILDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class WMSDescribeLayerV111Request extends GPBaseWMSDescribeLayerRequest<WMSDescribeLayerResponse> implements GPWMSDescribeLayerV111Request {

    /**
     * @param server
     */
    public WMSDescribeLayerV111Request(@Nonnull(when = NEVER) GPWMSServerConnector server) {
        super(server, WMS_JAXB_CONTEXT_V111);
    }

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    protected WMSDescribeLayerResponse readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        return (WMSDescribeLayerResponse) getUnmarshaller().unmarshal(WMS_SAX_SOURCE_BUILDER.buildSource(inputStream));
    }
}