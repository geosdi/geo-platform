package org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CoverageResource.class, name = "coverage"),
        @JsonSubTypes.Type(value = FeatureTypeResource.class, name = "featureType")})
public interface GeoserverLayerResource extends Serializable {

    /**
     * @return {@link LayerResourceType}
     */
    LayerResourceType getType();

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getHref();
}
