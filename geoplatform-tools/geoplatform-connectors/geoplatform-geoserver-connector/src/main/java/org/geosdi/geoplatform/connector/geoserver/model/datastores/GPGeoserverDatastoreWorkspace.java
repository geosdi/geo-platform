package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "workspace")
@XmlAccessorType(value = FIELD)
public class GPGeoserverDatastoreWorkspace implements IGPGeoserverDatastoreWorkspace {

    private static final long serialVersionUID = -3829437698785199252L;
    //
    private String name;
    private String href;
}