package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV111.WMS_CONTEXT_KEY_V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSJAXBContextV111 {

    GPBaseJAXBContext WMS_JAXB_CONTEXT_V111 = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V111);
    IGPWMSSAXSourceBuilder WMS_SAX_SOURCE_BUILDER = new IGPWMSSAXSourceBuilder.GPWMSSAXSourceBuilder();
}