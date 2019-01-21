package org.geosdi.geoplatform.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@NoArgsConstructor
@XmlAccessorType(value = FIELD)
public class UserDTOResponse implements Serializable {

    private static final long serialVersionUID = 680093198709942814L;
    //
    @XmlElement(name = "userDTO")
    private UserDTO userDTO;

    /**
     * @param theUserDTO
     */
    @JsonCreator
    public UserDTOResponse(@Nonnull(when = When.NEVER) @JsonProperty(value = "userDTO") UserDTO theUserDTO) {
        checkArgument(theUserDTO != null, "The Parameter userDTO must not be null.");
        this.userDTO = theUserDTO;
    }
}