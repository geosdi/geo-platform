package org.geosdi.geoplatform.connector.server.v111;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetCapabilitiesV111Request extends GPWMSGetCapabilitiesRequest<WMTMSCapabilities>, GPWMSJAXBContextV111 {
}