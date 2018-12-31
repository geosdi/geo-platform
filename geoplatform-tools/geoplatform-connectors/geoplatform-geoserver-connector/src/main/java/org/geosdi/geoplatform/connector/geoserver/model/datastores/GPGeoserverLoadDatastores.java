package org.geosdi.geoplatform.connector.geoserver.model.datastores;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "dataStores")
@XmlAccessorType(value = FIELD)
public class GPGeoserverLoadDatastores implements IGPGeoserverLoadDatastores {

    private static final long serialVersionUID = 5795246106210304394L;
    //
    @XmlElement(name = "dataStore")
    private List<IGPGeoserverDatastoreWorkspace> dataStores;
}