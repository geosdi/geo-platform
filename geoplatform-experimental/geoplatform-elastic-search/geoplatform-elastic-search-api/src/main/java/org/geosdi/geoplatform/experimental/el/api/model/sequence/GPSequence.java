package org.geosdi.geoplatform.experimental.el.api.model.sequence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonRootName(value = "GPSequence")
public class GPSequence implements IGPSequence {

    private final String id;

    @JsonCreator
    public GPSequence(String theId) {
        this.id = theId;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getSequenceId() {
        return this.id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " id = '" + id + '\'' +
                '}';
    }
}
