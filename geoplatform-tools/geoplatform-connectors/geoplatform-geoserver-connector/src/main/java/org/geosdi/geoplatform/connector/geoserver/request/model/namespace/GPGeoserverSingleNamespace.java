package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "namespace")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverSingleNamespace implements IGPGeoserverSingleNamespace {

    private static final long serialVersionUID = -2742811374588297436L;
    //
    private String prefix;
    private String uri;
}