package org.geosdi.geoplatform.connector.server.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSFeatureInfoFormat implements GPWMSFeatureInfoFormat {

    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    JSON("application/json"),
    GML("application/vnd.ogc.gml");

    private final String format;

    /**
     * @param theFormat
     */
    WMSFeatureInfoFormat(String theFormat) {
        this.format = theFormat;
    }

    @Override
    public WMSFeatureInfoFormat getImplementorKey() {
        return this;
    }

    /**
     * @return
     */
    @JsonValue
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * @param format
     * @return {@link WMSFeatureInfoFormat}
     */
    @JsonCreator
    public static WMSFeatureInfoFormat forFormat(String format) {
        Optional<WMSFeatureInfoFormat> optional = stream(WMSFeatureInfoFormat.values())
                .filter(v -> ((format != null) && !(format.trim().isEmpty()))
                        ? v.getFormat().equalsIgnoreCase(format) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}