package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.raster.GeoserverRasterLayer;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource.GeoserverLayerResource;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.vector.GeoserverVectorLayer;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverStyle;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeoserverRasterLayer.class, name = "RASTER"),
        @JsonSubTypes.Type(value = GeoserverVectorLayer.class, name = "VECTOR")})
@XmlRootElement(name = "layer")
public interface GeoserverLayer extends Serializable {

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @return {@link String}
     */
    String getPath();

    /**
     * @return {@link GeoserverLayerType}
     */
    GeoserverLayerType getLayerType();

    /**
     * @return {@link GPGeoserverStyle}
     */
    GPGeoserverStyle getDefaultStyle();

    /**
     * @return {@link GeoserverLayerStyle}
     */
    GeoserverLayerStyle getLayerStyle();

    /**
     * @return {@link GeoserverLayerResource}
     */
    GeoserverLayerResource getLayerResource();

    /**
     * @return {@link GeoserverLayerAttribution}
     */
    GeoserverLayerAttribution getLayerAttribution();
}