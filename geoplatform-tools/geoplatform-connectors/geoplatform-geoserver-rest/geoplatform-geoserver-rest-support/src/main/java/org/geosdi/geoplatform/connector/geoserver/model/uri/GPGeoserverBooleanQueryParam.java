package org.geosdi.geoplatform.connector.geoserver.model.uri;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverBooleanQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam<Boolean> {

    public GPGeoserverBooleanQueryParam(@Nonnull(when = When.NEVER) String theKey, @Nonnull(when = When.NEVER) Boolean theValue) {
        super(theKey, theValue);
        Preconditions.checkArgument(theValue != null, "The Parameter value must not be null");
    }

    @Override
    public String formatValue() {
        return this.getValue().toString();
    }
}
