package org.geosdi.geoplatform.connector.geoserver.request.model.namespace;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "namespaces")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPGeoserverNamespaces implements IGPGeoserverNamespaces {

    private static final long serialVersionUID = -7903676887685842449L;
    //
    @XmlElement(name = "namespace")
    @JsonDeserialize(contentAs = GPGeoserverNamespace.class)
    private List<IGPGeoserverNamespace> namespaces;
}
