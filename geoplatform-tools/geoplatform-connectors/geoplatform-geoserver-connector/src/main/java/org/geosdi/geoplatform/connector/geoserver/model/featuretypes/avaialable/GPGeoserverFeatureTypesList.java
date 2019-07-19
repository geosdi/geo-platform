package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.avaialable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
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
@XmlRootElement(name = "list")
public class GPGeoserverFeatureTypesList implements IGPGeoserverFeatureTypesList {

    private static final long serialVersionUID = 105434675380488208L;
    //
    @XmlElement(name = "string")
    private final List<String> values;

    /**
     * @param theValues
     */
    @JsonCreator
    public GPGeoserverFeatureTypesList(@Nonnull(when = NEVER) @JsonProperty(value = "string") List<String> theValues) {
        checkArgument(theValues != null, "The Parameter values must not be null.");
        this.values = theValues;
    }
}