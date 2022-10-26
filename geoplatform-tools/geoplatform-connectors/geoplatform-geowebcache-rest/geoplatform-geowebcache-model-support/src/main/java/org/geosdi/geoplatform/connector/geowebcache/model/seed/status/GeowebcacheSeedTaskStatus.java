package org.geosdi.geoplatform.connector.geowebcache.model.seed.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
public class GeowebcacheSeedTaskStatus implements IGeowebcacheSeedTaskStatus {

    private static final long serialVersionUID = 4789955367732661224L;
    //
    @JsonProperty(value = "long-array-array")
    private final List<List<Long>> statusTaskValues;

    /**
     * @param theStatusTaskValues
     */
    @JsonCreator
    protected GeowebcacheSeedTaskStatus(@Nonnull(when = NEVER) @JsonProperty(value = "long-array-array") List<List<Long>> theStatusTaskValues) {
        checkArgument(theStatusTaskValues != null, "The Parameter statusTaskValues must not be null.");
        this.statusTaskValues = theStatusTaskValues;
    }
}