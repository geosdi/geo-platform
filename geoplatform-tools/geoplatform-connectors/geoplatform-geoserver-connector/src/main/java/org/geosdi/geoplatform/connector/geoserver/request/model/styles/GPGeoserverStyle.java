package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

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
public class GPGeoserverStyle implements IGPGeoserverStyle {

    private static final long serialVersionUID = -6947095438767775683L;
    //
    private String name;
    private String href;
}