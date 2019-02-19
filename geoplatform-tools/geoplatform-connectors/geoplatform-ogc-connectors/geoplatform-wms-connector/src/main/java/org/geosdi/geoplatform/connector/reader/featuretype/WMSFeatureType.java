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
public class WMSFeatureType implements GPWMSFeatureType {

    private static final long serialVersionUID = -2096404960642022019L;
    //
    private final String prefix;
    private final String name;

    /**
     * @param thePrefix
     * @param theName
     */
    public WMSFeatureType(@Nonnull(when = NEVER) String thePrefix, @Nonnull(when = NEVER) String theName) {
        checkArgument((thePrefix != null) && !(thePrefix.trim().isEmpty()), "The Parameter prefix must not be null or an empty string.");
        checkArgument((theName != null) && !(theName.trim().isEmpty()), "The Parameter name must not be null or an empty string.");
        this.prefix = thePrefix;
        this.name = theName;
    }
}