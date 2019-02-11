package org.geosdi.geoplatform.connector.server.request;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetMapBaseRequest extends GPWMSKeyValuePair {

    /**
     * @param <BoundingBox>
     * @return {@link BoundingBox}
     */
    <BoundingBox extends GPWMSBoundingBox> BoundingBox getBoundingBox();

    /**
     * @return {@link List<String>}
     */
    List<String> getLayers();

    /**
     * @return {@link String}
     */
    String getSrs();

    /**
     * @return {@link String}
     */
    String getWidth();

    /**
     * @return {@link String}
     */
    String getHeight();
}