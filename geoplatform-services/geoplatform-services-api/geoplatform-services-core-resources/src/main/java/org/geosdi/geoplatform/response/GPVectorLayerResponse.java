package org.geosdi.geoplatform.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.GPVectorLayer;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@NoArgsConstructor
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GPVectorLayerResponse implements Serializable {

    private static final long serialVersionUID = 7216996084094454511L;
    //
    @XmlElement(name = "vectorLayer")
    private GPVectorLayer vectorLayer;

    /**
     * @param theVectorLayer
     */
    @JsonCreator
    public GPVectorLayerResponse(@Nonnull(when = NEVER) @JsonProperty(value = "vectorLayer") GPVectorLayer theVectorLayer) {
        checkArgument(theVectorLayer != null, "The Parameter vectorLayer must not be null.");
        this.vectorLayer = theVectorLayer;
    }
}
