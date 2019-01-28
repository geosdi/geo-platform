package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "coverageStores")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class GPGeoserverCoverageStores implements IGPGeoserverCoverageStores {

    private static final long serialVersionUID = -8165003725949281907L;
    //
    @XmlElement(name = "coverageStore")
    private List<GPGeoserverStore> coverageStores;
}