package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.IGPGeoserverWorkspace;

import javax.xml.bind.annotation.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@AllArgsConstructor
@ToString
@XmlRootElement(name = "coverageStore")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "description", "type", "workspace", "enabled", "_default", "url", "coverages"})
public class GPGeoserverCoverageStore implements IGPGeoserverCoverageStore {

    private static final long serialVersionUID = -6970625901177655105L;
    //
    private String name;
    private String description;
    private GPCoverageStoreType type;
    private boolean enabled;
    private IGPGeoserverWorkspace workspace;
    @XmlElement(name = "_default")
    private boolean isDefault;
    private String url;
    private String coverages;

    protected GPGeoserverCoverageStore() {
    }

    /**
     * @param theName
     * @param theDescription
     * @param theType
     * @param theEnabled
     * @param theWorkspace
     * @param theDefault
     * @param theUrl
     * @param theCoverages
     * @return
     */
    @JsonCreator
    protected static IGPGeoserverCoverageStore creator(@JsonProperty(value = "name") String theName,
            @JsonProperty(value = "description") String theDescription, @JsonProperty(value = "type") GPCoverageStoreType theType,
            @JsonProperty(value = "enabled") boolean theEnabled, @JsonProperty(value = "workspace") IGPGeoserverWorkspace theWorkspace,
            @JsonProperty(value = "_default") boolean theDefault, @JsonProperty(value = "url") String theUrl,
            @JsonProperty(value = "coverages") String theCoverages) {
        return new GPGeoserverCoverageStore(theName, theDescription, theType, theEnabled, theWorkspace, theDefault, theUrl, theCoverages);
    }
}