package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "style")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverSingleStyle implements IGPGeoserverSingleStyle {

    private static final long serialVersionUID = 7624720815856249808L;
    //
    private String name;
    private String format;
    private IGPStyleVersion languageVersion;
    @XmlElement(name = "filename")
    private String fileName;
}
