package org.geosdi.geoplatform.rs.support.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = IGPCreationResponse.GPCreationResponse.class)
public interface IGPCreationResponse extends Serializable {

    /**
     * @return {@link String}
     */
    String getId();


    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean isIdSetted();

    @Getter
    @ToString
    @XmlAccessorType(XmlAccessType.FIELD)
    class GPCreationResponse implements IGPCreationResponse {

        private static final long serialVersionUID = 7024014926231450966L;
        //
        private final String id;

        /**
         * @param theId
         */
        @JsonCreator
        public GPCreationResponse(@Nonnull(when = NEVER) @JsonProperty(value = "id") String theId) {
            checkArgument(((theId != null) && !(theId.trim().isEmpty())), "The Parameter id mut not be null or an empty string.");
            this.id = theId;
        }

        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean isIdSetted() {
            return ((this.id != null) && !(this.id.trim().isEmpty()));
        }
    }
}