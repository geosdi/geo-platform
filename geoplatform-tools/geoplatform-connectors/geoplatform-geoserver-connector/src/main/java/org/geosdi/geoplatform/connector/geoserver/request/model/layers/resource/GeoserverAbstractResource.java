package org.geosdi.geoplatform.connector.geoserver.request.model.layers.resource;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlTransient
public abstract class GeoserverAbstractResource implements GeoserverLayerResource {

    private static final long serialVersionUID = -5525127129104725365L;
    //
    private String name;
    private String href;
}
