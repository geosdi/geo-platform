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
public class GPLayerGroups implements IGPLayerGroups {

    @XmlElement(name = "layerGroup")
    @JsonDeserialize(contentAs = GPLayerGroupsEntry.class)
    private List<IGPLayerGroupsEntry> entries;
}