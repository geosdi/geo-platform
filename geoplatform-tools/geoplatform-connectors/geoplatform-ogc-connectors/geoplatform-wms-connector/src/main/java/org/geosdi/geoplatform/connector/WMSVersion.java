package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.server.GPServerConnector;

import java.util.Optional;

import static java.util.Arrays.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSVersion implements GPServerConnector.GPServerConnectorVersion {

    V111("1.1.1"),
    V130("1.3.0");

    private final String version;

    /**
     * @param theVersion
     */
    WMSVersion(String theVersion) {
        this.version = theVersion;
    }

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
     * @return {@link WMSVersion} <p>Default value if no version is found is : {@link WMSVersion#V111}</p>
     */
    public static WMSVersion forValue(String version) {
        Optional<WMSVersion> optional = stream(WMSVersion.values())
                .filter(v -> ((version != null) && !(version.trim().isEmpty()))
                        ? v.getVersion().equalsIgnoreCase(version) : Boolean.FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(Optional.empty()))) ? optional.get() : WMSVersion.V111;
    }
}