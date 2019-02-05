package org.geosdi.geoplatform.connector.server.v130;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetCapabilitiesRequest;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetCapabilitiesV130Request extends GPWMSGetCapabilitiesRequest<WMSCapabilities>, GPWMSJAXBContextV130 {
}