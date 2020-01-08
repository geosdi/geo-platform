package org.geosdi.geoplatform.experimental.el.api.response;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@ToString
@Immutable
public class GPUpdateResponse implements IGPUpdateResponse {

    private static final long serialVersionUID = 2210245010999973487L;
    //
    private final Boolean status;
    private final Long version;

    /**
     * @param theStatus
     * @param theVersion
     */
    GPUpdateResponse(@Nonnull(when = NEVER) Boolean theStatus, @Nonnull(when = NEVER) Long theVersion) {
        checkArgument(theStatus != null, "The Parameter status must not be null.");
        checkArgument(theVersion != null, "The Parameter version must not be null.");
        this.status = theStatus;
        this.version = theVersion;
    }
}