package org.geosdi.geoplatform.connector.geoserver.model.uri;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverStringQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam<String> {

    public GPGeoserverStringQueryParam(@Nonnull(when = When.NEVER) String theKey, @Nonnull(when = When.NEVER) String theValue) {
        super(theKey, theValue);
        Preconditions
                .checkArgument(theValue != null && !(theValue.trim().isEmpty()), "The Parameter value must not be null");
    }

    @Override
    public String formatValue() {
        return this.getValue();
    }
}
