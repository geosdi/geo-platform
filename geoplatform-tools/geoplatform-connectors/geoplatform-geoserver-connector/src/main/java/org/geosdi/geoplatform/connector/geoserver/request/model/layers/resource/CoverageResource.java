package org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource.LayerResourceType.Coverage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class CoverageResource extends GeoserverAbstractResource {

    private static final long serialVersionUID = -8081490248993023725L;
    //
    @XmlElement(name = "@class")
    private final LayerResourceType type = Coverage;
}
