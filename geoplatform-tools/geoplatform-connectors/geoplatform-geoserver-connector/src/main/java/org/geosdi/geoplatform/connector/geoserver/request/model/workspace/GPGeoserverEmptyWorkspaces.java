package org.geosdi.geoplatform.connector.geoserver.request.model.workspace;

import org.geosdi.geoplatform.connector.geoserver.request.model.GPGeoserverEmptyResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverEmptyWorkspaces implements GPGeoserverEmptyResponse<GPGeoserverWorkspaces> {

    private String workspaces;

    /**
     * @return {@link GPGeoserverWorkspaces}
     */
    @Override
    public GPGeoserverWorkspaces toModel() {
        return new GPGeoserverWorkspaces();
    }
}
