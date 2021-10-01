package org.geosdi.geoplatform.connector.geoserver.model.uri;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverStringArrayQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam<String[]> {

    public GPGeoserverStringArrayQueryParam(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) String[] theValue) {
        super(theKey, theValue);
        checkArgument(theValue != null && theValue.length > 0, "The Parameter value must not be null");
    }
    
    @Override
    public String formatValue() {
        return Arrays.stream(this.getValue()).collect(Collectors.joining(","));
    }
}
