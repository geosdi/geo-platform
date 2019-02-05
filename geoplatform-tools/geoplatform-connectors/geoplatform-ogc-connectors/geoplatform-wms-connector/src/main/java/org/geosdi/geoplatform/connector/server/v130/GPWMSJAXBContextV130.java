package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV130.WMS_CONTEXT_KEY_V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSJAXBContextV130 {

    GPBaseJAXBContext WMS_JAXB_CONTEXT_V130 = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V130);
}