package org.geosdi.geoplatform.connector.geoserver.request.model.layers;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.GPGeoserverStyle;
import org.geosdi.geoplatform.connector.geoserver.request.model.styles.IGPGeoserverStyle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoserverLayerStyle implements Serializable {

    private static final long serialVersionUID = 5869917765836810769L;
    //
    @XmlElement(name = "@class")
    private String type;
    @JsonDeserialize(contentAs = GPGeoserverStyle.class)
    @XmlElement(name = "style")
    private List<IGPGeoserverStyle> styles;
}
