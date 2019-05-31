package org.geosdi.geoplatform.connector.bridge.implementor.gml;

import org.geosdi.geoplatform.connector.bridge.implementor.text.GPWMSGetFeatureInfoTextReader;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML_AS_STRING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoGmlStringReader extends GPWMSGetFeatureInfoTextReader {

    /**
     * @return {@link WMSFeatureInfoFormat}
     */
    @Override
    public WMSFeatureInfoFormat getKey() {
        return GML_AS_STRING;
    }

    @Override
    public Boolean isValid() {
        return TRUE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}