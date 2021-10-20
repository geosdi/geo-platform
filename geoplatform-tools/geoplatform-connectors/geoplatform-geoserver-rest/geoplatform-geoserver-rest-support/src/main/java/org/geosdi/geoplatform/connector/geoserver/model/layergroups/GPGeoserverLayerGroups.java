package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Getter
@ToString
@XmlRootElement(name = "layerGroups")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GPGeoserverLayerGroups implements IGPGeoserverLayerGroups {

    @XmlElement(name = "layerGroup", type = GPGeoserverLayerGroup.class)
    @JsonDeserialize(contentAs = GPGeoserverLayerGroup.class)
    private List<IGPGeoserverLayerGroup> layers;
}