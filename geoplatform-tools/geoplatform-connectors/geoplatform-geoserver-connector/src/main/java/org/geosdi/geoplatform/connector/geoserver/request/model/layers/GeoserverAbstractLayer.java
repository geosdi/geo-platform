package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource.GeoserverLayerResource;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlTransient
public abstract class GeoserverAbstractLayer implements GeoserverLayer {

    private static final long serialVersionUID = -7206692946003427810L;
    //
    private String name;
    private String path;
    private GPGeoserverStyle defaultStyle;
    @XmlElement(name = "styles")
    private GeoserverLayerStyle layerStyle;
    @XmlElement(name = "resource")
    private GeoserverLayerResource layerResource;
    @XmlElement(name = "attribution")
    private GeoserverLayerAttribution layerAttribution;
}