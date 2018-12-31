package org.geosdi.geoplatform.connector.geoserver.model.workspace;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
public class GPGeoserverLoadWorkspace implements IGPGeoserverLoadWorkspace {

    private static final long serialVersionUID = 7303489484330797642L;
    //
    @XmlElement(name = "name")
    private String workspaceName;
    private String dataStores;
    private String coverageStores;
    private String wmsStores;
}