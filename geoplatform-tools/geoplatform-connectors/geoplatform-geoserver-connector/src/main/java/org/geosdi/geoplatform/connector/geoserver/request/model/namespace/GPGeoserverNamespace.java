package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

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
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GPGeoserverNamespace implements IGPGeoserverNamespace {

    private static final long serialVersionUID = -5156025413783784293L;
    //
    private String name;
    private String href;
}
