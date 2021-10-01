package org.geosdi.geoplatform.connector.geoserver.model.uri;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverStringQueryParam extends GPGeoserverQueryParam.GeoserverQueryParam {

    public GPGeoserverStringQueryParam(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) String theValue) {
        super(theKey, theValue);
    }
}
