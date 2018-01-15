package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoserverLayerAttribution implements Serializable {

    private static final long serialVersionUID = -1409138085098149048L;
    //
    private String logoWidth;
    private String logoHeight;
}
