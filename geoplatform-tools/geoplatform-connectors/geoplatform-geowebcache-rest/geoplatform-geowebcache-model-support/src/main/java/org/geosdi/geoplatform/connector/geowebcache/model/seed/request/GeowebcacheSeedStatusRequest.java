package org.geosdi.geoplatform.connector.geowebcache.model.seed.request;

import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GeowebcacheSeedStatusRequest implements IGeowebcacheSeedStatusRequest {

    private static final long serialVersionUID = 2630474654445470819L;
    //
    private final String layerName;

    /**
     * @param theLayerName
     */
    public GeowebcacheSeedStatusRequest(@Nonnull(when = NEVER) String theLayerName) {
        checkArgument(((theLayerName != null) && !(theLayerName.trim().isEmpty())), "The Parameter layerName must not be null or an empty string.");
        this.layerName = (theLayerName.endsWith(".json") ? theLayerName : theLayerName.concat(".json"));
    }
}