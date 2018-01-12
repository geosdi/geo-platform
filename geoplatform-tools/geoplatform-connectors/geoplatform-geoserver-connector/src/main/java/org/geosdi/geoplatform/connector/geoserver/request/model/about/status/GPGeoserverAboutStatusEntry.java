package org.geosdi.geoplatform.connector.geoserver.request.model.about.status;

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
public class GPGeoserverAboutStatusEntry implements IGPGeoserverAboutStatusEntry {

    private static final long serialVersionUID = -9201236555135699778L;
    //
    private String name;
    private String href;
}
