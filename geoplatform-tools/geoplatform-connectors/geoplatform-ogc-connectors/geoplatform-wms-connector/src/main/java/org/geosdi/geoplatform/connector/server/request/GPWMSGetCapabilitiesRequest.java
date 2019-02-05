package org.geosdi.geoplatform.connector.server.request;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetCapabilitiesRequest<T> extends GPConnectorRequest<T> {

    String WMS_GET_CAPABILITIES_BASE_REQUEST = "?SERVICE=WMS&REQUEST=GetCapabilities&VERSION=";
}