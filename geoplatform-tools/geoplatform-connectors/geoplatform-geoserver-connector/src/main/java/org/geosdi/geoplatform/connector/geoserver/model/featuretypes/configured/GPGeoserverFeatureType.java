package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverFeatureType implements IGPGeoserverFeatureType {

    private static final long serialVersionUID = -7235705371676527825L;
    //
    private String name;
    private String href;
}