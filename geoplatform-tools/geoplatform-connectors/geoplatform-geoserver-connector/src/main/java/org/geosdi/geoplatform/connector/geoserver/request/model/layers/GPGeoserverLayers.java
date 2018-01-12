package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "layers")
public class GPGeoserverLayers implements IGPGeoserverLayers {

    private static final long serialVersionUID = 8313396633251792701L;
    //
    @JsonDeserialize(contentAs = GPGeoserverLayer.class)
    @XmlElement(name = "layer")
    private List<IGPGeoserverLayer> layers;
}