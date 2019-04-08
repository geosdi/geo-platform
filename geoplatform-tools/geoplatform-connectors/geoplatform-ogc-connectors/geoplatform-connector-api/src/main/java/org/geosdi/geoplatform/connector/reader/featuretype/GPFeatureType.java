package org.geosdi.geoplatform.connector.reader.featuretype;

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
public class GPFeatureType implements IGPFeatureType {

    private static final long serialVersionUID = -4334244381048021735L;
    //
    private final String prefix;
    private final String name;

    /**
     * @param thePrefix
     * @param theName
     */
    public GPFeatureType(@Nonnull(when = NEVER) String thePrefix, @Nonnull(when = NEVER) String theName) {
        checkArgument((thePrefix != null) && !(thePrefix.trim().isEmpty()), "The Parameter prefix must not be null or an empty string.");
        checkArgument((theName != null) && !(theName.trim().isEmpty()), "The Parameter name must not be null or an empty string.");
        this.prefix = thePrefix;
        this.name = theName;
    }
}