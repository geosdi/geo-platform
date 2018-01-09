package org.geosdi.geoplatform.connector.geoserver.request.model.about.version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "about")
@XmlAccessorType(value = FIELD)
@JsonDeserialize(as = GPGeoserverAboutVersion.class)
public class GPGeoserverAboutVersion implements IGPGeoserverAboutVersion {

    private static final long serialVersionUID = 3027331102616942224L;
    //
    @JsonDeserialize(contentAs = GPGeoserverVersionResource.class)
    @XmlElement(name = "resource")
    private List<IGPGeoserverVersionResource> resources;
}
