package org.geosdi.geoplatform.connector.server.v130;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.GPWMSBaseGetFeatureInfoRequest;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Charsets.UTF_8;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.v130.GPWMSJAXBContextV130.WMS_JAXB_CONTEXT_V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class WMSGetFeatureInfoV130Request extends GPWMSBaseGetFeatureInfoRequest<Object, GPWMSGetFeatureInfoV130Request<Object>> implements GPWMSGetFeatureInfoV130Request<Object> {

    /**
     * @param server
     */
    WMSGetFeatureInfoV130Request(@Nonnull(when = When.NEVER) GPServerConnector server) {
        super(server, WMS_JAXB_CONTEXT_V130);
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
     * @return {@link GPWMSGetFeatureInfoV130Request<Object>}
     */
    @Override
    protected GPWMSGetFeatureInfoV130Request<Object> self() {
        return this;
    }
}