package org.geosdi.geoplatform.connector.server.v111;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPWMSBaseGetFeatureInfoRequest;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.v111.GPWMSJAXBContextV111.WMS_JAXB_CONTEXT_V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class WMSGetFeatureInfoV111Request extends GPWMSBaseGetFeatureInfoRequest<Object, GPWMSGetFeatureInfoV111Request<Object>> implements GPWMSGetFeatureInfoV111Request<Object> {

    /**
     * @param server
     */
    WMSGetFeatureInfoV111Request(@Nonnull(when = NEVER) GPServerConnector server) {
        super(server, WMS_JAXB_CONTEXT_V111);
    }

    /**
     * @param inputStream
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    protected Object readInternal(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        return CharStreams.toString(new InputStreamReader(inputStream, UTF_8));
    }

    /**
     * @return {@link GPWMSGetFeatureInfoV111Request<Object>}
     */
    @Override
    protected GPWMSGetFeatureInfoV111Request<Object> self() {
        return this;
    }
}