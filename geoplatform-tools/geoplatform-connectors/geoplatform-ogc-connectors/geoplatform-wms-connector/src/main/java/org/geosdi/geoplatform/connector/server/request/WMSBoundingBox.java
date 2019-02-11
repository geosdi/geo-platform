package org.geosdi.geoplatform.connector.server.request;

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
public class WMSBoundingBox implements GPWMSBoundingBox {

    private static final long serialVersionUID = 6490150131133508326L;
    //
    private final Double minx;
    private final Double miny;
    private final Double maxx;
    private final Double maxy;
    private String boundingBoxKeyValuePair;

    /**
     * @param theMinx
     * @param theMiny
     * @param theMaxx
     * @param theMaxy
     */
    public WMSBoundingBox(@Nonnull(when = NEVER) Double theMinx, @Nonnull(when = NEVER) Double theMiny,
            @Nonnull(when = NEVER) Double theMaxx, @Nonnull(when = NEVER) Double theMaxy) {
        checkArgument(theMinx != null, "The Parameter minx must not be null.");
        checkArgument(theMiny != null, "The Parameter miny must not be null.");
        checkArgument(theMaxx != null, "The Parameter maxx must not be null.");
        checkArgument(theMaxy != null, "The Parameter maxy must not be null.");
        this.minx = theMinx;
        this.miny = theMiny;
        this.maxx = theMaxx;
        this.maxy = theMaxy;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toWMSKeyValuePair() {
        return this.boundingBoxKeyValuePair = ((this.boundingBoxKeyValuePair != null) ? this.boundingBoxKeyValuePair : this.toInternalWMSKeyValuePair());
    }

    String toInternalWMSKeyValuePair() {
        return "BBOX=".concat(this.minx.toString()).concat(",").concat(this.miny.toString())
                .concat(",").concat(this.maxx.toString()).concat(",").concat(this.maxy.toString());
    }
}