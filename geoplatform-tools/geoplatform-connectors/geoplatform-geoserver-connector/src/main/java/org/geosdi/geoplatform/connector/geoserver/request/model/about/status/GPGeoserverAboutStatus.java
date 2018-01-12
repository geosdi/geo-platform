package org.geosdi.geoplatform.connector.geoserver.request.model.about.status;

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
@XmlRootElement(name = "statuss")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GPGeoserverAboutStatus implements IGPGeoserverAboutStatus {

    private static final long serialVersionUID = -3849179561784167785L;
    //
    @XmlElement(name = "status")
    @JsonDeserialize(contentAs = GPGeoserverAboutStatusEntry.class)
    private List<IGPGeoserverAboutStatusEntry> entries;
}
