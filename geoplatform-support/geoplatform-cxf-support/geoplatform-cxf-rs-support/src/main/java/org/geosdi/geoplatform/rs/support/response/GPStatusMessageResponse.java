package org.geosdi.geoplatform.rs.support.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString(callSuper = true)
@Immutable
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GPStatusMessageResponse extends GPStatusResponse {

    private static final long serialVersionUID = 6292684374465011785L;
    //
    private final String message;

    /**
     * @param theStatus
     * @param theMessage
     */
    @JsonCreator
    public GPStatusMessageResponse(@JsonProperty(value = "status") boolean theStatus, @JsonProperty(value = "message") String theMessage) {
        super(theStatus);
        this.message = theMessage;
    }
}