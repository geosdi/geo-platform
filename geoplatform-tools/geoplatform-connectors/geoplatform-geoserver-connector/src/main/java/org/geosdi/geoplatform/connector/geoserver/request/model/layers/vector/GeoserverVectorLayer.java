package org.geosdi.geoplatform.connector.geoserver.request.model.layers.vector;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GeoserverAbstractLayer;
import org.geosdi.geoplatform.connector.geoserver.request.model.layers.GeoserverLayerType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static org.geosdi.geoplatform.connector.geoserver.request.model.layers.GeoserverLayerType.Vector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoserverVectorLayer extends GeoserverAbstractLayer {

    private static final long serialVersionUID = 6601349304565388019L;
    //
    @XmlElement(name = "type")
    private final GeoserverLayerType layerType = Vector;
}