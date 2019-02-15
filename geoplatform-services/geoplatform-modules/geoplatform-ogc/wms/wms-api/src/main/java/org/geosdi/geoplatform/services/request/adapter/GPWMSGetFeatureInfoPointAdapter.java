package org.geosdi.geoplatform.services.request.adapter;

import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoPoint;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoPoint;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoPointAdapter extends XmlAdapter<WMSGetFeatureInfoPoint, GPWMSGetFeatureInfoPoint> {

    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public GPWMSGetFeatureInfoPoint unmarshal(WMSGetFeatureInfoPoint v) throws Exception {
        return v;
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public WMSGetFeatureInfoPoint marshal(GPWMSGetFeatureInfoPoint v) throws Exception {
        return (WMSGetFeatureInfoPoint) v;
    }
}
