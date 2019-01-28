package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "coverageStore")
@XmlAccessorType(FIELD)
@Getter
@ToString
@Immutable
public class GPGeoserverCoverageStoreBody implements IGPGeoserverCoverageStoreBody {

    private static final long serialVersionUID = -4453498418179539490L;
    //
    @XmlElement(name = "name")
    private String coverageName;
    private String description;
    private GPCoverageStoreType type;
    private boolean enabled;
    private String url;
    private String workspace;

    protected GPGeoserverCoverageStoreBody() {
    }

    /**
     * @param theCoverageName
     * @param theUrl
     */
    protected GPGeoserverCoverageStoreBody(@Nonnull(when = NEVER) String theCoverageName, @Nullable String theDescription,
            @Nonnull(when = NEVER) String theWorkspace, @Nonnull(when = NEVER) GPCoverageStoreType theType,
            @Nonnull(when = NEVER) String theUrl, boolean theEnabled) {
        checkArgument((theCoverageName != null) && !(theCoverageName.trim().isEmpty()), "The Parameter coverageName must not be null or an empty string.");
        checkArgument((theWorkspace != null) && !(theWorkspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        checkArgument(theType != null, "The Parameter type must not be null.");
        checkArgument((theUrl != null) && !(theUrl.trim().isEmpty()), "The Parameter url must not be null or an empty string.");
        this.coverageName = theCoverageName;
        this.workspace = theWorkspace;
        this.url = theUrl;
        this.type = theType;
        this.description = theDescription;
        this.enabled = theEnabled;
    }

    /**
     * @param theCoverageName
     * @param theUrl
     * @return {@link IGPGeoserverCoverageStoreBody}
     */
    @JsonCreator
    protected static IGPGeoserverCoverageStoreBody creator(@JsonProperty(value = "name") String theCoverageName,
            @JsonProperty(value = "description") String theDescription, @JsonProperty(value = "workspace") String theWorkspace,
            @JsonProperty(value = "type") GPCoverageStoreType theType, @JsonProperty(value = "url") String theUrl,
            @JsonProperty(value = "enabled") boolean theEnabled) {
        return new GPGeoserverCoverageStoreBody(theCoverageName, theDescription, theWorkspace, theType, theUrl, theEnabled);
    }
}