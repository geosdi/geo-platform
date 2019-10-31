package org.geosdi.geoplatform.experimental.el.rest.api.info;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
class ElasticSearchRestInfo implements GPElasticSearchRestInfo {

    private static final long serialVersionUID = -916192227026156535L;
    //
    private final String nodeName;
    private final GPElasticSearchRestVersion version;
    private final String clusterName;
    private final String clusterUUID;
    private final String tagLine;

    /**
     * @param theNodeName
     * @param theVersion
     * @param theClusterName
     * @param theClusterUUID
     * @param theTagLine
     */
    ElasticSearchRestInfo(String theNodeName, GPElasticSearchRestVersion theVersion, String theClusterName,
            String theClusterUUID, String theTagLine) {
        this.nodeName = theNodeName;
        this.version = theVersion;
        this.clusterName = theClusterName;
        this.clusterUUID = theClusterUUID;
        this.tagLine = theTagLine;
    }
}