package org.geosdi.geoplatform.connector.geoserver.request.model.workspace;

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
@XmlRootElement(name = "workspaces")
@XmlAccessorType(value = FIELD)
public class GPGeoserverWorkspaces implements IGPGeoserverWorkspaces {

    private static final long serialVersionUID = 5444090587246752085L;
    //
    @JsonDeserialize(contentAs = GPGeoserverWorkspace.class)
    @XmlElement(name = "workspace")
    private List<IGPGeoserverWorkspace> workspaces;
}