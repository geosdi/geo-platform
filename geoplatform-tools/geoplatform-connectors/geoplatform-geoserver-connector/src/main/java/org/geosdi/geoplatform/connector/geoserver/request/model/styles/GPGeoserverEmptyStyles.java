package org.geosdi.geoplatform.connector.geoserver.request.model.styles;

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
public class GPGeoserverEmptyStyles implements GPGeoserverEmptyResponse<GPGeoserverStyles> {

    private String styles;

    /**
     * @return {@link GPGeoserverStyles}
     */
    @Override
    public GPGeoserverStyles toModel() {
        return new GPGeoserverStyles();
    }
}