package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverLayer implements IGPGeoserverLayer {

    private static final long serialVersionUID = -1329679273067502659L;
    //
    private String layerName;
    private String href;
}