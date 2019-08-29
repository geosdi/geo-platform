package org.geosdi.geoplatform.connector.geoserver.model.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.geoserver.model.settings.contact.IGPGeoserverContactSettings;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class GPGeoserverSettings implements IGPGeoserverSettings {

    private static final long serialVersionUID = -812737277880524071L;
    //
    private final String id;
    private final IGPGeoserverContactSettings contact;
    private final String charset;
    private final Integer numDecimals;
    private final String onlineResource;
    private final boolean verbose;
    private final boolean verboseExceptions;
    private final boolean localWorkspaceIncludesPrefix;

    /**
     * @param theId
     * @param theContact
     * @param theCharset
     * @param theNumDecimals
     * @param theOnlineResource
     * @param theVerbose
     * @param theVerboseExceptions
     * @param theLocalWorkspaceIncludesPrefix
     */
    @JsonCreator
    public GPGeoserverSettings(@JsonProperty(value = "id") String theId, @JsonProperty(value = "contact") IGPGeoserverContactSettings theContact,
            @JsonProperty(value = "charset") String theCharset, @JsonProperty(value = "numDecimals") Integer theNumDecimals,
            @JsonProperty(value = "onlineResource") String theOnlineResource, @JsonProperty(value = "verbose") boolean theVerbose,
            @JsonProperty(value = "verboseExceptions") boolean theVerboseExceptions, @JsonProperty(value = "localWorkspaceIncludesPrefix") boolean theLocalWorkspaceIncludesPrefix) {
        this.id = theId;
        this.contact = theContact;
        this.charset = theCharset;
        this.numDecimals = theNumDecimals;
        this.onlineResource = theOnlineResource;
        this.verbose = theVerbose;
        this.verboseExceptions = theVerboseExceptions;
        this.localWorkspaceIncludesPrefix = theLocalWorkspaceIncludesPrefix;
    }
}