package org.geosdi.geoplatform.connector.geoserver.model.uri;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverBooleanQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam<Boolean> {

    public GPGeoserverBooleanQueryParam(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) Boolean theValue) {
        super(theKey, theValue);
        checkArgument(theValue != null, "The Parameter value must not be null");
    }

    @Override
    public String formatValue() {
        return this.getValue().toString();
    }
}
