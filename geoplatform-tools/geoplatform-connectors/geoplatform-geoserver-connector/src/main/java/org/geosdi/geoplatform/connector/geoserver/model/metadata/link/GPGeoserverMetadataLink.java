package org.geosdi.geoplatform.connector.geoserver.model.metadata.link;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
public class GPGeoserverMetadataLink implements IGPGeoserverMetadataLink {

    private static final long serialVersionUID = 4690038103311980721L;
    //
    private String type;
    private String metadataType;
    private String content;
}