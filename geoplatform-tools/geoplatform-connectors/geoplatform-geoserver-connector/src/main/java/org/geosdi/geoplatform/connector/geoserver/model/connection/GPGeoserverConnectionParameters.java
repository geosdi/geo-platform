package org.geosdi.geoplatform.connector.geoserver.model.connection;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class GPGeoserverConnectionParameters implements IGPGeoserverConnectionParameters {

    private static final long serialVersionUID = -5744680926370539432L;
    //
    @XmlElement(name = "entry")
    private List<IGPGeoserverConnectionParam> params;
}