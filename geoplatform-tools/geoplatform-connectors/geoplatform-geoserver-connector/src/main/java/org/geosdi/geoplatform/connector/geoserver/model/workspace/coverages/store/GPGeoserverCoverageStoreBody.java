package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GPGeoserverCoverageStoreBody implements GeoserverCoverageStoreBody {

    private static final long serialVersionUID = -5544034097091691373L;
    //
    private final String name;
    private final String url;

    /**
     * @param theName
     * @param theUrl
     */
    public GPGeoserverCoverageStoreBody(@Nonnull(when = NEVER) @JsonProperty(value = "name") String theName,
            @Nonnull(when = NEVER) @JsonProperty(value = "url") String theUrl) {
        this.name = theName;
        this.url = theUrl;
    }
}