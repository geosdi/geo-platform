package org.geosdi.geoplatform.experimental.el.api.response;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPUpdateResponse extends Serializable {

    /**
     * @return {@link Boolean}
     */
    Boolean getStatus();

    /**
     * @return {@link Long}
     */
    Long getVersion();

    /**
     * @param theStatus
     * @param theVersion
     * @return {@link IGPUpdateResponse}
     * @throws Exception
     */
    static IGPUpdateResponse of(@Nonnull(when = NEVER) Boolean theStatus, @Nonnull(when = NEVER) Long theVersion) throws Exception {
        return new GPUpdateResponse(theStatus, theVersion);
    }
}