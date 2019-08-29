package org.geosdi.geoplatform.connector.geoserver.model.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.connector.geoserver.model.settings.coverage.IGPGeoserverCoverageAccessSettings;
import org.geosdi.geoplatform.connector.geoserver.model.settings.jai.IGPGeoserverJaiSettings;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@XmlRootElement(name = "global")
@Immutable
public class GPGeoserverGlobalSettings implements IGPGeoserverGlobalSettings {

    private static final long serialVersionUID = -152958236682302537L;
    //
    private final IGPGeoserverSettings settings;
    private final IGPGeoserverJaiSettings jai;
    private final IGPGeoserverCoverageAccessSettings coverageAccess;
    private final Integer updateSequence;
    private final Integer featureTypeCacheSize;
    private final boolean globalServices;
    private final Integer xmlPostRequestLogBufferSize;

    /**
     * @param theSettings
     * @param theJai
     * @param theCoverageAccess
     * @param theUpdateSequence
     * @param theFeatureTypeCacheSize
     * @param theGlobalServices
     * @param theXmlPostRequestLogBufferSize
     */
    @JsonCreator
    public GPGeoserverGlobalSettings(@JsonProperty(value = "settings") IGPGeoserverSettings theSettings, @JsonProperty(value = "jai") IGPGeoserverJaiSettings theJai,
            @JsonProperty(value = "coverageAccess") IGPGeoserverCoverageAccessSettings theCoverageAccess, @JsonProperty(value = "updateSequence") Integer theUpdateSequence,
            @JsonProperty(value = "featureTypeCacheSize") Integer theFeatureTypeCacheSize, @JsonProperty(value = "globalServices") boolean theGlobalServices,
            @JsonProperty(value = "xmlPostRequestLogBufferSize") Integer theXmlPostRequestLogBufferSize) {
        this.settings = theSettings;
        this.jai = theJai;
        this.coverageAccess = theCoverageAccess;
        this.updateSequence = theUpdateSequence;
        this.featureTypeCacheSize = theFeatureTypeCacheSize;
        this.globalServices = theGlobalServices;
        this.xmlPostRequestLogBufferSize = theXmlPostRequestLogBufferSize;
    }
}