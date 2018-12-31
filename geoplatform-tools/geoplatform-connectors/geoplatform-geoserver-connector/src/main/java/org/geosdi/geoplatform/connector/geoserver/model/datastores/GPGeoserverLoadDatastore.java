package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.connection.GPGeoserverConnectionParameters;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Getter
@XmlRootElement(name = "dataStore")
@XmlAccessorType(value = FIELD)
public class GPGeoserverLoadDatastore implements IGPGeoserverLoadDatastore {

    private static final long serialVersionUID = -3829437698785199252L;
    //
    private String name;
    private boolean enabled;
    private IGPGeoserverDatastoreWorkspace workspace;
    private GPGeoserverConnectionParameters connectionParameters;
    @XmlElement(name = "_default")
    private boolean isDefault;
    private String featureTypes;
}