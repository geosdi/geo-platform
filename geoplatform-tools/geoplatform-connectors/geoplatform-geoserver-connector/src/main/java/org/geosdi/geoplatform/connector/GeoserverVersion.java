package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.server.GPServerConnector;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GeoserverVersion implements GPServerConnector.GPServerConnectorVersion {

    V212x("2.12.x");

    private String version;

    GeoserverVersion(String thVersion) {
        this.version = thVersion;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return this.version;
    }

    /**
     * @param version
     * @return {@link GeoserverVersion}
     */
    public static GeoserverVersion fromString(String version) {
        Optional<GeoserverVersion> optional = stream(GeoserverVersion.values())
                .filter(v -> ((version != null) && !(version.trim().isEmpty()))
                        ? v.getVersion().equalsIgnoreCase(version) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : GeoserverVersion.V212x;
    }
}