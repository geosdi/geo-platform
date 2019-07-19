package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
@XmlRootElement(name = "featureTypes")
public class GPGeoserverFeatureTypes implements IGPGeoserverFeatureTypes {

    private static final long serialVersionUID = -1049360230776512398L;
    //
    private final List<IGPGeoserverFeatureType> featureTypes;

    /**
     * @param theFeatureTypes
     */
    @JsonCreator
    public GPGeoserverFeatureTypes(@Nonnull(when = NEVER) @JsonProperty(value = "featureType") List<IGPGeoserverFeatureType> theFeatureTypes) {
        checkArgument(theFeatureTypes != null, "The Parameter featureTypes must not be null.");
        this.featureTypes = theFeatureTypes;
    }
}