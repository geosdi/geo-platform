package org.geosdi.geoplatform.connector.geoserver.request.model.workspace;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlAccessorType(value = FIELD)
public class GPGeoserverWorkspace implements IGPGeoserverWorkspace {

    private static final long serialVersionUID = -7603339038135009989L;
    //
    @XmlElement(name = "name")
    private String workspaceName;
    @XmlElement(name = "href")
    private String workspaceHref;
}
