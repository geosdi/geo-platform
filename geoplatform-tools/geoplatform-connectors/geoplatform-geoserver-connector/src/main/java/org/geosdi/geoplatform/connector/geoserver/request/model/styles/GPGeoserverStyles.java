package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "styles")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverStyles implements IGPGeoserverStyles {

    private static final long serialVersionUID = -9119093243708708782L;
    //
    @JsonDeserialize(contentAs = GPGeoserverStyle.class)
    @XmlAttribute(name = "style")
    private List<IGPGeoserverStyle> styles;
}
