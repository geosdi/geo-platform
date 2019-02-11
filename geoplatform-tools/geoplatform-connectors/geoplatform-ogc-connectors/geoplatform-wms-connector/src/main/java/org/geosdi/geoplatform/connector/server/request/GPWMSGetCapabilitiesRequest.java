package org.geosdi.geoplatform.connector.server.request;

import org.geosdi.geoplatform.connector.WMSVersion;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetCapabilitiesRequest<T> extends GPConnectorRequest<T> {

    String WMS_GET_CAPABILITIES_BASE_REQUEST = "${start}SERVICE=WMS&REQUEST=GetCapabilities&VERSION=";

    /**
     * @param baseURI
     * @return {@link String}
     * @throws Exception
     */
    default String buildGetCapabilitiesURL(@Nonnull(when = When.NEVER) String baseURI) throws Exception {
        checkArgument((baseURI != null) && !(baseURI.trim().isEmpty()), "The Parameter baseURI must not be null or an empty string.");
        return (baseURI.contains("?")
                ? baseURI.concat(WMS_GET_CAPABILITIES_BASE_REQUEST.replace("${start}", "&").concat(this.toVersionAsString()))
                : baseURI.concat(WMS_GET_CAPABILITIES_BASE_REQUEST.replace("${start}", "?").concat(this.toVersionAsString())));
    }

    /**
     * @return {@link WMSVersion}
     */
    WMSVersion getVersion();

    /**
     * @return {@link String}
     */
    default String toVersionAsString() {
        checkArgument(this.getVersion() != null, "The Parameter WMSVersion must not be null.");
        return this.getVersion().toString();
    }
}