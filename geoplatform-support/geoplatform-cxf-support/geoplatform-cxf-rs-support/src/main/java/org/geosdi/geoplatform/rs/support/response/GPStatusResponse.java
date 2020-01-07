package org.geosdi.geoplatform.rs.support.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GPStatusResponse implements Serializable {

    private final boolean status;

    /**
     * @param theStatus
     */
    @JsonCreator
    public GPStatusResponse(@JsonProperty(value = "status") boolean theStatus) {
        this.status = theStatus;
    }
}