package org.geosdi.geoplatform.connector.geoserver.model.layergroups;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPLayerGroupsEntry implements IGPLayerGroupsEntry {

    private static final long serialVersionUID = 8968633650621214629L;
    //
    private String name;
    private String href;
}