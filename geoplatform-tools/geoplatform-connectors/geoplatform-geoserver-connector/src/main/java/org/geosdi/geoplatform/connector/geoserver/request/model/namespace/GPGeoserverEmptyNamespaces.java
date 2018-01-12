package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.request.model.GPGeoserverEmptyResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverEmptyNamespaces implements GPGeoserverEmptyResponse<GPGeoserverNamespaces> {

    private String namespaces;

    /**
     * @return {@link GPGeoserverEmptyNamespaces}
     */
    @Override
    public GPGeoserverNamespaces toModel() {
        return new GPGeoserverNamespaces();
    }
}
