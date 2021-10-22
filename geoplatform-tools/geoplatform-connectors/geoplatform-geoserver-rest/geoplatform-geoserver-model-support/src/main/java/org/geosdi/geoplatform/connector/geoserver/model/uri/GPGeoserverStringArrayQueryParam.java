package org.geosdi.geoplatform.connector.geoserver.model.uri;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverStringArrayQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam<String[]> {

    public GPGeoserverStringArrayQueryParam(@Nonnull(when = When.NEVER) String theKey, @Nonnull(when = When.NEVER) String[] theValue) {
        super(theKey, theValue);
        Preconditions.checkArgument(theValue != null && theValue.length > 0, "The Parameter value must not be null");
    }
    
    @Override
    public String formatValue() {
        return Arrays.stream(this.getValue()).collect(Collectors.joining(","));
    }
}
